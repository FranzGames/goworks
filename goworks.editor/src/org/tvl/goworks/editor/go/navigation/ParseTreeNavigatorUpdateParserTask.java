/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.navigation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.VersionedDocumentUtilities;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserDataOptions;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.antlr.works.editor.antlr4.navigation.ParseTreeNode;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.parser.CompiledModel;
import org.tvl.goworks.editor.go.parser.CurrentDeclarationContextData;

/**
 *
 * @author Sam Harwell
 */
public class ParseTreeNavigatorUpdateParserTask implements ParserTask {
    private final Object lock = new Object();

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext parseContext, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results) throws InterruptedException, ExecutionException {
        synchronized (lock) {
            GoParseTreeNavigatorPanel panel = GoParseTreeNavigatorPanel.getInstance();
            if (panel == null) {
                return;
            }

            JTextComponent currentComponent = EditorRegistry.lastFocusedComponent();
            if (currentComponent == null) {
                return;
            }

            Document document = currentComponent.getDocument();
            if (document == null || !VersionedDocumentUtilities.getVersionedDocument(document).equals(snapshot.getVersionedDocument())) {
                return;
            }

            Future<ParserData<CompiledModel>> futureData = taskManager.getData(snapshot, GoParserDataDefinitions.COMPILED_MODEL, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
            ParserData<CompiledModel> parserData = futureData != null ? futureData.get() : null;
            if (parserData == null) {
                return;
            }

            CompiledModel compiledModel = parserData.getData();

            Future<ParserData<CurrentDeclarationContextData>> futureContextData = taskManager.getData(snapshot, GoParserDataDefinitions.CURRENT_DECLARATION_CONTEXT, EnumSet.of(ParserDataOptions.NO_UPDATE, ParserDataOptions.SYNCHRONOUS));
            ParserData<CurrentDeclarationContextData> parserContextData = futureContextData != null ? futureContextData.get() : null;
            CurrentDeclarationContextData context = null;
            if (parserContextData != null) {
                context = parserContextData.getData();
            }

            panel.setCurrentFile(compiledModel.getResult().getFileObject());
            panel.setParseTree(new ParseTreeNode(compiledModel.getResult().getResult()));
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Arrays.<ParserDataDefinition<?>>asList(
                GoParserDataDefinitions.COMPILED_MODEL,
                GoParserDataDefinitions.CURRENT_DECLARATION_CONTEXT);

        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>emptyList();

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Go Parse Tree Navigator Update", INPUTS, OUTPUTS, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
        }
    }

    @MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=ParserTaskProvider.class)
    public static final class Provider extends SingletonParserTaskProvider {

        @Override
        public ParserTaskDefinition getDefinition() {
            return Definition.INSTANCE;
        }

        @Override
        public ParserTask createTaskImpl() {
            return new ParseTreeNavigatorUpdateParserTask();
        }

    }

}