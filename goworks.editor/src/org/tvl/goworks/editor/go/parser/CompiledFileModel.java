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
package org.tvl.goworks.editor.go.parser;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;
import org.antlr.works.editor.shared.parser.SyntaxError;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.filesystems.FileObject;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.parser.GoParserBase.sourceFileContext;

/**
 *
 * @author Sam Harwell
 */
public class CompiledFileModel {

    @NonNull
    private final FileObject fileObject;
    private final Token[] tokens;

    private final GoParser parser;
    private final sourceFileContext result;
    private final List<? extends SyntaxError> syntaxErrors;

    public CompiledFileModel(@NonNull FileObject fileObject) {
        Parameters.notNull("fileObject", fileObject);

        this.fileObject = fileObject;
        this.tokens = null;
        this.parser = null;
        this.result = null;
        this.syntaxErrors = null;
    }

    public CompiledFileModel(GoParser parser, sourceFileContext result, List<? extends SyntaxError> syntaxErrors, @NonNull FileObject fileObject, @NullAllowed Token[] tokens) {
        Parameters.notNull("fileObject", fileObject);

        this.fileObject = fileObject;
        this.tokens = tokens;
        this.parser = parser;
        this.result = result;
        this.syntaxErrors = syntaxErrors;
    }

    public @NonNull FileObject getFileObject() {
        return fileObject;
    }

    public @CheckForNull Token[] getTokens() {
        return tokens;
    }

    public GoParser getParser() {
        return parser;
    }

    public sourceFileContext getResult() {
        return result;
    }

    public List<? extends SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

}
