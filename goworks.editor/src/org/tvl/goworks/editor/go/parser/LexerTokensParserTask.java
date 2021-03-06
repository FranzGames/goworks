/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutionException;
import org.antlr.netbeans.editor.classification.TokenTag;
import org.antlr.netbeans.editor.tagging.Tagger;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocument;
import org.antlr.netbeans.parsing.spi.BaseParserData;
import org.antlr.netbeans.parsing.spi.DocumentParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.v4.runtime.Token;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;

/**
 *
 * @author Sam Harwell
 */
public final class LexerTokensParserTask implements ParserTask {

    private static final String DOCUMENT_CACHE_KEY = LexerTokensParserTask.class.getName() + "-snapshot-data";

    private final Object lock = new Object();

    private LexerTokensParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        if (requestedData.contains(GoParserDataDefinitions.LEXER_TOKENS)) {
            DocumentCacheMap documentCache;
            
            synchronized (lock) {
                documentCache = (DocumentCacheMap)snapshot.getVersionedDocument().getProperty(DOCUMENT_CACHE_KEY);

                if (documentCache == null) {
                    documentCache = new DocumentCacheMap();
                    snapshot.getVersionedDocument().putProperty(DOCUMENT_CACHE_KEY, documentCache);
                }
            }

            ParserData<Tagger<TokenTag<Token>>> result;
            synchronized (documentCache) {
                result = documentCache.get(snapshot);

                if (result == null) {
                    int requestedVersion = snapshot.getVersion().getVersionNumber();
                    ParserData<Tagger<TokenTag<Token>>> previousResult = null;
                    int previousVersion = -1;
                    List<ParserData<Tagger<TokenTag<Token>>>> values;
                    synchronized (documentCache) {
                        values = new ArrayList<>(documentCache.values());
                    }

                    for (ParserData<Tagger<TokenTag<Token>>> data : values) {
                        int dataVersion = data.getSnapshot().getVersion().getVersionNumber();
                        if (dataVersion > previousVersion && dataVersion < requestedVersion) {
                            previousResult = data;
                            previousVersion = dataVersion;
                        }
                    }

                    GoTokensTaskTaggerSnapshot previousTagger = previousResult != null ? (GoTokensTaskTaggerSnapshot)previousResult.getData() : null;
                    if (previousTagger != null) {
                        result = new BaseParserData<>(context, GoParserDataDefinitions.LEXER_TOKENS, snapshot, previousTagger.translateTo(snapshot));
                    } else {
                        GoTokensTaskTaggerSnapshot tagger = new GoTokensTaskTaggerSnapshot(snapshot);
                        tagger.initialize();
                        result = new BaseParserData<>(context, GoParserDataDefinitions.LEXER_TOKENS, snapshot, tagger);
                    }

                    synchronized (documentCache) {
                        ParserData<Tagger<TokenTag<Token>>> updatedResult = documentCache.get(snapshot);
                        if (updatedResult != null) {
                            result = updatedResult;
                        } else {
                            documentCache.put(snapshot, result);
                        }
                    }
                }
            }
            
            results.addResult(result);
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>emptyList();
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GoParserDataDefinitions.LEXER_TOKENS);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Go Lexer Tokens", INPUTS, OUTPUTS, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends DocumentParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl(VersionedDocument document) {
            return new LexerTokensParserTask();
        }

    }

    private static class DocumentCacheMap extends WeakHashMap<DocumentSnapshot, ParserData<Tagger<TokenTag<Token>>>> {
    }

}
