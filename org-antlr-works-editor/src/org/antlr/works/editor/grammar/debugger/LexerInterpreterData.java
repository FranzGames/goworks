/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.debugger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.v4.Tool;
import org.antlr.v4.misc.Utils;
import org.antlr.v4.runtime.atn.ATNSerializer;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.GrammarRootAST;
import org.antlr.works.editor.grammar.debugger.LexerDebuggerControllerTopComponent.TokenDescriptor;

/**
 *
 * @author Sam Harwell
 */
public class LexerInterpreterData {

    public String grammarFileName;
    public String serializedAtn;
    public List<TokenDescriptor> tokenNames;
    public List<String> ruleNames;
    public List<String> modeNames;

    public static LexerInterpreterData buildFromSnapshot(DocumentSnapshot snapshot) {
        Tool tool = new TracingTool();

        ANTLRStringStream stream = new ANTLRStringStream(snapshot.getText());
        stream.name = snapshot.getVersionedDocument().getFileObject().getNameExt();
        GrammarRootAST ast = tool.parse(stream.name, stream);
        Grammar grammar = tool.createGrammar(ast);
        tool.process(grammar, false);

        LexerGrammar lexerGrammar;
        if (grammar instanceof LexerGrammar) {
            lexerGrammar = (LexerGrammar)grammar;
        } else {
            lexerGrammar = grammar.implicitLexer;
        }

        if (lexerGrammar == null) {
            return null;
        }

        LexerInterpreterData data = new LexerInterpreterData();
        data.grammarFileName = lexerGrammar.fileName;
        data.serializedAtn = ATNSerializer.getSerializedAsString(lexerGrammar.atn, Arrays.asList(lexerGrammar.getRuleNames()));
        data.tokenNames = new ArrayList<>(Arrays.asList(getTokenNames(lexerGrammar)));
        data.ruleNames = new ArrayList<>(lexerGrammar.rules.keySet());
        data.modeNames = new ArrayList<>(lexerGrammar.modes.keySet());
        return data;
    }

    public static TokenDescriptor[] getTokenNames(LexerGrammar grammar) {
        int numTokens = grammar.getMaxTokenType();
        List<String> typeToStringLiteralList = new ArrayList<>(grammar.typeToStringLiteralList);
        Utils.setSize(typeToStringLiteralList, numTokens + 1);
        for (Map.Entry<String, Integer> entry : grammar.stringLiteralToTypeMap.entrySet()) {
            if (entry.getValue() < 0 || entry.getValue() >= typeToStringLiteralList.size()) {
                continue;
            }

            typeToStringLiteralList.set(entry.getValue(), entry.getKey());
        }

        TokenDescriptor[] tokenNames = new TokenDescriptor[numTokens+1];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = new TokenDescriptor();
        }

        for (String tokenName : grammar.tokenNameToTypeMap.keySet()) {
            Integer ttype = grammar.tokenNameToTypeMap.get(tokenName);
            if (ttype < 0 || ttype >= tokenNames.length) {
                continue;
            }

            if ( tokenName!=null && tokenName.startsWith(Grammar.AUTO_GENERATED_TOKEN_NAME_PREFIX) ) {
                if (ttype < typeToStringLiteralList.size()) {
                    String literal = typeToStringLiteralList.get(ttype);
                    if (literal != null) {
                        tokenNames[ttype].literal = literal;
                    }
                }
            }

            tokenNames[ttype].name = tokenName;
            tokenNames[ttype].value = ttype;
        }

        return tokenNames;
    }

}
