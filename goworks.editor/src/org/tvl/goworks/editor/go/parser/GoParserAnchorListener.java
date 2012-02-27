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
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.antlr.netbeans.editor.completion.AbstractAnchor;
import org.antlr.netbeans.editor.completion.Anchor;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.TrackingPositionRegion;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.parser.GoParserBase.TopLevelDeclContext;

/**
 *
 * @author Sam Harwell
 */
public class GoParserAnchorListener extends GoParserBaseBaseListener {
    private final Stack<Integer> anchorPositions = new Stack<Integer>();
    private final List<Anchor> anchors = new ArrayList<Anchor>();
    private final DocumentSnapshot snapshot;
    private final AtomicBoolean cancel;

    public GoParserAnchorListener(DocumentSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);
        this.snapshot = snapshot;
        this.cancel = null;
    }

    public GoParserAnchorListener(DocumentSnapshot snapshot, AtomicBoolean cancel) {
        Parameters.notNull("snapshot", snapshot);
        this.snapshot = snapshot;
        this.cancel = cancel;
    }

    public List<Anchor> getAnchors() {
        return anchors;
    }

    private void checkCancellation() {
        boolean cancelled;
        if (cancel != null) {
            cancelled = cancel.get();
        } else {
            cancelled = Thread.interrupted();
        }

        if (cancelled) {
            throw new CancellationException();
        }
    }

    @Override
    public void enterEveryRule(ParserRuleContext<? extends Token> ctx) {
        checkCancellation();
    }

    @Override
    public void exitEveryRule(ParserRuleContext<? extends Token> ctx) {
        checkCancellation();
    }

    @Override
    public void enterTopLevelDecl(TopLevelDeclContext ctx) {
        enterAnchor(ctx);
    }

    @Override
    public void exitTopLevelDecl(TopLevelDeclContext ctx) {
        exitAnchor(ctx, ctx.ruleIndex);
    }

    private void enterAnchor(ParserRuleContext<Token> ctx) {
        anchorPositions.push(ctx.getStart().getStartIndex());
    }

    private void exitAnchor(ParserRuleContext<Token> ctx, int anchorId) {
        int start = anchorPositions.pop();
        int stop = ctx.getStop() != null ? ctx.getStop().getStopIndex() + 1 : snapshot.length();
        TrackingPositionRegion.Bias trackingMode = ctx.getStop() != null ? TrackingPositionRegion.Bias.Exclusive : TrackingPositionRegion.Bias.Forward;
        anchors.add(createAnchor(ctx, start, stop, trackingMode, anchorId));
    }

    private Anchor createAnchor(ParserRuleContext<Token> ctx, int start, int stop, TrackingPositionRegion.Bias trackingMode, int rule) {
        TrackingPositionRegion trackingSpan = snapshot.createTrackingRegion(start, stop - start, trackingMode);
            return new TemplateAnchor(trackingSpan, rule);
//        }
    }

    public static class TemplateAnchor extends AbstractAnchor {
        private TemplateAnchor(@NonNull TrackingPositionRegion span, int rule) {
            super(span, rule);
        }

        @Override
        protected List<String> getRuleNames() {
            return Arrays.asList(GoParser.ruleNames);
        }
    }

}
