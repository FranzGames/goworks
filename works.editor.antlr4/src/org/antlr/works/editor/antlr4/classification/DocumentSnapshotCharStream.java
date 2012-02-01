/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.antlr4.classification;

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.DocumentSnapshotLine;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.v4.runtime.CharStream;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class DocumentSnapshotCharStream implements CharStream {

    // input info
    private final DocumentSnapshot snapshot;
    private final int count;
    private String sourceName = "Snapshot";

    // position info
    private int index;
    private int line;
    private int charPositionInLine;

    // cache info
    private boolean explicitCache;
    private int currentSnapshotLineStartIndex;
    private String currentSnapshotLine;

    public DocumentSnapshotCharStream(DocumentSnapshot snapshot) {
        Parameters.notNull("snapshot", snapshot);

        this.snapshot = snapshot;
        this.count = snapshot.length();

        updateCachedLine();
    }

    public DocumentSnapshotCharStream(DocumentSnapshot snapshot, OffsetRegion cachedSpan) {
        this(new SnapshotPositionRegion(snapshot, cachedSpan));
    }

    public DocumentSnapshotCharStream(SnapshotPositionRegion cachedSpan) {
        this.snapshot = cachedSpan.getSnapshot();
        this.count = getSnapshot().length();
        this.explicitCache = true;
        this.currentSnapshotLineStartIndex = cachedSpan.getStart().getOffset();
        this.currentSnapshotLine = cachedSpan.getText();
    }

    public final DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCharPositionInLine() {
        return charPositionInLine;
    }

    public void setCharPositionInLine(int charPositionInLine) {
        this.charPositionInLine = charPositionInLine;
    }

    @Override
    public final int size() {
        return count;
    }

    @Override
    public final int index() {
        return index;
    }

    private void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        Parameters.notNull("sourceName", sourceName);
        this.sourceName = sourceName;
    }

    public int LT(int i) {
        return LA(i);
    }

    @Override
    public String substring(int start, int stop) {
        if (currentSnapshotLine != null) {
            if (start >= currentSnapshotLineStartIndex && stop < currentSnapshotLineStartIndex + currentSnapshotLine.length()) {
                return currentSnapshotLine.substring(start - currentSnapshotLineStartIndex, stop + 1 - currentSnapshotLineStartIndex);
            }
        }

        // HACK: special handling due to Lexer passing invalid indexes.
        if (stop >= size()) {
            stop = size() - 1;
        }

        return getSnapshot().subSequence(start, stop + 1).toString();
    }

    @Override
    public void consume() {
        int la = LA(1);
        if (la < 0) {
            return;
        }

        if (la == '\n') {
            setLine(getLine() + 1);
            setCharPositionInLine(0);
        } else {
            setCharPositionInLine(getCharPositionInLine() + 1);
        }

        setIndex(index() + 1);
        updateCachedLine();
    }

    @Override
    public int LA(int i) {
        if (i == 0) {
            // undefined
            return 0;
        }

        if (i < 0) {
            // e.g., translate LA(-1) to use offset i=0; then data[p+0-1]
            i++;
            if ((index() + i - 1) < 0) {
                // invalid; no char before first char
                return CharStream.EOF;
            }
        }

        if ((index() + i - 1) >= size()) {
            return CharStream.EOF;
        }

        int actualIndex = index() + i - 1;

        if (currentSnapshotLine != null
            && actualIndex >= currentSnapshotLineStartIndex
            && actualIndex < currentSnapshotLineStartIndex + currentSnapshotLine.length()) {
            return currentSnapshotLine.charAt(actualIndex - currentSnapshotLineStartIndex);
        }

        return getSnapshot().charAt(actualIndex);
    }

    @Override
    public int mark() {
        return 0;
    }

    @Override
    public void release(int marker) {
    }

    @Override
    public void seek(int index) {
        if (index == index()) {
            return;
        }

        setIndex(index);
        DocumentSnapshotLine currentLine = getSnapshot().findLineFromOffset(index());
        setLine(currentLine.getLineNumber());
        setCharPositionInLine(index() - currentLine.getStart().getOffset());
        updateCachedLine();
    }

    private void updateCachedLine() {
        if (explicitCache) {
            return;
        }

        if (currentSnapshotLine == null
            || index() < currentSnapshotLineStartIndex
            || index() >= currentSnapshotLineStartIndex + currentSnapshotLine.length()) {
            if (index() >= 0 && index() < size()) {
                DocumentSnapshotLine currentLine = getSnapshot().findLineFromOffset(index());
                currentSnapshotLineStartIndex = currentLine.getStart().getOffset();
                currentSnapshotLine = currentLine.getTextIncludingLineBreak();
            } else {
                currentSnapshotLine = null;
                currentSnapshotLineStartIndex = 0;
            }
        }
    }

}
