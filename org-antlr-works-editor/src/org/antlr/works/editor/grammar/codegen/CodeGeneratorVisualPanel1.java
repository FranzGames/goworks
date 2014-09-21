/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codegen;

import java.io.File;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.filesystems.FileChooserBuilder;

public final class CodeGeneratorVisualPanel1 extends JPanel {
    private final CodeGeneratorWizardPanel1 _wizardPanel;
    private final DocumentListener _documentListener = new DocumentListener() {

        @Override
        public void insertUpdate(DocumentEvent e) {
            fireChange();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            fireChange();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            fireChange();
        }

    };

    public CodeGeneratorVisualPanel1() {
        this(null);
    }

    public CodeGeneratorVisualPanel1(@NullAllowed CodeGeneratorWizardPanel1 wizardPanel) {
        initComponents();
        _wizardPanel = wizardPanel;
        if (wizardPanel != null) {
            setOutputDirectory(wizardPanel.getOutputDirectory());
            setLibraryDirectory(wizardPanel.getLibraryDirectory());
            setTargetName(wizardPanel.getTargetName());
        }

        txtOutputDirectory.getDocument().addDocumentListener(_documentListener);
        txtLibDirectory.getDocument().addDocumentListener(_documentListener);
    }

    public String getOutputDirectory() {
        return txtOutputDirectory.getText();
    }

    public void setOutputDirectory(String value) {
        if (value == null) {
            value = "";
        }

        txtOutputDirectory.setText(value);
    }

    public String getLibraryDirectory() {
        return txtLibDirectory.getText();
    }

    public void setLibraryDirectory(String value) {
        if (value == null) {
            value = "";
        }

        txtLibDirectory.setText(value);
    }

    public String getTargetName() {
        return (String)cmbTargetLanguage.getSelectedItem();
    }

    public void setTargetName(String targetName) {
        cmbTargetLanguage.setSelectedItem(targetName);
    }

    @Override
    public String getName() {
        return "Location";
    }

    private void fireChange() {
        if (_wizardPanel != null) {
            _wizardPanel.getChangeSupport().fireChange();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"rawtypes", "unchecked", "Convert2Diamond"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        txtOutputDirectory = new javax.swing.JTextField();
        txtLibDirectory = new javax.swing.JTextField();
        cmbTargetLanguage = new javax.swing.JComboBox<String>();
        btnBrowseOutputDirectory = new javax.swing.JButton();
        btnBrowseLibDirectory = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CodeGeneratorVisualPanel1.class, "CodeGeneratorVisualPanel1.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(CodeGeneratorVisualPanel1.class, "CodeGeneratorVisualPanel1.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(CodeGeneratorVisualPanel1.class, "CodeGeneratorVisualPanel1.jLabel3.text")); // NOI18N

        txtOutputDirectory.setMinimumSize(new java.awt.Dimension(80, 20));

        cmbTargetLanguage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Java", "Java (sharwell/optimized)", "Python 2", "Python 3" }));
        cmbTargetLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTargetLanguageActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnBrowseOutputDirectory, org.openide.util.NbBundle.getMessage(CodeGeneratorVisualPanel1.class, "CodeGeneratorVisualPanel1.btnBrowseOutputDirectory.text")); // NOI18N
        btnBrowseOutputDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseOutputDirectoryActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(btnBrowseLibDirectory, org.openide.util.NbBundle.getMessage(CodeGeneratorVisualPanel1.class, "CodeGeneratorVisualPanel1.btnBrowseLibDirectory.text")); // NOI18N
        btnBrowseLibDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseLibDirectoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtOutputDirectory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLibDirectory)
                    .addComponent(cmbTargetLanguage, 0, 169, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBrowseOutputDirectory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseLibDirectory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowseOutputDirectory)
                    .addComponent(txtOutputDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowseLibDirectory)
                    .addComponent(txtLibDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTargetLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 222, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseOutputDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseOutputDirectoryActionPerformed
        browseForDirectory(txtOutputDirectory);
    }//GEN-LAST:event_btnBrowseOutputDirectoryActionPerformed

    private void btnBrowseLibDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseLibDirectoryActionPerformed
        browseForDirectory(txtLibDirectory);
    }//GEN-LAST:event_btnBrowseLibDirectoryActionPerformed

    private void cmbTargetLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTargetLanguageActionPerformed
        fireChange();
    }//GEN-LAST:event_cmbTargetLanguageActionPerformed

    private void browseForDirectory(JTextField field) {
        FileChooserBuilder builder = new FileChooserBuilder(CodeGenerator.class)
            .setDirectoriesOnly(true);

        File existing = new File(field.getText());
        if (existing.isDirectory()) {
            builder.setDefaultWorkingDirectory(existing);
            builder.forceUseOfDefaultWorkingDirectory(true);
        }

        File targetDirectory = builder.showOpenDialog();
        if (targetDirectory != null && targetDirectory.isDirectory()) {
            field.setText(targetDirectory.getAbsolutePath());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseLibDirectory;
    private javax.swing.JButton btnBrowseOutputDirectory;
    private javax.swing.JComboBox<String> cmbTargetLanguage;
    private javax.swing.JTextField txtLibDirectory;
    private javax.swing.JTextField txtOutputDirectory;
    // End of variables declaration//GEN-END:variables
}
