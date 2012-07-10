/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.syndiag;

import java.awt.Component;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.netbeans.editor.text.OffsetRegion;
import org.antlr.netbeans.editor.text.SnapshotPositionRegion;
import org.antlr.netbeans.parsing.spi.ParserData;
import org.antlr.netbeans.parsing.spi.ParserDataEvent;
import org.antlr.netbeans.parsing.spi.ParserDataListener;
import org.antlr.netbeans.parsing.spi.ParserTaskManager;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleDependencies;
import org.antlr.v4.runtime.RuleDependency;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.works.editor.grammar.GrammarParserDataDefinitions;
import org.antlr.works.editor.grammar.experimental.CurrentRuleContextData;
import org.antlr.works.editor.grammar.experimental.GrammarParser;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.AltListContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.AlternativeContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.AtomContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.EbnfSuffixContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerAltContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerAltListContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerAtomContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerBlockContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.LexerRuleContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.ParserRuleSpecContext;
import org.antlr.works.editor.grammar.experimental.AbstractGrammarParser.RuleAltListContext;
import org.antlr.works.editor.grammar.experimental.GrammarParserBaseListener;
import org.netbeans.api.annotations.common.NullAllowed;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.Parameters;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 *
 * @author Sam Harwell
 */
@ConvertAsProperties(dtd = "-//org.antlr.works.editor.grammar.syndiag//SyntaxDiagram//EN",
                     autostore = false)
@TopComponent.Description(preferredID = "SyntaxDiagramTopComponent",
                          //iconBase="SET/PATH/TO/ICON/HERE",
                          persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "bottomSlidingSide", openAtStartup = true)
@ActionID(category = "Window", id = "org.antlr.works.editor.grammar.syndiag.SyntaxDiagramTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_SyntaxDiagramAction",
                                     preferredID = "SyntaxDiagramTopComponent")
@NbBundle.Messages({
    "CTL_SyntaxDiagramAction=Syntax Diagram",
    "CTL_SyntaxDiagramTopComponent=Syntax Diagram",
    "HINT_SyntaxDiagramTopComponent=Grammar Rule Syntax Diagram"
})
public final class SyntaxDiagramTopComponent extends TopComponent {

    private DocumentSnapshot snapshot;
    private GrammarParser.RuleSpecContext context;
    private Diagram diagram;

    public SyntaxDiagramTopComponent() {
        initComponents();
        setName(Bundle.CTL_SyntaxDiagramTopComponent());
        setToolTipText(Bundle.HINT_SyntaxDiagramTopComponent());

        ParserTaskManager taskManager = Lookup.getDefault().lookup(ParserTaskManager.class);
        taskManager.addDataListener(GrammarParserDataDefinitions.CURRENT_RULE_CONTEXT, new CurrentRuleContextListener());
    }

