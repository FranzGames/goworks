/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.parsing.spi.BaseParserData;
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
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.tvl.goworks.editor.GoEditorKit;
import org.tvl.goworks.editor.go.GoParserDataDefinitions;

/**
 *
 * @author Sam Harwell
 */
public final class DynamicAnchorsParserTask implements ParserTask {

    private DynamicAnchorsParserTask() {
    }

    @Override
    public ParserTaskDefinition getDefinition() {
        return Definition.INSTANCE;
    }

    @Override
    public void parse(ParserTaskManager taskManager, ParseContext context, DocumentSnapshot snapshot, Collection<? extends ParserDataDefinition<?>> requestedData, ParserResultHandler results)
        throws InterruptedException, ExecutionException {
        Future<ParserData<List<Anchor>>> futureReferenceData = taskManager.getData(snapshot, GoParserDataDefinitions.REFERENCE_ANCHOR_POINTS, EnumSet.of(ParserDataOptions.ALLOW_STALE, ParserDataOptions.SYNCHRONOUS));
        assert futureReferenceData != null;

        ParserData<List<Anchor>> referenceData = futureReferenceData != null ? futureReferenceData.get() : null;
        assert referenceData != null;

        List<Anchor> anchors = referenceData != null ? referenceData.getData() : null;
        assert anchors != null;

        BaseParserData<List<Anchor>> result = new BaseParserData<>(context, GoParserDataDefinitions.DYNAMIC_ANCHOR_POINTS, snapshot, anchors);
        results.addResult(result);
    }

    private static final class Definition extends ParserTaskDefinition {
        private static final Collection<ParserDataDefinition<?>> INPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GoParserDataDefinitions.REFERENCE_ANCHOR_POINTS);
        private static final Collection<ParserDataDefinition<?>> OUTPUTS =
            Collections.<ParserDataDefinition<?>>singletonList(GoParserDataDefinitions.DYNAMIC_ANCHOR_POINTS);

        public static final Definition INSTANCE = new Definition();

        public Definition() {
            super("Go Dynamic Anchors", INPUTS, OUTPUTS, ParserTaskScheduler.CONTENT_SENSITIVE_TASK_SCHEDULER);
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
            return new DynamicAnchorsParserTask();
        }

    }

}
