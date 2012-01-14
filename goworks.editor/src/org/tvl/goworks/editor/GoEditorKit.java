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
package org.tvl.goworks.editor;

import java.util.prefs.Preferences;
import javax.swing.Action;
import javax.swing.text.EditorKit;
import javax.swing.text.TextAction;
import org.antlr.netbeans.editor.commenting.BlockCommentFormat;
import org.antlr.netbeans.editor.commenting.ExtendedCommentAction;
import org.antlr.netbeans.editor.commenting.ExtendedUncommentAction;
import org.antlr.netbeans.editor.commenting.LineCommentFormat;
import org.antlr.netbeans.editor.commenting.StandardCommenter;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.editor.settings.SimpleValueNames;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.editor.NbEditorKit;

/**
 *
 * @author Sam Harwell
 */
@MimeRegistration(mimeType=GoEditorKit.GO_MIME_TYPE, service=EditorKit.class)
public class GoEditorKit extends NbEditorKit {

    public static final String GO_MIME_TYPE = "text/x-go";

    private static final LineCommentFormat LINE_COMMENT_FORMAT = new LineCommentFormat("//");
    private static final BlockCommentFormat BLOCK_COMMENT_FORMAT = new BlockCommentFormat("/*", "*/");

    @Override
    protected void initDocument(BaseDocument doc) {
        super.initDocument(doc);

        Preferences preferences = MimeLookup.getLookup(GO_MIME_TYPE).lookup(Preferences.class);
        if (preferences != null) {
            preferences.putInt(SimpleValueNames.COMPLETION_AUTO_POPUP_DELAY, 0);
        }
    }

    @Override
    public String getContentType() {
        return GO_MIME_TYPE;
    }

    @Override
    protected Action[] createActions() {
        Action[] superActions = super.createActions();

        StandardCommenter commenter = new StandardCommenter(LINE_COMMENT_FORMAT, BLOCK_COMMENT_FORMAT);
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ExtendedCommentAction commentAction = new ExtendedCommentAction(commenter);
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ExtendedUncommentAction uncommentAction = new ExtendedUncommentAction(commenter);

        Action[] actions = {
        };

        actions = TextAction.augmentList(superActions, actions);
        for (int i = 0; i < actions.length; i++) {
            if (actions[i] instanceof CommentAction) {
                actions[i] = commentAction;
            } else if (actions[i] instanceof UncommentAction) {
                actions[i] = uncommentAction;
            }
        }

        return actions;
    }

}
