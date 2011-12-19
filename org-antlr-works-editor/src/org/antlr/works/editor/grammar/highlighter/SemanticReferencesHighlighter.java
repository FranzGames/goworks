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
package org.antlr.works.editor.grammar.highlighter;

import javax.swing.text.StyledDocument;
import org.antlr.works.editor.grammar.GrammarEditorKit;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.highlighting.HighlightsLayer;
import org.netbeans.spi.editor.highlighting.HighlightsLayerFactory;
import org.netbeans.spi.editor.highlighting.HighlightsSequence;
import org.netbeans.spi.editor.highlighting.ZOrder;
import org.netbeans.spi.editor.highlighting.support.AbstractHighlightsContainer;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class SemanticReferencesHighlighter extends AbstractHighlightsContainer {
    private final StyledDocument document;

    private SemanticReferencesHighlighter(@NonNull StyledDocument document) {
        Parameters.notNull("document", document);
        this.document = document;
    }

    @Override
    public HighlightsSequence getHighlights(int startOffset, int endOffset) {
        return HighlightsSequence.EMPTY;
    }

    private void initialize() {
    }

    @MimeRegistration(mimeType=GrammarEditorKit.GRAMMAR_MIME_TYPE, service=HighlightsLayerFactory.class)
    public static class LayerFactory implements HighlightsLayerFactory {

        @Override
        public HighlightsLayer[] createLayers(Context context) {
            if (!(context.getDocument() instanceof StyledDocument)) {
                return new HighlightsLayer[0];
            }

            StyledDocument document = (StyledDocument)context.getDocument();
            SemanticReferencesHighlighter highlighter = new SemanticReferencesHighlighter(document);
            highlighter.initialize();
            return new HighlightsLayer[] { HighlightsLayer.create(SemanticReferencesHighlighter.class.getName(), ZOrder.SYNTAX_RACK, true, highlighter) };
        }

    }
}
