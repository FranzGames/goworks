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
package org.tvl.goworks.editor.go.completion;

import org.antlr.works.editor.antlr4.completion.AbstractCompletionItem;

/**
 *
 * @author Sam Harwell
 */
public abstract class GoCompletionItem extends AbstractCompletionItem {

    public static final int KEYWORD_SORT_PRIORITY = 100;
    public static final int PACKAGE_SORT_PRIORITY = 100;
    public static final int TYPE_SORT_PRIORITY = 100;
    public static final int LABEL_SORT_PRIORITY = 100;
    public static final int RULE_SORT_PRIORITY = 100;
    public static final int ELEMENT_REFERENCE_SORT_PRIORITY = 100;
    public static final int PROPERTY_SORT_PRIORITY = 100;
    public static final int MEMBER_SORT_PRIORITY = 100;
    public static final int DECLARATION_SORT_PRIORITY = -100;

    public static final String KEYWORD_COLOR = "<font color=#000099>"; //NOI18N
    public static final String FIELD_COLOR = "<font color=#008618>"; //NOI18N
    public static final String METHOD_COLOR = "<font color=#000000>"; //NOI18N
    public static final String PARAMETER_NAME_COLOR = "<font color=#a06001>"; //NOI18N
    public static final String REFERENCE_COLOR = "<font color=#a06001>"; //NOI18N
    public static final String PACKAGE_COLOR = "<font color=#a06001>"; //NOI18N
    public static final String TYPE_COLOR = "<font color=#2B91AF>"; //NOI18N
    public static final String LABEL_COLOR = "<font color=#000000>"; //NOI18N

}
