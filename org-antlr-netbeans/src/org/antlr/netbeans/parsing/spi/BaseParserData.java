/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.parsing.spi;

import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 */
public class BaseParserData<T> implements ParserData<T> {
    @NonNull
    private final ParseContext context;
    @NonNull
    private final ParserDataDefinition<T> definition;
    @NonNull
    private final DocumentSnapshot snapshot;
    @NullAllowed
    private final T data;

    public BaseParserData(@NonNull ParseContext context, @NonNull ParserDataDefinition<T> definition, @NonNull DocumentSnapshot snapshot, @NullAllowed T data) {
        Parameters.notNull("context", context);
        Parameters.notNull("definition", definition);
        Parameters.notNull("snapshot", snapshot);

        this.context = context;
        this.definition = definition;
        this.snapshot = snapshot;
        this.data = data;
    }

    @Override
    public ParseContext getContext() {
        return context;
    }

    @Override
    public ParserDataDefinition<T> getDefinition() {
        return definition;
    }

    @Override
    public DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BaseParserData<?>)) {
            return false;
        }

        BaseParserData<?> other = (BaseParserData<?>)obj;
        return this.definition.equals(other.definition)
            && this.context.equals(other.context)
            && this.snapshot.equals(other.snapshot)
            && (this.data == other.data || (this.data != null && this.data.equals(other.data)));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.context != null ? this.context.hashCode() : 0);
        hash = 67 * hash + (this.definition != null ? this.definition.hashCode() : 0);
        hash = 67 * hash + (this.snapshot != null ? this.snapshot.hashCode() : 0);
        hash = 67 * hash + (this.data != null ? this.data.hashCode() : 0);
        return hash;
    }

}
