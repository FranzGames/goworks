/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.formatting;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import org.antlr.netbeans.editor.formatting.CategorySupport;
import org.netbeans.modules.options.editor.spi.PreferencesCustomizer;
import org.openide.util.NbBundle;
import org.tvl.goworks.editor.GoEditorKit;

/**
 *
 * @author Sam Harwell
 */
@NbBundle.Messages({
    "SAMPLE_Wrapping="
})
@SuppressWarnings("rawtypes")
public class FormatWrapping extends javax.swing.JPanel implements FocusListener {

    /**
     * Creates new form FormatWrapping
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public FormatWrapping() {
        initComponents();

        scrollPane.getViewport().setBackground(java.awt.SystemColor.controlLtHighlight);

        cmbArrayInitializer.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapArrayInitializer);
        cmbArrayInitializer.addFocusListener(this);
        cmbAssignmentOperators.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapAssignmentOperators);
        cmbAssignmentOperators.addFocusListener(this);
        cmbBinaryOperators.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapBinaryOperators);
        cmbBinaryOperators.addFocusListener(this);
        cmbChainedMethodCalls.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapChainedMethodCalls);
        cmbChainedMethodCalls.addFocusListener(this);
        cmbDoWhileStatement.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapDoWhileStatement);
        cmbDoWhileStatement.addFocusListener(this);
        cmbEnumConstants.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapEnumConstants);
        cmbEnumConstants.addFocusListener(this);
        cmbFor.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapFor);
        cmbFor.addFocusListener(this);
        cmbForStatement.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapForStatement);
        cmbForStatement.addFocusListener(this);
        cmbIfStatement.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapIfStatement);
        cmbIfStatement.addFocusListener(this);
        cmbMethodCallArguments.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapMethodCallArguments);
        cmbMethodCallArguments.addFocusListener(this);
        cmbMethodParameters.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapMethodParameters);
        cmbMethodParameters.addFocusListener(this);
        cmbTernaryOperators.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapTernaryOperators);
        cmbTernaryOperators.addFocusListener(this);
        cmbWhileStatement.putClientProperty(CategorySupport.OPTION_ID, GoFormatOptions.wrapWhileStatement);
        cmbWhileStatement.addFocusListener(this);
    }

    public static PreferencesCustomizer.Factory getController() {
        return new CategorySupport.Factory(GoEditorKit.GO_MIME_TYPE, "wrapping", FormatWrapping.class, //NOI18N
                Bundle.SAMPLE_Wrapping(), GoPreviewFormatter.INSTANCE);
    }

    @Override
    public void focusGained(FocusEvent e) {
        scrollPane.getViewport().scrollRectToVisible(e.getComponent().getBounds());
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrollPane = new javax.swing.JScrollPane();
        panel1 = new javax.swing.JPanel();
        lblMethodParameters = new javax.swing.JLabel();
        cmbMethodParameters = new javax.swing.JComboBox();
        lblMethodCallArguments = new javax.swing.JLabel();
        cmbMethodCallArguments = new javax.swing.JComboBox();
        lblChainedMethodCalls = new javax.swing.JLabel();
        cmbChainedMethodCalls = new javax.swing.JComboBox();
        lblArrayInitializer = new javax.swing.JLabel();
        cmbArrayInitializer = new javax.swing.JComboBox();
        lblFor = new javax.swing.JLabel();
        cmbFor = new javax.swing.JComboBox();
        lblForStatement = new javax.swing.JLabel();
        cmbForStatement = new javax.swing.JComboBox();
        lblIfStatement = new javax.swing.JLabel();
        cmbIfStatement = new javax.swing.JComboBox();
        lblWhileStatement = new javax.swing.JLabel();
        cmbWhileStatement = new javax.swing.JComboBox();
        lblDoWhileStatement = new javax.swing.JLabel();
        cmbDoWhileStatement = new javax.swing.JComboBox();
        lblEnumConstants = new javax.swing.JLabel();
        cmbEnumConstants = new javax.swing.JComboBox();
        lblBinaryOperators = new javax.swing.JLabel();
        cmbBinaryOperators = new javax.swing.JComboBox();
        lblTernaryOperators = new javax.swing.JLabel();
        cmbTernaryOperators = new javax.swing.JComboBox();
        lblAssignmentOperators = new javax.swing.JLabel();
        cmbAssignmentOperators = new javax.swing.JComboBox();

        setName(org.openide.util.NbBundle.getMessage(FormatWrapping.class, "LBL_Wrapping")); // NOI18N
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        scrollPane.setMinimumSize(new java.awt.Dimension(212, 300));
        scrollPane.setPreferredSize(new java.awt.Dimension(240, 500));

        panel1.setOpaque(false);
        panel1.setLayout(new java.awt.GridBagLayout());

        lblMethodParameters.setText("Method Parameters:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 4, 0);
        panel1.add(lblMethodParameters, gridBagConstraints);

        cmbMethodParameters.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 6, 4, 8);
        panel1.add(cmbMethodParameters, gridBagConstraints);

        lblMethodCallArguments.setText("Method Call Arguments:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblMethodCallArguments, gridBagConstraints);

        cmbMethodCallArguments.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbMethodCallArguments, gridBagConstraints);

        lblChainedMethodCalls.setText("Chained Method Calls:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblChainedMethodCalls, gridBagConstraints);

        cmbChainedMethodCalls.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbChainedMethodCalls, gridBagConstraints);

        lblArrayInitializer.setText("Array Initializer:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblArrayInitializer, gridBagConstraints);

        cmbArrayInitializer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbArrayInitializer, gridBagConstraints);

        lblFor.setText("For:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblFor, gridBagConstraints);

        cmbFor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbFor, gridBagConstraints);

        lblForStatement.setText("For Statement:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblForStatement, gridBagConstraints);

        cmbForStatement.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbForStatement, gridBagConstraints);

        lblIfStatement.setText("If Statement:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblIfStatement, gridBagConstraints);

        cmbIfStatement.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbIfStatement, gridBagConstraints);

        lblWhileStatement.setText("While Statement:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblWhileStatement, gridBagConstraints);

        cmbWhileStatement.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbWhileStatement, gridBagConstraints);

        lblDoWhileStatement.setText("Do ... While Statement:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblDoWhileStatement, gridBagConstraints);

        cmbDoWhileStatement.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbDoWhileStatement, gridBagConstraints);

        lblEnumConstants.setText("Enum Constants:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblEnumConstants, gridBagConstraints);

        cmbEnumConstants.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbEnumConstants, gridBagConstraints);

        lblBinaryOperators.setText("Binary Operators:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblBinaryOperators, gridBagConstraints);

        cmbBinaryOperators.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbBinaryOperators, gridBagConstraints);

        lblTernaryOperators.setText("Ternary Operators:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblTernaryOperators, gridBagConstraints);

        cmbTernaryOperators.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbTernaryOperators, gridBagConstraints);

        lblAssignmentOperators.setText("Assignment Operators:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 4, 0);
        panel1.add(lblAssignmentOperators, gridBagConstraints);

        cmbAssignmentOperators.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 4, 8);
        panel1.add(cmbAssignmentOperators, gridBagConstraints);

        scrollPane.setViewportView(panel1);

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbArrayInitializer;
    private javax.swing.JComboBox cmbAssignmentOperators;
    private javax.swing.JComboBox cmbBinaryOperators;
    private javax.swing.JComboBox cmbChainedMethodCalls;
    private javax.swing.JComboBox cmbDoWhileStatement;
    private javax.swing.JComboBox cmbEnumConstants;
    private javax.swing.JComboBox cmbFor;
    private javax.swing.JComboBox cmbForStatement;
    private javax.swing.JComboBox cmbIfStatement;
    private javax.swing.JComboBox cmbMethodCallArguments;
    private javax.swing.JComboBox cmbMethodParameters;
    private javax.swing.JComboBox cmbTernaryOperators;
    private javax.swing.JComboBox cmbWhileStatement;
    private javax.swing.JLabel lblArrayInitializer;
    private javax.swing.JLabel lblAssignmentOperators;
    private javax.swing.JLabel lblBinaryOperators;
    private javax.swing.JLabel lblChainedMethodCalls;
    private javax.swing.JLabel lblDoWhileStatement;
    private javax.swing.JLabel lblEnumConstants;
    private javax.swing.JLabel lblFor;
    private javax.swing.JLabel lblForStatement;
    private javax.swing.JLabel lblIfStatement;
    private javax.swing.JLabel lblMethodCallArguments;
    private javax.swing.JLabel lblMethodParameters;
    private javax.swing.JLabel lblTernaryOperators;
    private javax.swing.JLabel lblWhileStatement;
    private javax.swing.JPanel panel1;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
