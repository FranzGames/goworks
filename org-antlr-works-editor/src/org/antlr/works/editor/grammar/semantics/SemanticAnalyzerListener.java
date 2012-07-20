/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.semantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.netbeans.semantics.ObjectDecorator;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTree.ErrorNode;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ActionBlockContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ActionContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ActionExpressionContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ActionScopeExpressionContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ActionScopeNameContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.AltListContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.AlternativeContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ArgActionBlockContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ArgActionParameterContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ArgActionParameterTypeContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ArgActionParameterTypePartContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ArgActionParametersContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.AtomContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.BlockContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.BlockSetContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.BlockSuffixContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.DelegateGrammarContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.DelegateGrammarsContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.EbnfContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.EbnfSuffixContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ElementContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ElementOptionContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ElementOptionsContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ElementsContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ExceptionGroupContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ExceptionHandlerContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.FinallyClauseContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.GrammarSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.GrammarTypeContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.IdContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.IgnoredContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LabeledAltContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LabeledElementContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LabeledLexerElementContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerAltContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerAltListContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerAtomContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerBlockContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerCommandContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerCommandExprContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerCommandsContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerElementContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerElementsContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerRuleBlockContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LocalsSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ModeSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.NotSetContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.OptionContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.OptionValueContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.OptionsSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.PrequelConstructContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.QidContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RangeContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RuleActionContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RuleAltListContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RuleBlockContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RuleModifierContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RuleModifiersContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RulePrequelContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RulePrequelsContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RuleReturnsContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RuleSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RulerefContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RulesContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.SetElementContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.TerminalContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ThrowsSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.TokenSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.TokensSpecContext;
import org.antlr.works.editor.grammar.experimental.GrammarParserListener;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class SemanticAnalyzerListener implements GrammarParserListener {

    private final ObjectDecorator<Tree> treeDecorator;
    private final ObjectDecorator<Token> tokenDecorator;

    private final Map<String, Token> declaredRules = new HashMap<String, Token>();
    private final Map<String, Token> declaredModes = new HashMap<String, Token>();

    private final List<Token> unresolvedReferences = new ArrayList<Token>();
    private final List<Token> unresolvedModeReferences = new ArrayList<Token>();

    public SemanticAnalyzerListener(@NonNull ObjectDecorator<Tree> treeDecorator, @NonNull ObjectDecorator<Token> tokenDecorator) {
        Parameters.notNull("treeDecorator", treeDecorator);
        Parameters.notNull("tokenDecorator", tokenDecorator);
        this.treeDecorator = treeDecorator;
        this.tokenDecorator = tokenDecorator;
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleref, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_terminal, version=0),
    })
    public void visitTerminal(ParseTree.TerminalNode<? extends Token> node) {
        NodeType nodeType = treeDecorator.getProperty(node.getParent(), GrammarTreeProperties.PROP_NODE_TYPE);
        if (nodeType == NodeType.UNDEFINED) {
            nodeType = null;
        }

        Token symbol = node.getSymbol();
        int ruleIndex = node.getParent().getRuleContext().getRuleIndex();
        switch (symbol.getType()) {
        case GrammarParser.RULE_REF:
            switch (ruleIndex) {
            case GrammarParser.RULE_parserRuleSpec:
                nodeType = NodeType.RULE_DECL;
                declaredRules.put(symbol.getText(), symbol);
                break;

            case GrammarParser.RULE_ruleref:
                nodeType = NodeType.RULE_REF;
                unresolvedReferences.add(symbol);
                break;
            }
            break;

        case GrammarParser.TOKEN_REF:
            switch (ruleIndex) {
            case GrammarParser.RULE_lexerRule:
                nodeType = NodeType.TOKEN_DECL;
                declaredRules.put(symbol.getText(), symbol);
                break;

            case GrammarParser.RULE_terminal:
                nodeType = NodeType.TOKEN_REF;
                unresolvedReferences.add(symbol);
                break;
            }
            break;
        }

        if (nodeType != null) {
            tokenDecorator.putProperty(symbol, GrammarTreeProperties.PROP_NODE_TYPE, nodeType);
        }
    }

    @Override
    public void visitErrorNode(ErrorNode<? extends Token> node) {
        visitTerminal(node);
    }

    @Override
    public void enterEveryRule(ParserRuleContext<? extends Token> ctx) {
    }

    @Override
    public void exitEveryRule(ParserRuleContext<? extends Token> ctx) {
    }

    @Override
    public void enterParserRuleSpec(ParserRuleSpecContext ctx) {
    }

    @Override
    public void exitParserRuleSpec(ParserRuleSpecContext ctx) {
    }

    @Override
    public void enterAtom(AtomContext ctx) {
    }

    @Override
    public void exitAtom(AtomContext ctx) {
    }

    @Override
    public void enterRulePrequels(RulePrequelsContext ctx) {
    }

    @Override
    public void exitRulePrequels(RulePrequelsContext ctx) {
    }

    @Override
    public void enterRuleBlock(RuleBlockContext ctx) {
    }

    @Override
    public void exitRuleBlock(RuleBlockContext ctx) {
    }

    @Override
    public void enterNotSet(NotSetContext ctx) {
    }

    @Override
    public void exitNotSet(NotSetContext ctx) {
    }

    @Override
    public void enterLexerAltList(LexerAltListContext ctx) {
    }

    @Override
    public void exitLexerAltList(LexerAltListContext ctx) {
    }

    @Override
    public void enterArgActionParameter(ArgActionParameterContext ctx) {
    }

    @Override
    public void exitArgActionParameter(ArgActionParameterContext ctx) {
    }

    @Override
    public void enterRuleModifier(RuleModifierContext ctx) {
    }

    @Override
    public void exitRuleModifier(RuleModifierContext ctx) {
    }

    @Override
    public void enterRuleAltList(RuleAltListContext ctx) {
    }

    @Override
    public void exitRuleAltList(RuleAltListContext ctx) {
    }

    @Override
    public void enterTerminal(TerminalContext ctx) {
    }

    @Override
    public void exitTerminal(TerminalContext ctx) {
    }

    @Override
    public void enterThrowsSpec(ThrowsSpecContext ctx) {
    }

    @Override
    public void exitThrowsSpec(ThrowsSpecContext ctx) {
    }

    @Override
    public void enterAction(ActionContext ctx) {
    }

    @Override
    public void exitAction(ActionContext ctx) {
    }

    @Override
    public void enterActionScopeExpression(ActionScopeExpressionContext ctx) {
    }

    @Override
    public void exitActionScopeExpression(ActionScopeExpressionContext ctx) {
    }

    @Override
    public void enterLocalsSpec(LocalsSpecContext ctx) {
    }

    @Override
    public void exitLocalsSpec(LocalsSpecContext ctx) {
    }

    @Override
    public void enterModeSpec(ModeSpecContext ctx) {
    }

    @Override
    public void exitModeSpec(ModeSpecContext ctx) {
    }

    @Override
    public void enterElements(ElementsContext ctx) {
    }

    @Override
    public void exitElements(ElementsContext ctx) {
    }

    @Override
    public void enterOption(OptionContext ctx) {
    }

    @Override
    public void exitOption(OptionContext ctx) {
    }

    @Override
    public void enterElement(ElementContext ctx) {
    }

    @Override
    public void exitElement(ElementContext ctx) {
    }

    @Override
    public void enterElementOptions(ElementOptionsContext ctx) {
    }

    @Override
    public void exitElementOptions(ElementOptionsContext ctx) {
    }

    @Override
    public void enterLexerElement(LexerElementContext ctx) {
    }

    @Override
    public void exitLexerElement(LexerElementContext ctx) {
    }

    @Override
    public void enterAlternative(AlternativeContext ctx) {
    }

    @Override
    public void exitAlternative(AlternativeContext ctx) {
    }

    @Override
    public void enterLexerCommandExpr(LexerCommandExprContext ctx) {
    }

    @Override
    public void exitLexerCommandExpr(LexerCommandExprContext ctx) {
    }

    @Override
    public void enterGrammarType(GrammarTypeContext ctx) {
    }

    @Override
    public void exitGrammarType(GrammarTypeContext ctx) {
    }

    @Override
    public void enterRuleAction(RuleActionContext ctx) {
    }

    @Override
    public void exitRuleAction(RuleActionContext ctx) {
    }

    @Override
    public void enterEbnfSuffix(EbnfSuffixContext ctx) {
    }

    @Override
    public void exitEbnfSuffix(EbnfSuffixContext ctx) {
    }

    @Override
    public void enterExceptionGroup(ExceptionGroupContext ctx) {
    }

    @Override
    public void exitExceptionGroup(ExceptionGroupContext ctx) {
    }

    @Override
    public void enterRulePrequel(RulePrequelContext ctx) {
    }

    @Override
    public void exitRulePrequel(RulePrequelContext ctx) {
    }

    @Override
    public void enterLexerBlock(LexerBlockContext ctx) {
    }

    @Override
    public void exitLexerBlock(LexerBlockContext ctx) {
    }

    @Override
    public void enterSetElement(SetElementContext ctx) {
    }

    @Override
    public void exitSetElement(SetElementContext ctx) {
    }

    @Override
    public void enterBlockSet(BlockSetContext ctx) {
    }

    @Override
    public void exitBlockSet(BlockSetContext ctx) {
    }

    @Override
    public void enterActionScopeName(ActionScopeNameContext ctx) {
    }

    @Override
    public void exitActionScopeName(ActionScopeNameContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledAlt, version=1),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=0),
    })
    public void enterLabeledAlt(LabeledAltContext ctx) {
        if (ctx.getChildCount() == 3 && ctx.getChild(2) instanceof IdContext) {
            treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.ALT_LABEL);
        }
    }

    @Override
    public void exitLabeledAlt(LabeledAltContext ctx) {
    }

    @Override
    public void enterArgActionParameterType(ArgActionParameterTypeContext ctx) {
    }

    @Override
    public void exitArgActionParameterType(ArgActionParameterTypeContext ctx) {
    }

    @Override
    public void enterLexerAtom(LexerAtomContext ctx) {
    }

    @Override
    public void exitLexerAtom(LexerAtomContext ctx) {
    }

    @Override
    public void enterLabeledElement(LabeledElementContext ctx) {
    }

    @Override
    public void exitLabeledElement(LabeledElementContext ctx) {
    }

    @Override
    public void enterLexerRuleBlock(LexerRuleBlockContext ctx) {
    }

    @Override
    public void exitLexerRuleBlock(LexerRuleBlockContext ctx) {
    }

    @Override
    public void enterFinallyClause(FinallyClauseContext ctx) {
    }

    @Override
    public void exitFinallyClause(FinallyClauseContext ctx) {
    }

    @Override
    public void enterIgnored(IgnoredContext ctx) {
    }

    @Override
    public void exitIgnored(IgnoredContext ctx) {
    }

    @Override
    public void enterGrammarSpec(GrammarSpecContext ctx) {
    }

    @Override
    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0)
    public void exitGrammarSpec(GrammarSpecContext ctx) {
        for (Token token : unresolvedReferences) {
            String text = token.getText();
            if (text == null || text.isEmpty()) {
                continue;
            }

            Token decl = declaredRules.get(text);
            if (decl != null) {
                tokenDecorator.putProperty(token, GrammarTreeProperties.PROP_TARGET, decl);
            }
        }

        for (Token token : unresolvedModeReferences) {
            String text = token.getText();
            if (text == null || text.isEmpty()) {
                continue;
            }

            Token decl = declaredModes.get(text);
            if (decl != null) {
                tokenDecorator.putProperty(token, GrammarTreeProperties.PROP_TARGET, decl);
            }
        }
    }

    @Override
    public void enterDelegateGrammar(DelegateGrammarContext ctx) {
    }

    @Override
    public void exitDelegateGrammar(DelegateGrammarContext ctx) {
    }

    @Override
    public void enterLexerElements(LexerElementsContext ctx) {
    }

    @Override
    public void exitLexerElements(LexerElementsContext ctx) {
    }

    @Override
    public void enterRange(RangeContext ctx) {
    }

    @Override
    public void exitRange(RangeContext ctx) {
    }

    @Override
    public void enterTokenSpec(TokenSpecContext ctx) {
    }

    @Override
    public void exitTokenSpec(TokenSpecContext ctx) {
    }

    @Override
    public void enterBlock(BlockContext ctx) {
    }

    @Override
    public void exitBlock(BlockContext ctx) {
    }

    @Override
    public void enterArgActionParameterTypePart(ArgActionParameterTypePartContext ctx) {
    }

    @Override
    public void exitArgActionParameterTypePart(ArgActionParameterTypePartContext ctx) {
    }

    @Override
    public void enterLexerRule(LexerRuleContext ctx) {
    }

    @Override
    public void exitLexerRule(LexerRuleContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_labeledLexerElement, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=0),
    })
    public void enterLabeledLexerElement(LabeledLexerElementContext ctx) {
        if (ctx.getChildCount() == 0 || !(ctx.getChild(0) instanceof IdContext)) {
            return;
        }

        treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.LABEL_DECL);
    }

    @Override
    public void exitLabeledLexerElement(LabeledLexerElementContext ctx) {
    }

    @Override
    public void enterDelegateGrammars(DelegateGrammarsContext ctx) {
    }

    @Override
    public void exitDelegateGrammars(DelegateGrammarsContext ctx) {
    }

    @Override
    public void enterActionExpression(ActionExpressionContext ctx) {
    }

    @Override
    public void exitActionExpression(ActionExpressionContext ctx) {
    }

    @Override
    public void enterRuleref(RulerefContext ctx) {
    }

    @Override
    public void exitRuleref(RulerefContext ctx) {
    }

    @Override
    public void enterBlockSuffix(BlockSuffixContext ctx) {
    }

    @Override
    public void exitBlockSuffix(BlockSuffixContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_grammarSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_modeSpec, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommand, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommandExpr, version=1),
    })
    public void enterId(IdContext ctx) {
        if (ctx.start != null && ctx.parent != null) {

            int caller = ctx.parent.getRuleIndex();
            switch (caller) {
            case GrammarParser.RULE_grammarSpec:
                tokenDecorator.putProperty(ctx.start, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.GRAMMAR_DECL);
                break;

            case GrammarParser.RULE_modeSpec:
                declaredModes.put(ctx.start.getText(), ctx.start);
                tokenDecorator.putProperty(ctx.start, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.MODE_DECL);
                break;

            case GrammarParser.RULE_lexerCommandExpr:
                assert ctx.getParent().getParent() instanceof LexerCommandContext;
                if (ctx.getParent().getParent() instanceof LexerCommandContext) {
                    LexerCommandContext commandContext = (LexerCommandContext)ctx.getParent().getParent();
                    IdContext command = commandContext.id();
                    if (command != null && command.start != null) {
                        if ("pushMode".equals(command.start.getText()) || "mode".equals(command.start.getText())) {
                            unresolvedModeReferences.add(ctx.start);
                            tokenDecorator.putProperty(ctx.start, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.MODE_REF);
                        } else if ("type".equals(command.start.getText())
                            && Character.isUpperCase(ctx.start.getText().charAt(0))) {
                            unresolvedReferences.add(ctx.start);
                            tokenDecorator.putProperty(ctx.start, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.TOKEN_REF);
                        }
                    }
                }
                break;
            }

        }
    }

    @Override
    public void exitId(IdContext ctx) {
    }

    @Override
    public void enterActionBlock(ActionBlockContext ctx) {
    }

    @Override
    public void exitActionBlock(ActionBlockContext ctx) {
    }

    @Override
    public void enterElementOption(ElementOptionContext ctx) {
    }

    @Override
    public void exitElementOption(ElementOptionContext ctx) {
    }

    @Override
    public void enterRuleSpec(RuleSpecContext ctx) {
    }

    @Override
    public void exitRuleSpec(RuleSpecContext ctx) {
    }

    @Override
    public void enterExceptionHandler(ExceptionHandlerContext ctx) {
    }

    @Override
    public void exitExceptionHandler(ExceptionHandlerContext ctx) {
    }

    @Override
    public void enterTokensSpec(TokensSpecContext ctx) {
    }

    @Override
    public void exitTokensSpec(TokensSpecContext ctx) {
    }

    @Override
    public void enterRuleReturns(RuleReturnsContext ctx) {
    }

    @Override
    public void exitRuleReturns(RuleReturnsContext ctx) {
    }

    @Override
    public void enterQid(QidContext ctx) {
    }

    @Override
    public void exitQid(QidContext ctx) {
    }

    @Override
    public void enterOptionsSpec(OptionsSpecContext ctx) {
    }

    @Override
    public void exitOptionsSpec(OptionsSpecContext ctx) {
    }

    @Override
    public void enterAltList(AltListContext ctx) {
    }

    @Override
    public void exitAltList(AltListContext ctx) {
    }

    @Override
    public void enterPrequelConstruct(PrequelConstructContext ctx) {
    }

    @Override
    public void exitPrequelConstruct(PrequelConstructContext ctx) {
    }

    @Override
    public void enterRules(RulesContext ctx) {
    }

    @Override
    public void exitRules(RulesContext ctx) {
    }

    @Override
    public void enterLexerCommands(LexerCommandsContext ctx) {
    }

    @Override
    public void exitLexerCommands(LexerCommandsContext ctx) {
    }

    @Override
    public void enterOptionValue(OptionValueContext ctx) {
    }

    @Override
    public void exitOptionValue(OptionValueContext ctx) {
    }

    @Override
    public void enterLexerAlt(LexerAltContext ctx) {
    }

    @Override
    public void exitLexerAlt(LexerAltContext ctx) {
    }

    @Override
    public void enterArgActionBlock(ArgActionBlockContext ctx) {
    }

    @Override
    public void exitArgActionBlock(ArgActionBlockContext ctx) {
    }

    @Override
    @RuleDependencies({
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerCommand, version=0),
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_id, version=0),
    })
    public void enterLexerCommand(LexerCommandContext ctx) {
        if (ctx.getChildCount() == 0 || !(ctx.getChild(0) instanceof IdContext)) {
            return;
        }

        treeDecorator.putProperty(ctx, GrammarTreeProperties.PROP_NODE_TYPE, NodeType.LEXER_ACTION);
    }

    @Override
    public void exitLexerCommand(LexerCommandContext ctx) {
    }

    @Override
    public void enterRuleModifiers(RuleModifiersContext ctx) {
    }

    @Override
    public void exitRuleModifiers(RuleModifiersContext ctx) {
    }

    @Override
    public void enterArgActionParameters(ArgActionParametersContext ctx) {
    }

    @Override
    public void exitArgActionParameters(ArgActionParametersContext ctx) {
    }

    @Override
    public void enterEbnf(EbnfContext ctx) {
    }

    @Override
    public void exitEbnf(EbnfContext ctx) {
    }

}
