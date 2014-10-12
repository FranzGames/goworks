/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.codemodel.impl;

import java.util.AbstractList;
import java.util.List;

/**
 *
 * @author Sam Harwell
 */
public class ProxyCollection<E> extends AbstractList<E> {
    private final List<? extends E>[] collections;

    @SafeVarargs
    @SuppressWarnings("varargs")
    public ProxyCollection(List<? extends E> ...collections) {
        this.collections = collections;
    }

    @Override
    public E get(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        int offset = 0;
        for (List<? extends E> list : collections) {
            if (index - offset < list.size()) {
                return list.get(index - offset);
            }

            offset += list.size();
        }

        throw new ArrayIndexOutOfBoundsException(index);
    }

    @Override
    public int size() {
        int size = 0;
        for (List<?> list : collections) {
            size += list.size();
        }

        return size;
    }

}
