/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;
import org.tvl.goworks.editor.go.codemodel.TypeWrapperModel;

/**
 *
 * @author Sam Harwell
 */
public abstract class TypeWrapperModelImpl extends TypeModelImpl implements TypeWrapperModel {

    private final TypeModelImpl elementType;

    public TypeWrapperModelImpl(String name, @NonNull TypeModelImpl elementType) {
        super(name, elementType.getFile(), null, null);
        Parameters.notNull("elementType", elementType);
        this.elementType = elementType;
    }

    @Override
    public boolean isResolved() {
        return elementType.isResolved();
    }

    @Override
    public TypeModelImpl getElementType() {
        return elementType;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypeWrapperModel)) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return getElementType().resolve().equals(((TypeWrapperModel)obj).getElementType().resolve());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + super.hashCode();
        hash = 31 * hash + elementType.hashCode();
        return hash;
    }

}
