/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.semantics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.tvl.goworks.editor.go.codemodel.CodeElementModel;
import org.tvl.goworks.editor.go.codemodel.PackageModel;
import org.tvl.goworks.editor.go.codemodel.impl.TypeModelImpl;
import org.tvl.goworks.editor.go.codemodel.impl.TypeSliceModelImpl;

/**
 *
 * @author Sam Harwell
 */
public class VariadicParameterTypeReference extends CodeElementReference {

    private final CodeElementReference type;

    public VariadicParameterTypeReference(CodeElementReference type) {
        this.type = type;
    }

    @Override
    public Collection<? extends CodeElementModel> resolve(GoAnnotatedParseTree annotatedParseTree, PackageModel currentPackage, Map<String, Collection<PackageModel>> resolvedPackages) {
        Collection<? extends CodeElementModel> types = type.resolve(annotatedParseTree, currentPackage, resolvedPackages);
        List<CodeElementModel> sliceTypes = new ArrayList<>();
        for (CodeElementModel model : types) {
            if (!(model instanceof TypeModelImpl)) {
                continue;
            }

            TypeModelImpl typeModel = (TypeModelImpl)model;
            sliceTypes.add(new TypeSliceModelImpl(typeModel));
        }

        return sliceTypes;
    }

}
