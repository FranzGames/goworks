/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.IdContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerCommandNameContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.GrammarParserBaseVisitor;

/**
 * Token types are suppressed for fragment rules and for rules containing
 * the lexer commands {@code more}, {@code skip}, and/or {@code type}.
 */
public class SuppressTokenTypeVisitor extends GrammarParserBaseVisitor<Boolean> {
    public static final SuppressTokenTypeVisitor INSTANCE = new SuppressTokenTypeVisitor();

    @Override
    protected Boolean defaultResult() {
        return false;
    }

    @Override
    protected Boolean aggregateResult(Boolean aggregate, Boolean nextResult) {
        return aggregate || nextResult;
    }

    @Override
    protected boolean shouldVisitNextChild(RuleNode<? extends Token> node, Boolean currentResult) {
        // have to stop when we reach true
        return !currentResult;
    }

    @Override
    public Boolean visitLexerRule(LexerRuleContext ctx) {
        if (ctx.FRAGMENT() != null) {
            return true;
        }
        return super.visitLexerRule(ctx);
    }

    @Override
    public Boolean visitLexerCommandName(LexerCommandNameContext ctx) {
        IdContext id = ctx.id();
        if (id != null && id.start != null) {
            String command = id.start.getText();
            if ("type".equals(command) || "more".equals(command) || "skip".equals(command)) {
                return true;
            }
        }
        return super.visitLexerCommandName(ctx);
    }

}
