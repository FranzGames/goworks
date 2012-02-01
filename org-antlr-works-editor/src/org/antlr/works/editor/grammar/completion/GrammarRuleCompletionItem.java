/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.completion;

import javax.swing.ImageIcon;
import org.antlr.netbeans.editor.navigation.Description;
import org.antlr.works.editor.grammar.navigation.GrammarNode;

/**
 *
 * @author Sam Harwell
 */
public class GrammarRuleCompletionItem extends GrammarCompletionItem {
    private static final ImageIcon PARSER_ICON;
    private static final ImageIcon LEXER_ICON;

    private final Description rule;

    private String leftText;

    static {
        PARSER_ICON = new ImageIcon(GrammarNode.PARSER_IMAGE);
        LEXER_ICON = new ImageIcon(GrammarNode.LEXER_IMAGE);
    }

    public GrammarRuleCompletionItem(Description rule) {
        this.rule = rule;
    }

    @Override
    public int getSortPriority() {
        return RULE_SORT_PRIORITY;
    }

    @Override
    public String getSortTextImpl() {
        return rule.getName();
    }

    @Override
    public CharSequence getInsertPrefix() {
        return rule.getName();
    }

    @Override
    protected ImageIcon getIcon() {
        String name = rule.getName();
        if (Character.isLowerCase(name.charAt(0))) {
            return PARSER_ICON;
        } else {
            return LEXER_ICON;
        }
    }

    @Override
    protected String getLeftHtmlText() {
        if (leftText == null) {
            StringBuilder builder = new StringBuilder();

            builder.append(METHOD_COLOR);

            if (!rule.isInherited()) {
                builder.append(BOLD);
            }

            builder.append(rule.getName());

            if (!rule.isInherited()) {
                builder.append(BOLD_END);
            }

            builder.append(COLOR_END);
            
            leftText = builder.toString();
        }

        return leftText;
    }

}
