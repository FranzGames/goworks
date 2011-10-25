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
package org.antlr.works.editor.grammar.fold;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.StyledDocument;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.tool.ast.GrammarAST;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.antlr.works.editor.grammar.parser.GrammarParser.GrammarParserResult;
import org.antlr.works.editor.grammar.parser.GrammarParserV4;
import org.openide.text.NbDocument;

/**
 *
 * @author sam
 */
public class GrammarFoldManagerTaskV4 extends GrammarFoldManagerTask {

    @Override
    protected List<FoldInfo> calculateFolds(StyledDocument document, GrammarParserResult result) {
        GrammarParserV4.GrammarParserResultV4 result4 = (GrammarParserV4.GrammarParserResultV4)result;

        final List<GrammarFoldManagerTask.FoldInfo> folds = new ArrayList<GrammarFoldManagerTask.FoldInfo>();

        GrammarRootAST parseResult = result4.getResult().getResult();
        if (parseResult != null) {
            IntervalSet foldTypes = new IntervalSet();
            foldTypes.add(ANTLRParser.MODE);
            foldTypes.add(ANTLRParser.RULE);
            foldTypes.add(ANTLRParser.TOKENS);
            foldTypes.add(ANTLRParser.OPTIONS);
//            foldTypes.add(ANTLRParser.AT);

            List<GrammarAST> foldNodes = parseResult.getNodesWithType(foldTypes);
            for (GrammarAST child : foldNodes) {
                String blockHint = null;
                switch (child.getType()) {
                case ANTLRParser.MODE:
                    blockHint = "mode " + child.getChild(0).getText();
                    break;

                case ANTLRParser.RULE:
                    blockHint = child.getChild(0).getText() + "...";
                    break;

                case ANTLRParser.TOKENS:
                    blockHint = "tokens {...}";
                    break;

                case ANTLRParser.OPTIONS:
                    blockHint = "options {...}";
                    break;

//                case ANTLRParser.AT:
//                    if (child.getChildCount() == 2) {
//                        blockHint = "@" + child.getChild(0) + " {...}";
//                    } else if (child.getChildCount() == 3) {
//                        blockHint = "@" + child.getChild(0) + "::" + child.getChild(1).getText() + " {...}";
//                    }
//                    break;

                default:
                    continue;
                }

                if (blockHint == null || blockHint.isEmpty()) {
                    continue;
                }

                GrammarFoldManagerTask.FoldInfo fold = createFold(child, blockHint, document, result.getResult().getTokens());
                if (fold != null) {
                    folds.add(fold);
                }
            }

            for (CommonToken token : result4.getResult().getTokens()) {
                switch (token.getType()) {
                case ANTLRParser.DOC_COMMENT:
                case ANTLRParser.COMMENT:
                case ANTLRParser.ACTION:
                    int startLine = NbDocument.findLineNumber(document, token.getStartIndex());
                    int stopLine = NbDocument.findLineNumber(document, token.getStopIndex() + 1);
                    if (startLine >= stopLine) {
                        continue;
                    }

                    String blockHint = null;
                    if (token.getType() == ANTLRParser.DOC_COMMENT) {
                        blockHint = "/** ... */";
                    } else if (token.getType() == ANTLRParser.COMMENT) {
                        blockHint = "/* ... */";
                    } else if (token.getType() == ANTLRParser.ACTION) {
                        blockHint = "{}";
                    } else {
                        throw new IllegalStateException();
                    }

                    GrammarFoldManagerTask.FoldInfo info = new GrammarFoldManagerTask.FoldInfo(document, token.getStartIndex(), token.getStopIndex() + 1, blockHint);
                    folds.add(info);

                    break;

                default:
                    continue;
                }
            }
        }

        return folds;
    }

    private static FoldInfo createFold(GrammarAST child, String blockHint, StyledDocument document, CommonToken[] tokens) {
        CommonToken startToken = tokens[child.getTokenStartIndex()];
        CommonToken stopToken = tokens[child.getTokenStopIndex()];

        if (startToken.getType() == ANTLRParser.DOC_COMMENT) {
            for (int index = child.getTokenStartIndex(); index <= child.getTokenStopIndex(); index++) {
                startToken = tokens[index];
                if (startToken.getType() != ANTLRParser.DOC_COMMENT && startToken.getChannel() == Token.DEFAULT_CHANNEL) {
                    break;
                }
            }
        }

        int startLine = NbDocument.findLineNumber(document, startToken.getStartIndex());
        int stopLine = NbDocument.findLineNumber(document, stopToken.getStopIndex() + 1);
        if (startLine >= stopLine) {
            return null;
        }

        GrammarFoldManagerTask.FoldInfo fold = new GrammarFoldManagerTask.FoldInfo(document, startToken.getStartIndex(), stopToken.getStopIndex() + 1, blockHint);
        return fold;
    }

}
