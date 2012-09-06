/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.IdContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LabeledLexerElementContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerAltContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerAltListContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerAtomContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerCommandNameContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerElementContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerElementsContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.TerminalContext;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.GrammarParserBaseVisitor;

/**
 * Literal lexer rules are non-fragment rules that contain a single string
 * literal, and optionally lexer action(s) which do not include {@code more},
 * {@code skip}, or {@code type}.
 */
public class LiteralLexerRuleVisitor extends GrammarParserBaseVisitor<Boolean> {
    public static final LiteralLexerRuleVisitor INSTANCE = new LiteralLexerRuleVisitor();

    @Override
    protected Boolean defaultResult() {
        return true;
    }

    @Override
    protected Boolean aggregateResult(Boolean aggregate, Boolean nextResult) {
        return aggregate && nextResult;
    }

    @Override
    protected boolean shouldVisitNextChild(RuleNode<? extends Token> node, Boolean currentResult) {
        // have to stop when we reach false
        return currentResult;
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0)
    public Boolean visitLexerRule(LexerRuleContext ctx) {
        if (ctx.FRAGMENT() != null) {
            return false;
        }
        return super.visitLexerRule(ctx);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAltList, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAlt, version=1),
    })
    public Boolean visitLexerAltList(LexerAltListContext ctx) {
        if (ctx.lexerAlt().size() != 1) {
            return false;
        }
        return super.visitLexerAltList(ctx);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAlt, version=1),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerElements, version=0),
    })
    public Boolean visitLexerAlt(LexerAltContext ctx) {
        if (ctx.lexerElements() == null) {
            return false;
        }
        return super.visitLexerAlt(ctx);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerElements, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerElement, version=0),
    })
    public Boolean visitLexerElements(LexerElementsContext ctx) {
        if (ctx.lexerElement().size() != 1) {
            return false;
        }
        return super.visitLexerElements(ctx);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerElement, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAtom, version=1),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledLexerElement, version=0),
    })
    public Boolean visitLexerElement(LexerElementContext ctx) {
        if (ctx.lexerAtom() == null && ctx.labeledLexerElement() == null) {
            return false;
        }
        return super.visitLexerElement(ctx);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledLexerElement, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAtom, version=1),
    })
    public Boolean visitLabeledLexerElement(LabeledLexerElementContext ctx) {
        // if the element is labeled, it must be a lexerAtom
        if (ctx.lexerAtom() == null) {
            return false;
        }
        return super.visitLabeledLexerElement(ctx);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAtom, version=1),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_terminal, version=0),
    })
    public Boolean visitLexerAtom(LexerAtomContext ctx) {
        if (ctx.terminal() == null) {
            return false;
        }
        return super.visitLexerAtom(ctx);
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_terminal, version=0)
    public Boolean visitTerminal(TerminalContext ctx) {
        if (ctx.STRING_LITERAL() == null) {
            return false;
        }
        return super.visitTerminal(ctx);
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandName, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=1),
    })
    public Boolean visitLexerCommandName(LexerCommandNameContext ctx) {
        IdContext id = ctx.id();
        if (id != null && id.start != null) {
            String command = id.start.getText();
            if ("type".equals(command) || "more".equals(command) || "skip".equals(command)) {
                return false;
            }
        }
        return super.visitLexerCommandName(ctx);
    }

}