    public static SyntaxDiagramTopComponent getInstance() {
        return (SyntaxDiagramTopComponent)WindowManager.getDefault().findTopComponent("SyntaxDiagramTopComponent");
    }

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0)
    public void setRuleContext(@NullAllowed CurrentRuleContextData context) {
        if (!SwingUtilities.isEventDispatchThread()) {
            throw new UnsupportedOperationException();
        }

        if (context == null) {
            clearDiagram();
            return;
        }

        GrammarParser.RuleSpecContext ruleSpecContext = context.getContext();
        if (isSameSnapshot(this.snapshot, snapshot) && isSameContext(this.context, ruleSpecContext)) {
            return;
        }

        if (diagram != null) {
            this.jScrollPane1.setViewportView(null);
            diagram = null;
        }

        this.snapshot = context.getSnapshot();
        this.context = ruleSpecContext;
        if (ruleSpecContext != null) {
            try {
                SyntaxBuilderListener listener = new SyntaxBuilderListener(context.getGrammarType(), snapshot);
                new ParseTreeWalker().walk(listener, ruleSpecContext);
                this.diagram = new Diagram(listener.getRule());
                this.jScrollPane1.setViewportView(diagram);
                this.jScrollPane1.validate();
                this.diagram.getRule().updatePositions();
            } catch (NullPointerException ex) {
                clearDiagram();
            } catch (IllegalArgumentException ex) {
                clearDiagram();
            }
        }
    }

    private void clearDiagram() {
        if (jScrollPane1 != null) {
            this.jScrollPane1.setViewportView(null);
        }

        this.diagram = null;
    }

    private static boolean isSameSnapshot(DocumentSnapshot a, DocumentSnapshot b) {
        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return a.equals(b);
    }

    @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleSpec, version=0)
    private static boolean isSameContext(GrammarParser.RuleSpecContext a, GrammarParser.RuleSpecContext b) {
        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return isSameToken(a.start, b.start) && isSameToken(a.stop, b.stop);
    }

    private static boolean isSameToken(Token a, Token b) {
        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return a.getStartIndex() == b.getStartIndex()
            && a.getStopIndex() == b.getStopIndex();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();

        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(Properties properties) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        properties.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(Properties properties) {
        String version = properties.getProperty("version");
        // TODO read your settings according to their version
    }

    private class CurrentRuleContextListener implements ParserDataListener<CurrentRuleContextData> {

        @Override
        public void dataChanged(ParserDataEvent<? extends CurrentRuleContextData> event) {
            ParserData<? extends CurrentRuleContextData> parserData = event.getData();
            final CurrentRuleContextData RuleSpecContext = parserData.getData();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    SyntaxDiagramTopComponent syntaxDiagram = SyntaxDiagramTopComponent.getInstance();
                    if (syntaxDiagram != null) {
                        syntaxDiagram.setRuleContext(RuleSpecContext);
                    }
                }
            });
        }

    }

    private static class SyntaxBuilderListener extends GrammarParserBaseListener {

        private final int grammarType;
        private final DocumentSnapshot snapshot;
        private final Deque<JComponent> nodes = new ArrayDeque<JComponent>();

        private Rule RuleSpec;
        private ParserRuleContext<Token> outermostAtom;

        public SyntaxBuilderListener(int grammarType, DocumentSnapshot snapshot) {
            Parameters.notNull("snapshot", snapshot);
            this.grammarType = grammarType;
            this.snapshot = snapshot;
        }

        public Rule getRule() {
            return RuleSpec;
        }

        /*
         * RuleSpec is the top level context
         */

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0)
        public void enterParserRuleSpec(ParserRuleSpecContext ctx) {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            Rule RuleSpec = new Rule(ctx.name.getText());
            this.RuleSpec = RuleSpec;
            nodes.push(RuleSpec);
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_parserRuleSpec, version=0)
        public void exitParserRuleSpec(ParserRuleSpecContext ctx) {
            assert nodes.size() == 1;
            this.RuleSpec = (Rule)nodes.pop();
//            this.RuleSpec.setupElements();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0)
        public void enterLexerRule(LexerRuleContext ctx) {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            Rule RuleSpec = new Rule(ctx.name.getText());
            this.RuleSpec = RuleSpec;
            nodes.push(RuleSpec);
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerRule, version=0)
        public void exitLexerRule(LexerRuleContext ctx) {
            assert nodes.size() == 1;
            this.RuleSpec = (Rule)nodes.pop();
//            this.RuleSpec.setupElements();
        }

        /*
         * RuleSpecAltList and altList form the true body of a Block
         */

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleAltList, version=0)
        public void enterRuleAltList(RuleAltListContext ctx) {
            enterBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_altList, version=0)
        public void enterAltList(AltListContext ctx) {
            enterBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAltList, version=0)
        public void enterLexerAltList(LexerAltListContext ctx) {
            enterBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerBlock, version=0)
        public void enterLexerBlock(LexerBlockContext ctx) {
            enterBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleAltList, version=0)
        public void exitRuleAltList(RuleAltListContext ctx) {
            exitBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_altList, version=0)
        public void exitAltList(AltListContext ctx) {
            exitBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAltList, version=0)
        public void exitLexerAltList(LexerAltListContext ctx) {
            exitBlock();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerBlock, version=0)
        public void exitLexerBlock(LexerBlockContext ctx) {
            exitBlock();
        }

        /*
         * alternative (parser) and lexerAlt (lexer) are fairly straightforward
         */

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_alternative, version=0)
        public void enterAlternative(AlternativeContext ctx) {
            enterAlternative();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAlt, version=0)
        public void enterLexerAlt(LexerAltContext ctx) {
            enterAlternative();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_alternative, version=0)
        public void exitAlternative(AlternativeContext ctx) {
            exitAlternative();
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAlt, version=0)
        public void exitLexerAlt(LexerAltContext ctx) {
            exitAlternative();
        }

        /*
         * TODO: handle special actions and label sections similarly (was rewrites)
         */

        /*
         * Actual elements (atoms only for a test)
         */

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAtom, version=0)
        public void enterLexerAtom(LexerAtomContext ctx) {
            enterEveryAtom(ctx);
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_atom, version=0)
        public void enterAtom(AtomContext ctx) {
            enterEveryAtom(ctx);
        }

        @RuleDependencies({
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_terminal, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ruleref, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_range, version=0),
            @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_notSet, version=0),
        })
        public void enterEveryAtom(ParserRuleContext<Token> ctx) {
            if (outermostAtom != null) {
                return;
            }

            outermostAtom = ctx;
            SnapshotPositionRegion sourceSpan = null;
            if (ctx.stop != null) {
                sourceSpan = new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(ctx.start.getStartIndex(), ctx.stop.getStopIndex() + 1));
            } else if (ctx.start != null) {
                sourceSpan = new SnapshotPositionRegion(snapshot, OffsetRegion.fromBounds(ctx.start.getStartIndex(), ctx.start.getStopIndex() + 1));
            }

            boolean wildcard = ctx.start.getType() == GrammarParser.DOT;
            boolean hasChild = ctx.children != null && !ctx.children.isEmpty();
            boolean reference = hasChild
                && (ctx.children.get(0) instanceof GrammarParser.TerminalContext
                || ctx.children.get(0) instanceof GrammarParser.RulerefContext);
            boolean range = hasChild && ctx.children.get(0) instanceof GrammarParser.RangeContext;
            boolean notset = hasChild && ctx.children.get(0) instanceof GrammarParser.NotSetContext;

            if (wildcard || reference) {
                String text = ctx.start.getText();
                boolean nonTerminal = Character.isLowerCase(text.charAt(0));
                if (!nonTerminal && Character.isUpperCase(text.charAt(0)) && RuleSpec != null && Character.isUpperCase(RuleSpec.getRuleName().charAt(0)))
                    nonTerminal = true;

                if (nonTerminal) {
                    nodes.peek().add(new NonTerminal(text, sourceSpan));
                } else {
                    nodes.peek().add(new Terminal(text, sourceSpan));
                }
            } else if (range) {
                String label = "???";
                Terminal terminal = null;
                GrammarParser.RangeContext rangeContext = (GrammarParser.RangeContext)ctx.children.get(0);
                if (rangeContext.children != null && rangeContext.children.size() == 3) {
                    Token start = ((ParseTree.TerminalNode<Token>)rangeContext.children.get(0)).getSymbol();
                    Token end = ((ParseTree.TerminalNode<Token>)rangeContext.children.get(2)).getSymbol();
                    if (start != null && end != null) {
                        terminal = new RangeTerminal(start.getText(), end.getText(), sourceSpan);
                    }
                }

                if (terminal == null) {
                    terminal = new Terminal(label, sourceSpan);
                }

                nodes.peek().add(terminal);
            } else if (notset) {
                nodes.peek().add(new Terminal("~(...)", sourceSpan));
            } else {
                nodes.peek().add(new Terminal("???", sourceSpan));
            }
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_lexerAtom, version=0)
        public void exitLexerAtom(LexerAtomContext ctx) {
            if (outermostAtom == ctx) {
                outermostAtom = null;
            }
        }

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_atom, version=0)
        public void exitAtom(AtomContext ctx) {
            if (outermostAtom == ctx) {
                outermostAtom = null;
            }
        }

        /*
         * suffix
         */

        @Override
        @RuleDependency(recognizer=GrammarParser.class, rule=GrammarParser.RULE_ebnfSuffix, version=0)
        public void enterEbnfSuffix(EbnfSuffixContext ctx) {
            Block block;

            switch (ctx.start.getType()) {
            case GrammarParser.QUESTION:
                {
                    block = new Block();
                    JComponent last = nodes.peek();
                    Component lastChild = last.getComponent(last.getComponentCount() - 1);
                    last.remove(last.getComponentCount() - 1);
                    Alt alt = new Alt();
                    alt.add(lastChild);
                    block.add(alt);
                    block.add(new Alt());
                    last.add(block);
                    break;
                }

            case GrammarParser.STAR:
            case GrammarParser.PLUS:
                {
                    block = new PlusBlock();
                    JComponent last = nodes.peek();
                    Component lastChild = last.getComponent(last.getComponentCount() - 1);
                    last.remove(last.getComponentCount() - 1);
                    Alt alt = new Alt();
                    alt.add(lastChild);
                    block.add(alt);
                    if (ctx.start.getType() == GrammarParser.STAR) {
                        Block optionalBlock = new Block();
                        alt = new Alt();
                        alt.add(block);
                        optionalBlock.add(alt);
                        optionalBlock.add(new Alt());
                        block = optionalBlock;
                    }

                    last.add(block);
                    break;
                }

            default:
                break;
            }
        }

        /*
         * helper methods
         */

        private void enterBlock() {
            if (outermostAtom != null) {
                return;
            }

            Block block = new Block();
            nodes.push(block);
        }

        private void exitBlock() {
            if (outermostAtom != null) {
                return;
            }

            Component block = nodes.pop();
            nodes.peek().add(block);
        }

        private void enterAlternative() {
            if (outermostAtom != null) {
                return;
            }

            Alt alternative = new Alt();
            nodes.push(alternative);
        }

        private void exitAlternative() {
            if (outermostAtom != null) {
                return;
            }

            Component alternative = nodes.pop();
            nodes.peek().add(alternative);
        }

    }

}
