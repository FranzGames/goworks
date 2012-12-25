/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 * 
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.works.editor.grammar.codegen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.antlr.works.editor.grammar.GrammarDataObject;
import org.netbeans.api.queries.FileEncodingQuery;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle.Messages;

@ActionID(
    category = "Build",
    id = "org.antlr.works.editor.grammar.codegen.GenerateRecognizerAction")
@ActionRegistration(
    displayName = "#CTL_GenerateRecognizerAction")
@ActionReference(path = "Menu/BuildProject", position = 247, separatorBefore = 223)
@Messages("CTL_GenerateRecognizerAction=Generate Recognizer...")
public final class GenerateRecognizerAction implements ActionListener {

    private final DataObject context;

    public GenerateRecognizerAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (!(context instanceof GrammarDataObject)) {
            return;
        }

        WizardDescriptor wizard = new WizardDescriptor(new CodeGeneratorWizardIterator());
        wizard.setTitle("{0} ({1})");
        wizard.setTitle("Generate Recognizer");
        if (DialogDisplayer.getDefault().notify(wizard) == WizardDescriptor.FINISH_OPTION) {
            FileObject fileObject = context.getPrimaryFile();
            CodeGenerator generator = new CodeGenerator(fileObject);
            generator.outputDirectory = FileUtil.toFileObject(new File(CodeGeneratorWizardOptions.Location.getOutputDirectory(wizard)));

            String libraryDirectory = CodeGeneratorWizardOptions.Location.getLibraryDirectory(wizard);
            if (libraryDirectory != null && !libraryDirectory.isEmpty()) {
                generator.libDirectory = FileUtil.toFileObject(new File(libraryDirectory));
            }

            generator.listener = CodeGeneratorWizardOptions.Features.isGenerateListener(wizard);
            generator.visitor = CodeGeneratorWizardOptions.Features.isGenerateVisitor(wizard);
            generator.encoding = FileEncodingQuery.getEncoding(fileObject).name();
            generator.forceATN = CodeGeneratorWizardOptions.Advanced.isForceATN(wizard);
            generator.atn = CodeGeneratorWizardOptions.Advanced.isGenerateATNDiagrams(wizard);
            generator.treatWarningsAsErrors = CodeGeneratorWizardOptions.Advanced.isTreatWarningsAsErrors(wizard);
            generator.options = new HashMap<String, String>();
            if (CodeGeneratorWizardOptions.Features.isAbstractClasses(wizard)) {
                generator.options.put("abstract", "true");
            }

            generator.arguments = new ArrayList<String>();
            if (CodeGeneratorWizardOptions.Features.isUsePackageName(wizard)) {
                String packageName = CodeGeneratorWizardOptions.Features.getPackageName(wizard);
                if (packageName != null && !packageName.isEmpty()) {
                    generator.arguments.add("-package");
                    generator.arguments.add(packageName);
                }
            }

            if (CodeGeneratorWizardOptions.Advanced.isDebugST(wizard)) {
                generator.arguments.add("-XdbgST");
            }

            generator.run();
        }

    }
}
