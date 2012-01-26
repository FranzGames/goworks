/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.tvl.goworks.editor.go.parser;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.shared.TaggerTokenSource;
import org.antlr.works.editor.shared.completion.Anchor;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.openide.util.Exceptions;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.codemodel.FileModel;
import org.tvl.goworks.editor.go.codemodel.impl.CodeModelCacheImpl;
import org.tvl.goworks.editor.go.codemodel.impl.FileModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class ReferenceAnchorsParserTask implements ParserTask {
    private static final Map<Thread, Reference<GoParser>> parserCache =
        new WeakHashMap<Thread, Reference<GoParser>>();

    private static final Logger LOGGER = Logger.getLogger(ReferenceAnchorsParserTask.class.getName());

    private final VersionedDocument document;

    private ReferenceAnchorsParserTask(VersionedDocument document) {
        this.document = document;
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    protected GoParser createParser(TokenStream input, DocumentSnapshot snapshot) {
        synchronized (parserCache) {
            Reference<GoParser> ref = parserCache.get(Thread.currentThread());
            GoParser parser = ref != null ? ref.get() : null;
            if (parser == null) {
                parser = new GoParser(input, snapshot);
                parserCache.put(Thread.currentThread(), new SoftReference<GoParser>(parser));
            } else {
                parser.setTokenStream(input);
            }

            parser.setBuildParseTree(true);
            return parser;
        }
    }

    @Override
    public void parse(ParserTaskManager taskManager, JTextComponent component, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results) throws InterruptedException, ExecutionException {
        Future<ParserData<Tagger<TokenTag<Token>>>> futureTokensData = taskManager.getData(snapshot, GoParserDataDefinitions.LEXER_TOKENS);
        Tagger<TokenTag<Token>> tagger = futureTokensData.get().getData();
        TaggerTokenSource tokenSource = new TaggerTokenSource(tagger, snapshot);

        InterruptableTokenStream tokenStream = new InterruptableTokenStream(tokenSource);
        GoParser parser = createParser(tokenStream, snapshot);
        ParserRuleContext<Token> parseResult = parser.sourceFile();

        ParserData<ParserRuleContext<Token>> parseTreeResult = new BaseParserData<ParserRuleContext<Token>>(GoParserDataDefinitions.REFERENCE_PARSE_TREE, snapshot, parseResult);
        results.addResult(parseTreeResult);

        if (snapshot.getVersionedDocument().getDocument() != null) {
            GoParserAnchorListener listener = new GoParserAnchorListener(snapshot);
            ParseTreeWalker.DEFAULT.walk(listener, parseResult);
            ParserData<List<Anchor>> result = new BaseParserData<List<Anchor>>(GoParserDataDefinitions.REFERENCE_ANCHOR_POINTS, snapshot, listener.getAnchors());
            results.addResult(result);
        }

        try {
            CodeModelBuilderListener codeModelBuilderListener = new CodeModelBuilderListener(snapshot, tokenStream);
            ParseTreeWalker.DEFAULT.walk(codeModelBuilderListener, parseResult);
            FileModelImpl fileModel = codeModelBuilderListener.getFileModel();
            if (fileModel != null) {
                updateCodeModelCache(fileModel);
            }
            ParserData<FileModel> fileModelResult = new BaseParserData<FileModel>(GoParserDataDefinitions.FILE_MODEL, snapshot, fileModel);
            results.addResult(fileModelResult);
        } catch (RuntimeException ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                Exceptions.printStackTrace(ex);
            }
            throw ex;
        } catch (Error ex) {
            if (LOGGER.isLoggable(Level.FINE)) {
                Exceptions.printStackTrace(ex);
            }
            throw ex;
        }
    }

    private void updateCodeModelCache(FileModelImpl fileModel) {
        CodeModelCacheImpl codeModelCache = CodeModelCacheImpl.getInstance();
        codeModelCache.updateFile(fileModel);
    }

    private static class InterruptableTokenStream extends CommonTokenStream {
        public InterruptableTokenStream(TokenSource tokenSource) {
            super(tokenSource);
        }

        @Override
        public void consume() {
            if (Thread.interrupted()) {
                throw new CancellationException();
            }

            super.consume();
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>emptyList();
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                GoParserDataDefinitions.REFERENCE_ANCHOR_POINTS,
                GoParserDataDefinitions.REFERENCE_PARSE_TREE,
                GoParserDataDefinitions.FILE_MODEL);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Go Reference Anchors", INPUTS, OUTPUTS, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider implements ParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTask(VersionedDocument document) {
            return new ReferenceAnchorsParserTask(document);
        }

    }

}
