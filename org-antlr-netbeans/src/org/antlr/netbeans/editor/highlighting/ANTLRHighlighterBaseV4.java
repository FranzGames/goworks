/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Sam Harwell
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
package org.antlr.netbeans.editor.highlighting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.spi.editor.highlighting.HighlightsSequence;
import org.netbeans.spi.editor.highlighting.support.AbstractHighlightsContainer;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public abstract class ANTLRHighlighterBaseV4<TState extends LineStateInfo<TState>> extends AbstractHighlightsContainer {
    private static final boolean FULL_CHECKS = false;
    private static final boolean FIX_HIGHLIGHTER_UPDATE_BUG = false;

    private static boolean timeoutReported;

    private final Object lock = new Object();
    private final StyledDocument document;
    private final DocumentListenerImpl documentListener;
    private final ArrayList<TState> lineStates = new ArrayList<TState>();
    private final boolean propagateChangedImmediately;

    private Integer firstDirtyLine;
    private Integer lastDirtyLine;
    private Integer firstChangedLine;
    private Integer lastChangedLine;

    private boolean failedTimeout;

    public ANTLRHighlighterBaseV4(@NonNull StyledDocument document) {
        this(document, true);
    }

    public ANTLRHighlighterBaseV4(@NonNull StyledDocument document, boolean propagateChanges) {
        Parameters.notNull("document", document);
        this.document = document;
        this.propagateChangedImmediately = propagateChanges;
        this.documentListener = new DocumentListenerImpl();
    }

    @NonNull
    protected final StyledDocument getDocument() {
        return document;
    }

    public void initialize() {
        TState dirtyState = getStartState().createDirtyState();
        int lineCount = NbDocument.findLineRootElement(this.document).getElementCount();
        this.lineStates.ensureCapacity(lineCount);
        for (int i = 0; i < lineCount; i++) {
            this.lineStates.add(dirtyState);
        }

        subscribeEvents();
        firstDirtyLine = 0;
        lastDirtyLine = lineStates.size() - 1;
        forceRehighlightLines(0, lineStates.size() - 1);
    }

    @Override
    public HighlightsSequence getHighlights(int startOffset, int endOffset) {
        List<Highlight> highlights = new ArrayList<Highlight>();
        getHighlights(startOffset, endOffset, highlights, null, true, false);
        return new HighlightsList(highlights);
    }

    @CheckForNull
    public Interval getHighlights(int startOffset, int endOffset, List<Highlight> highlights, List<Token> tokens, boolean updateOffsets, boolean propagate) {
        if (highlights == null && tokens == null && !propagate) {
            return null;
        }

        if (endOffset == Integer.MAX_VALUE) {
            endOffset = document.getLength();
        }

        if (highlights != null) {
            highlights.clear();
        }

        if (tokens != null) {
            tokens.clear();
        }

        OffsetRegion span = OffsetRegion.fromBounds(startOffset, endOffset);

        if (failedTimeout) {
            return null;
        }

        int firstUpdatedLine;
        int lastUpdatedLine;
        boolean spanExtended = false;
        int extendMultiLineSpanToLine = 0;
        OffsetRegion extendedSpan = span;

        synchronized (lock) {
            OffsetRegion requestedSpan = span;

            ParseRequest<TState> request = adjustParseSpan(span);
            TState startState = request.getState();
            span = request.getRegion();
            firstUpdatedLine = NbDocument.findLineNumber(document, span.getStart());
            lastUpdatedLine = NbDocument.findLineNumber(document, span.getEnd());

            CharStream input;
            try {
                input = createInputStream(span);
            } catch (BadLocationException ex) {
                return null;
            }

            TokenSourceWithStateV4<TState> lexer = createLexer(input, startState);

            CommonToken previousToken = null;
//            int previousTokenLine = 0;
            boolean previousTokenEndsLine = false;

            /* this is held outside the loop because only tokens which end at the end of a line
             * impact its value.
             */
            boolean lineStateChanged = false;

            while (true)
            {
                // TODO: perform this under a read lock
                CommonToken token = (CommonToken)lexer.nextToken();

                boolean inBounds = token.getStartIndex() < span.getEnd();

                if (updateOffsets) {
                    int startLineCurrent;
                    if (token.getType() == Token.EOF)
                        startLineCurrent = NbDocument.findLineRootElement(document).getElementCount();
                    else
                        startLineCurrent = NbDocument.findLineNumber(document, token.getStartIndex());

//                    if (previousToken == null || previousTokenLine < startLineCurrent - 1)
//                    {
                        // endLinePrevious is the line number the previous token ended on
                        int endLinePrevious;
                        if (previousToken != null)
                            endLinePrevious = NbDocument.findLineNumber(document, previousToken.getStopIndex());
                        else
                            endLinePrevious = NbDocument.findLineNumber(document, span.getStart()) - 1;

                        if (startLineCurrent > endLinePrevious + 1 || (startLineCurrent == endLinePrevious + 1 && !previousTokenEndsLine))
                        {
                            int firstMultilineLine = endLinePrevious;
                            if (previousToken == null || previousTokenEndsLine)
                                firstMultilineLine++;

                            for (int i = firstMultilineLine; i < startLineCurrent; i++)
                            {
                                if (!lineStates.get(i).getIsMultiLineToken() || lineStateChanged)
                                    extendMultiLineSpanToLine = i + 1;

                                if (inBounds)
                                    setLineState(i, lineStates.get(i).createMultiLineState());
                            }
                        }
//                    }
                }

                if (token.getType() == Token.EOF)
                    break;

                if (updateOffsets && isMultiLineToken(lexer, token))
                {
                    int startLine = NbDocument.findLineNumber(document, token.getStartIndex());
                    int stopLine = NbDocument.findLineNumber(document, token.getStopIndex());
                    for (int i = startLine; i < stopLine; i++)
                    {
                        if (!lineStates.get(i).getIsMultiLineToken())
                            extendMultiLineSpanToLine = i + 1;

                        if (inBounds)
                            setLineState(i, lineStates.get(i).createMultiLineState());
                    }
                }

                boolean tokenEndsLine = tokenEndsAtEndOfLine(lexer, token);
                if (updateOffsets && tokenEndsLine)
                {
                    TState stateAtEndOfLine = lexer.getState();
                    int line = NbDocument.findLineNumber(document, token.getStopIndex());
                    lineStateChanged =
                        lineStates.get(line).getIsMultiLineToken()
                        || !lineStates.get(line).equals(stateAtEndOfLine);

                    // even if the state didn't change, we call SetLineState to make sure the _first/_lastChangedLine values get updated.
                    // have to check bounds for this one or the editor might not get an update (if the token ends a line)
                    if (updateOffsets && inBounds)
                        setLineState(line, stateAtEndOfLine);

                    if (lineStateChanged)
                    {
                        if (line < NbDocument.findLineRootElement(document).getElementCount() - 1)
                        {
                            /* update the span's end position or the line state change won't be reflected
                             * in the editor
                             */
                            int endPosition = line < NbDocument.findLineRootElement(document).getElementCount() - 2 ? NbDocument.findLineOffset(document, line + 2) : document.getLength();
                            if (endPosition > extendedSpan.getEnd())
                            {
                                spanExtended = true;
                                extendedSpan = OffsetRegion.fromBounds(extendedSpan.getStart(), endPosition);
                            }
                        }
                    }
                }

                previousToken = token;
                previousTokenEndsLine = tokenEndsLine;

                boolean canBreak = !propagate || !spanExtended;
                if (propagate && spanExtended) {
                    span = OffsetRegion.fromBounds(span.getStart(), extendedSpan.getEnd());
                    lastUpdatedLine = NbDocument.findLineNumber(document, span.getEnd());
                    spanExtended = false;
                }

                if (canBreak && (token.getStartIndex() >= span.getEnd())) {
                    break;
                }

                if (token.getStopIndex() < requestedSpan.getStart()) {
                    continue;
                }

                if (tokens != null) {
                    tokens.add(token);
                }

                if (highlights != null) {
                    Collection<Highlight> tokenClassificationSpans = getHighlightsForToken(token);
                    if (tokenClassificationSpans != null) {
                        highlights.addAll(tokenClassificationSpans);
                    }
                }

                if (canBreak && !inBounds) {
                    break;
                }
            }
        }

        if (updateOffsets && extendMultiLineSpanToLine > 0 && !propagate) {
            int endPosition = extendMultiLineSpanToLine < NbDocument.findLineRootElement(document).getElementCount() - 1 ? NbDocument.findLineOffset(document, extendMultiLineSpanToLine + 1) : document.getLength();
            if (endPosition > extendedSpan.getEnd()) {
                spanExtended = true;
                extendedSpan = OffsetRegion.fromBounds(extendedSpan.getStart(), endPosition);
            }
        }

        if (updateOffsets && spanExtended) {
            /* Subtract 1 from each of these because the spans include the line break on their last
             * line, forcing it to appear as the first position on the following line.
             */
            int firstLine = NbDocument.findLineNumber(document, span.getEnd());
            int lastLine = NbDocument.findLineNumber(document, extendedSpan.getEnd()) - 1;
            forceRehighlightLines(firstLine, lastLine);
        }

        return new Interval(firstUpdatedLine, lastUpdatedLine);
    }

    protected void setLineState(int line, TState state) {
        synchronized (lock) {
            checkDirtyLineBounds();

            assert firstDirtyLine == null || line <= firstDirtyLine || state.getIsDirty();
            lineStates.set(line, state);
            if (!state.getIsDirty() && firstDirtyLine != null && firstDirtyLine.equals(line)) {
                firstDirtyLine++;
            }

            if (!state.getIsDirty() && lastDirtyLine != null && lastDirtyLine.equals(line)) {
                assert firstDirtyLine != null && firstDirtyLine == lastDirtyLine + 1;
                firstDirtyLine = null;
                lastDirtyLine = null;
            }

            checkDirtyLineBounds();
        }
    }

    private void checkDirtyLineBounds() {
        if (!FULL_CHECKS) {
            return;
        }

        if (firstDirtyLine == null) {
            if (lastDirtyLine != null) {
                throw new IllegalStateException();
            }

            for (int i = 0; i < lineStates.size(); i++) {
                if (lineStates.get(i).getIsDirty()) {
                    throw new IllegalStateException();
                }
            }

            return;
        }

        if (lastDirtyLine == null) {
            throw new IllegalStateException();
        }

        for (int i = 0; i < firstDirtyLine; i++) {
            if (lineStates.get(i).getIsDirty()) {
                throw new IllegalStateException();
            }
        }

        for (int i = lastDirtyLine + 1; i < lineStates.size(); i++) {
            if (lineStates.get(i).getIsDirty()) {
                throw new IllegalStateException();
            }
        }
    }

    protected abstract TState getStartState();

    protected ParseRequest<TState> adjustParseSpan(OffsetRegion span) {
        int start = span.getStart();
        int end = span.getEnd();

        if (firstDirtyLine != null) {
            int firstDirtyLineOffset = NbDocument.findLineOffset(document, firstDirtyLine);
            start = Math.min(start, firstDirtyLineOffset);
        }

        TState state = null;
        int startLine = NbDocument.findLineNumber(document, start);
        while (startLine > 0) {
            TState lineState = lineStates.get(startLine - 1);
            if (!lineState.getIsMultiLineToken()) {
                state = lineState;
                break;
            }

            startLine--;
        }

        if (startLine == 0) {
            state = getStartState();
        }

        start = NbDocument.findLineOffset(document, startLine);
        int length = end - start;
        ParseRequest<TState> request = new ParseRequest<TState>(new OffsetRegion(start, length), state);
        return request;
    }

    protected boolean tokenSkippedLines(int endLinePrevious, CommonToken token) {
        int startLineCurrent = NbDocument.findLineNumber(this.document, token.getStartIndex());
        return startLineCurrent > endLinePrevious + 1;
    }

    protected boolean isMultiLineToken(TokenSourceWithStateV4<TState> lexer, CommonToken token) {
        /*if (lexer != null && lexer.getLine() > token.getLine()) {
            return true;
        }*/

        int startLine = NbDocument.findLineNumber(this.document, token.getStartIndex());
        int stopLine = NbDocument.findLineNumber(this.document, token.getStopIndex() + 1);
        return startLine != stopLine;
    }

    protected boolean tokenEndsAtEndOfLine(TokenSourceWithStateV4<TState> lexer, CommonToken token) {
        CharStream charStream = lexer.getCharStream();
        if (charStream != null) {
            int nextCharIndex = token.getStopIndex() + 1;
            if (nextCharIndex >= charStream.size()) {
                return true;
            }

            int c = charStream.substring(token.getStopIndex() + 1, token.getStopIndex() + 1).charAt(0);
            return c == '\r' || c == '\n';
        }

        if (token.getStopIndex() + 1 >= document.getLength()) {
            return true;
        }

        try {
            char c = document.getText(token.getStopIndex() + 1, 1).charAt(0);
            return c == '\r' || c == '\n';
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
            return false;
        }

        /*int line = NbDocument.findLineNumber(document, token.getStopIndex());
        int lineStart = NbDocument.findLineOffset(document, line);
        int nextLineStart = NbDocument.findLineOffset(document, line + 1);
        int lineEnd = nextLineStart - 1;
        if (lineEnd > 0 && lineEnd > lineStart) {
            try {
                char c = document.getText(lineEnd - 1, 1).charAt(0);
                if (c == '\r' || c == '\n') {
                    lineEnd--;
                }
            } catch (BadLocationException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        return lineEnd <= token.getStopIndex() + 1 && nextLineStart >= token.getStopIndex() + 1;*/
    }

    protected CharStream createInputStream(OffsetRegion span) throws BadLocationException {
        CharStream input;
        if (span.getLength() > 1000) {
            input = new DocumentCharStreamV4(this.document, span);
        } else {
            input = new DocumentCharStreamV4(this.document);
        }

        input.seek(span.getStart());
        return input;
    }

    protected abstract TokenSourceWithStateV4<TState> createLexer(CharStream input, TState startState);

    protected Collection<Highlight> getHighlightsForToken(CommonToken token) {
        AttributeSet attributes = highlightToken(token);
        if (attributes != null && attributes.getAttributeCount() > 0) {
            return new SingleHighlightSequence(new Highlight(token.getStartIndex(), token.getStopIndex() + 1, attributes));
        }

        return Collections.emptyList();
    }

    protected AttributeSet highlightToken(Token token) {
        return null;
    }

    public void forceRehighlightLines(int startLine, int endLineInclusive) {
        checkDirtyLineBounds();

        firstDirtyLine = firstDirtyLine != null ? Math.min(firstDirtyLine, startLine) : startLine;
        lastDirtyLine = lastDirtyLine != null ? Math.max(lastDirtyLine, endLineInclusive) : endLineInclusive;

        int start = NbDocument.findLineOffset(document, startLine);
        int end = (endLineInclusive == lineStates.size() - 1) ? document.getLength() : NbDocument.findLineOffset(document, endLineInclusive + 1);
        if (FIX_HIGHLIGHTER_UPDATE_BUG) {
            fireHighlightsChange(start, document.getLength());
        } else {
            fireHighlightsChange(start, end);
        }

        checkDirtyLineBounds();
    }

    protected void subscribeEvents() {
        this.document.addDocumentListener(this.documentListener);
    }

    protected void unsubscribeEvents() {
        this.document.removeDocumentListener(this.documentListener);
    }

    private final class DocumentListenerImpl implements DocumentListener {

        @Override
        public void changedUpdate(DocumentEvent e) {
            int lineCountDelta = NbDocument.findLineRootElement(document).getElementCount() - lineStates.size();
            int oldOffset = e.getOffset();
            int oldLength = e.getLength();
            int newOffset = e.getOffset();
            int newLength = e.getLength();

            processChange(lineCountDelta, oldOffset, oldLength, newOffset, newLength);
            processAfterChange();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            int lineCountDelta = NbDocument.findLineRootElement(document).getElementCount() - lineStates.size();
            int oldOffset = e.getOffset();
            int oldLength = 0;
            int newOffset = e.getOffset();
            int newLength = e.getLength();

            processChange(lineCountDelta, oldOffset, oldLength, newOffset, newLength);
            processAfterChange();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            int lineCountDelta = NbDocument.findLineRootElement(document).getElementCount() - lineStates.size();
            int oldOffset = e.getOffset();
            int oldLength = e.getLength();
            int newOffset = e.getOffset();
            int newLength = 0;

            processChange(lineCountDelta, oldOffset, oldLength, newOffset, newLength);
            processAfterChange();
        }

        private void processChange(int lineCountDelta, int oldOffset, int oldLength, int newOffset, int newLength) {
            synchronized (lock) {
                int lineNumberFromPosition = NbDocument.findLineNumber(document, newOffset);
                int num2 = NbDocument.findLineNumber(document, newOffset + newLength);
                if (lineCountDelta < 0) {
                    lineStates.subList(lineNumberFromPosition, lineNumberFromPosition + Math.abs(lineCountDelta)).clear();
                } else if (lineCountDelta > 0) {
                    TState endLineState = lineStates.get(lineNumberFromPosition);
                    List<TState> insertedElements = new ArrayList<TState>();
                    for (int i = 0; i < lineCountDelta; i++) {
                        insertedElements.add(endLineState);
                    }
                    lineStates.addAll(lineNumberFromPosition, insertedElements);
                }

                if (lastDirtyLine != null && lastDirtyLine > lineNumberFromPosition) {
                    lastDirtyLine += lineCountDelta;
                }

                if (lastChangedLine != null && lastChangedLine > lineNumberFromPosition) {
                    lastChangedLine += lineCountDelta;
                }

                for (int i = lineNumberFromPosition; i <= num2; i++) {
                    TState state = lineStates.get(i);
                    lineStates.set(i, state.createDirtyState());
                }

                firstDirtyLine = firstDirtyLine != null ? Math.min(firstDirtyLine, lineNumberFromPosition) : lineNumberFromPosition;
                lastDirtyLine = lastDirtyLine != null ? Math.max(lastDirtyLine, num2) : num2;
                firstChangedLine = firstChangedLine != null ? Math.min(firstChangedLine, lineNumberFromPosition) : lineNumberFromPosition;
                lastChangedLine = lastChangedLine != null ? Math.max(lastChangedLine, num2) : num2;
            }
        }

        private void processAfterChange() {
            if (firstChangedLine != null && lastChangedLine != null) {
                int startRehighlightLine = firstChangedLine;
                int endRehighlightLine = Math.min(lastChangedLine, NbDocument.findLineRootElement(document).getElementCount() - 1);

                firstChangedLine = null;
                lastChangedLine = null;

                if (propagateChangedImmediately) {
                    if (firstDirtyLine != null) {
                        startRehighlightLine = Math.min(startRehighlightLine, firstDirtyLine);
                    }

                    if (lastDirtyLine != null) {
                        endRehighlightLine = Math.max(endRehighlightLine, lastDirtyLine);
                    }

                    int startOffset = NbDocument.findLineOffset(document, startRehighlightLine);
                    int endOffset;
                    if (endRehighlightLine == NbDocument.findLineRootElement(document).getElementCount() - 1) {
                        endOffset = document.getLength();
                    } else {
                        endOffset = NbDocument.findLineOffset(document, endRehighlightLine + 1) - 1;
                    }

                    Interval propagatedChangedLines = getHighlights(startOffset, endOffset, null, null, true, true);
                    if (propagatedChangedLines != null) {
                        forceRehighlightLines(propagatedChangedLines.a, propagatedChangedLines.b);
                        return;
                    }
                }

                forceRehighlightLines(startRehighlightLine, endRehighlightLine);
            }
        }
    }
}
