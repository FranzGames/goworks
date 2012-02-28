/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.tvl.goworks.editor.go.parser;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.WeakHashMap;
import org.antlr.netbeans.editor.text.DocumentSnapshot;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.works.editor.antlr4.classification.AbstractTokensTaskTaggerSnapshot;
import org.antlr.works.editor.antlr4.classification.SimpleLexerState;
import org.antlr.works.editor.antlr4.highlighting.TokenSourceWithStateV4;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author Sam Harwell
 */
class GoTokensTaskTaggerSnapshot extends AbstractTokensTaskTaggerSnapshot<SimpleLexerState> {
    private static final Map<Thread, Reference<GoLexerWrapper>> lexerCache = new WeakHashMap<Thread, Reference<GoLexerWrapper>>();

    public GoTokensTaskTaggerSnapshot(@NonNull DocumentSnapshot snapshot) {
        super(snapshot);
    }

    protected GoTokensTaskTaggerSnapshot(@NonNull GoTokensTaskTaggerSnapshot reference, @NonNull DocumentSnapshot snapshot) {
        super(reference, snapshot);
    }

    @Override
    protected SimpleLexerState getStartState() {
        return SimpleLexerState.INITIAL;
    }

    @Override
    protected TokenSourceWithStateV4<Token, SimpleLexerState> createLexer(CharStream input, SimpleLexerState startState) {
        synchronized (lexerCache) {
            Reference<GoLexerWrapper> ref = lexerCache.get(Thread.currentThread());
            GoLexerWrapper lexer = ref != null ? ref.get() : null;
            if (lexer == null) {
                lexer = new GoLexerWrapper(input);
                lexerCache.put(Thread.currentThread(), new SoftReference<GoLexerWrapper>(lexer));
            } else {
                lexer.setInputStream(input);
            }

            startState.apply(lexer);
            return lexer;
        }
    }

    @Override
    protected TokenSource<Token> getEffectiveTokenSource(TokenSourceWithStateV4<Token, SimpleLexerState> lexer) {
        return new GoLexerWrapper(lexer.getInputStream());
    }

    @Override
    protected GoTokensTaskTaggerSnapshot translateToImpl(@NonNull DocumentSnapshot targetSnapshot) {
        return new GoTokensTaskTaggerSnapshot(this, targetSnapshot);
    }

    private static class GoLexerWrapper extends GoLexer implements TokenSourceWithStateV4<Token, SimpleLexerState> {

        public GoLexerWrapper(CharStream input) {
            super(input);
        }

        @Override
        public CharStream getCharStream() {
            return getInputStream();
        }

        @Override
        public SimpleLexerState getState() {
            return SimpleLexerState.createSimpleState(this);
        }

    }
}
