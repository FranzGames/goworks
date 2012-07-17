/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.antlr.netbeans.editor.parsing.SyntaxError;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.netbeans.parsing.spi.ParseContext;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataDefinition;
import org.antlr.netbeans.parsing.spi.ParserResultHandler;
import org.antlr.netbeans.parsing.spi.ParserTask;
import org.antlr.netbeans.parsing.spi.ParserTaskDefinition;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.netbeans.parsing.spi.ParserTaskProvider;
import org.antlr.netbeans.parsing.spi.ParserTaskScheduler;
import org.antlr.netbeans.parsing.spi.SingletonParserTaskProvider;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;
import org.tvl.goworks.editor.go.parser.CompiledModel;

/**
 *
 * @author Sam Harwell
 */
public class SyntaxErrorsHighlightingParserTask implements ParserTask {

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {

        if (snapshot.getVersionedDocument().getDocument() == null) {
            // no errors update for background parsed document
            return;
        }

        Future<ParserData<CompiledModel>> futureData = taskManager.getData(snapshot, context.getComponent(), GoParserDataDefinitions.COMPILED_MODEL);
        ParserData<CompiledModel> parserData = futureData.get();
        CompiledModel model = parserData.getData();
        DocumentSnapshot latestSnapshot = snapshot.getVersionedDocument().getCurrentSnapshot();

        try {
            List<? extends SyntaxError> syntaxErrors = model.getResult().getSyntaxErrors();
            Document document = model.getSnapshot().getVersionedDocument().getDocument();
            List<ErrorDescription> errors = new ArrayList<ErrorDescription>();
            for (SyntaxError syntaxError : syntaxErrors) {
                SnapshotPositionRegion location = syntaxError.getLocation();
                if (location == null) {
                    continue;
                }

                TrackingPositionRegion trackingRegion = snapshot.createTrackingRegion(location.getRegion(), TrackingPositionRegion.Bias.Forward);
                SnapshotPositionRegion region = trackingRegion.getRegion(latestSnapshot);

                String message = syntaxError.getMessage();
                try {
                    ErrorDescription errorDescription;
                    if (document != null) {
                        errorDescription = ErrorDescriptionFactory.createErrorDescription(syntaxError.getSeverity(), message, document, document.createPosition(region.getStart().getOffset()), document.createPosition(region.getEnd().getOffset()));
                    } else {
                        FileObject file = context.getDocument().getFileObject();
                        errorDescription = ErrorDescriptionFactory.createErrorDescription(syntaxError.getSeverity(), message, file, region.getStart().getOffset(), region.getEnd().getOffset());
                    }
                    errors.add(errorDescription);
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }

            if (document != null) {
                HintsController.setErrors(document, "go-syntax", errors);
            } else {
                HintsController.setErrors(context.getDocument().getFileObject(), "go-syntax", errors);
            }
        } catch (RuntimeException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GoParserDataDefinitions.COMPILED_MODEL);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS = Collections.emptyList();

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Go Syntax Errors Highlighting", INPUTS, OUTPUTS, ParserTaskScheduler.INPUT_SENSITIVE_TASK_SCHEDULER);
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
            return new SyntaxErrorsHighlightingParserTask();
        }

    }

}
