// $ANTLR ANTLRVersion> GrammarParser.java generatedTimestamp>

/*
 [The "BSD licence"]
 Copyright (c) 2005-2009 Terence Parr
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.antlr.works.editor.grammar.experimental;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused"})
public class GrammarParser extends Parser {
	public static final int
		DOLLAR=44, PROTECTED=19, LT=36, ACTION_COLON=81, STAR=40, NESTED_ACTION=70, 
		DOUBLE_ANGLE_STRING_LITERAL=9, FRAGMENT=15, ACTION_ESCAPE=83, ACTION_REFERENCE=85, 
		ACTION_COLON2=80, ACTION_DOT=71, NOT=50, ID=52, ARG_ACTION_LT=57, ACTION_NEWLINE=90, 
		TOKEN_REF=3, LPAREN=33, ARG_ACTION_ELEMENT=65, ARG_ACTION_RPAREN=60, ARG_ACTION_GT=58, 
		AT=48, RPAREN=34, ARG_ACTION_LPAREN=59, IMPORT=14, STRING_LITERAL=54, 
		END_ACTION=91, ETC=47, COMMA=31, ACTION_GT=73, ACTION_LITERAL=87, ARG_ACTION_NEWLINE=68, 
		DOC_COMMENT=5, BLOCK_COMMENT=6, PLUS=41, BEGIN_ACTION=11, DOUBLE_QUOTE_STRING_LITERAL=8, 
		DOT=45, ACTION_LBRACK=76, MODE=28, ACTION_WORD=84, GRAMMAR=18, ARG_ACTION_ESCAPE=63, 
		RETURNS=22, ARG_ACTION_TEXT=66, ACTION_EQUALS=78, LOCALS=23, ACTION_WS=89, 
		RBRACE=51, POUND=49, LINE_COMMENT=7, PRIVATE=21, RARROW=35, END_ARG_ACTION=69, 
		TOKENS=13, RANGE=46, THROWS=24, INT=53, SEMI=32, RULE_REF=4, ARG_ACTION_EQUALS=61, 
		ACTION_RPAREN=75, ACTION_COMMA=79, COLON=29, COLONCOLON=30, ACTION_RBRACK=77, 
		WS=55, ACTION_COMMENT=86, QUESTION=39, FINALLY=26, ACTION_LT=72, TEMPLATE=27, 
		ACTION_TEXT=88, LEXER=16, ERRCHAR=56, ACTION_MINUS=82, ARG_ACTION_COMMA=62, 
		OR=43, ASSIGN=38, PLUS_ASSIGN=42, ARG_ACTION_WS=67, GT=37, CATCH=25, ARG_ACTION_WORD=64, 
		PUBLIC=20, PARSER=17, ACTION_LPAREN=74, OPTIONS=12, BEGIN_ARG_ACTION=10, 
		RULE=92, PREC_RULE=93, RULES=94, RULEMODIFIERS=95, RULEACTIONS=96, BLOCK=97, 
		OPTIONAL=98, CLOSURE=99, POSITIVE_CLOSURE=100, SET=101, CHAR_RANGE=102, 
		EPSILON=103, ALT=104, ALTLIST=105, ARG=106, ARGLIST=107, RET=108, COMBINED=109, 
		INITACTION=110, LABEL=111, WILDCARD=112, LIST=113, ELEMENT_OPTIONS=114, 
		RESULT=115, LEXER_ALT_ACTION=116, LEXER_ACTION_CALL=117;
	public static final String[] tokenNames = {
		"<INVALID>", "<INVALID>", "<INVALID>",
		"TOKEN_REF", "RULE_REF", "DOC_COMMENT", "BLOCK_COMMENT", "LINE_COMMENT", 
		"DOUBLE_QUOTE_STRING_LITERAL", "DOUBLE_ANGLE_STRING_LITERAL", "BEGIN_ARG_ACTION", 
		"BEGIN_ACTION", "OPTIONS", "TOKENS", "import", "fragment", "lexer", "parser", 
		"grammar", "protected", "public", "private", "returns", "locals", "throws", 
		"catch", "finally", "template", "mode", "COLON", "COLONCOLON", "COMMA", 
		";", "LPAREN", "RPAREN", "->", "LT", "GT", "ASSIGN", "?", "*", "+", "+=", 
		"|", "$", "DOT", "..", "...", "@", "#", "~", "RBRACE", "ID", "INT", "STRING_LITERAL", 
		"WS", "ERRCHAR", "ARG_ACTION_LT", "ARG_ACTION_GT", "ARG_ACTION_LPAREN", 
		"ARG_ACTION_RPAREN", "ARG_ACTION_EQUALS", "ARG_ACTION_COMMA", "ARG_ACTION_ESCAPE", 
		"ARG_ACTION_WORD", "ARG_ACTION_ELEMENT", "ARG_ACTION_TEXT", "ARG_ACTION_WS", 
		"ARG_ACTION_NEWLINE", "END_ARG_ACTION", "{", ".", "<", ">", "(", ")", 
		"[", "]", "=", ",", "::", ":", "-", "ACTION_ESCAPE", "ACTION_WORD", "ACTION_REFERENCE", 
		"ACTION_COMMENT", "ACTION_LITERAL", "ACTION_TEXT", "ACTION_WS", "ACTION_NEWLINE", 
		"}", "RULE", "PREC_RULE", "RULES", "RULEMODIFIERS", "RULEACTIONS", "BLOCK", 
		"OPTIONAL", "CLOSURE", "POSITIVE_CLOSURE", "SET", "CHAR_RANGE", "EPSILON", 
		"ALT", "ALTLIST", "ARG", "ARGLIST", "RET", "COMBINED", "INITACTION", "LABEL", 
		"WILDCARD", "LIST", "ELEMENT_OPTIONS", "RESULT", "LEXER_ALT_ACTION", "LEXER_ACTION_CALL"
	};
	public static final int
		RULE_grammarSpec = 0, RULE_grammarType = 1, RULE_prequelConstruct = 2, 
		RULE_optionsSpec = 3, RULE_option = 4, RULE_optionValue = 5, RULE_delegateGrammars = 6, 
		RULE_delegateGrammar = 7, RULE_tokensSpec = 8, RULE_tokenSpec = 9, RULE_actionBlock = 10, 
		RULE_actionExpression = 11, RULE_actionScopeExpression = 12, RULE_argActionBlock = 13, 
		RULE_argActionParameters = 14, RULE_argActionParameter = 15, RULE_argActionParameterType = 16, 
		RULE_argActionParameterTypePart = 17, RULE_ignored = 18, RULE_action = 19, 
		RULE_actionScopeName = 20, RULE_modeSpec = 21, RULE_rules = 22, RULE_rule = 23, 
		RULE_parserRule = 24, RULE_exceptionGroup = 25, RULE_exceptionHandler = 26, 
		RULE_finallyClause = 27, RULE_rulePrequels = 28, RULE_rulePrequel = 29, 
		RULE_ruleReturns = 30, RULE_throwsSpec = 31, RULE_localsSpec = 32, RULE_ruleAction = 33, 
		RULE_ruleModifiers = 34, RULE_ruleModifier = 35, RULE_ruleBlock = 36, 
		RULE_ruleAltList = 37, RULE_labeledAlt = 38, RULE_lexerRule = 39, RULE_lexerRuleBlock = 40, 
		RULE_lexerAltList = 41, RULE_lexerAlt = 42, RULE_lexerElements = 43, RULE_lexerElement = 44, 
		RULE_labeledLexerElement = 45, RULE_lexerBlock = 46, RULE_lexerActions = 47, 
		RULE_lexerAction = 48, RULE_lexerActionExpr = 49, RULE_altList = 50, RULE_alternative = 51, 
		RULE_elements = 52, RULE_element = 53, RULE_labeledElement = 54, RULE_ebnf = 55, 
		RULE_blockSuffix = 56, RULE_ebnfSuffix = 57, RULE_lexerAtom = 58, RULE_atom = 59, 
		RULE_notSet = 60, RULE_blockSet = 61, RULE_setElement = 62, RULE_block = 63, 
		RULE_ruleref = 64, RULE_range = 65, RULE_terminal = 66, RULE_elementOptions = 67, 
		RULE_elementOption = 68, RULE_id = 69, RULE_qid = 70;
	public static final String[] ruleNames = {
		"grammarSpec", "grammarType", "prequelConstruct", "optionsSpec", "option", 
		"optionValue", "delegateGrammars", "delegateGrammar", "tokensSpec", "tokenSpec", 
		"actionBlock", "actionExpression", "actionScopeExpression", "argActionBlock", 
		"argActionParameters", "argActionParameter", "argActionParameterType", 
		"argActionParameterTypePart", "ignored", "action", "actionScopeName", 
		"modeSpec", "rules", "rule", "parserRule", "exceptionGroup", "exceptionHandler", 
		"finallyClause", "rulePrequels", "rulePrequel", "ruleReturns", "throwsSpec", 
		"localsSpec", "ruleAction", "ruleModifiers", "ruleModifier", "ruleBlock", 
		"ruleAltList", "labeledAlt", "lexerRule", "lexerRuleBlock", "lexerAltList", 
		"lexerAlt", "lexerElements", "lexerElement", "labeledLexerElement", "lexerBlock", 
		"lexerActions", "lexerAction", "lexerActionExpr", "altList", "alternative", 
		"elements", "element", "labeledElement", "ebnf", "blockSuffix", "ebnfSuffix", 
		"lexerAtom", "atom", "notSet", "blockSet", "setElement", "block", "ruleref", 
		"range", "terminal", "elementOptions", "elementOption", "id", "qid"
	};
	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator<Token>(this,_ATN);
	}
	public static class grammarSpecContext extends ParserRuleContext<Token> {
		public grammarSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final grammarSpecContext grammarSpec() throws RecognitionException {
		grammarSpecContext _localctx = new grammarSpecContext(_ctx, 0);
		enterRule(_localctx, RULE_grammarSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(142); match(DOC_COMMENT);
					}
					break;
			}
			setState(146); grammarType();
			setState(148); id();
			setState(150); match(SEMI);
			setState(156);
			_errHandler.sync(this);
			int _alt237 = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt237!=2 && _alt237!=-1 ) {
			    if ( _alt237==1 ) {
			        {
			        {
			        setState(152); prequelConstruct();
			        }
			        } 
			    }
				setState(158);
				_errHandler.sync(this);
				_alt237 = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(159); rules();
			setState(165);
			_errHandler.sync(this);
			int _alt250 = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt250!=2 && _alt250!=-1 ) {
			    if ( _alt250==1 ) {
			        {
			        {
			        setState(161); modeSpec();
			        }
			        } 
			    }
				setState(167);
				_errHandler.sync(this);
				_alt250 = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(168); match(EOF);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class grammarTypeContext extends ParserRuleContext<Token> {
		public Token t;;
		public Token g;;
		public grammarTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final grammarTypeContext grammarType() throws RecognitionException {
		grammarTypeContext _localctx = new grammarTypeContext(_ctx, 2);
		enterRule(_localctx, RULE_grammarType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(170); _localctx.t = match(LEXER);
					setState(172); _localctx.g = match(GRAMMAR);
					}
					break;

				case 2:
					{
					setState(174); _localctx.t = match(PARSER);
					setState(176); _localctx.g = match(GRAMMAR);
					}
					break;

				case 3:
					{
					setState(178); _localctx.g = match(GRAMMAR);
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class prequelConstructContext extends ParserRuleContext<Token> {
		public prequelConstructContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final prequelConstructContext prequelConstruct() throws RecognitionException {
		prequelConstructContext _localctx = new prequelConstructContext(_ctx, 4);
		enterRule(_localctx, RULE_prequelConstruct);
		try {
			setState(190);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(182); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(184); delegateGrammars();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(186); tokensSpec();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(188); action();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class optionsSpecContext extends ParserRuleContext<Token> {
		public optionsSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final optionsSpecContext optionsSpec() throws RecognitionException {
		optionsSpecContext _localctx = new optionsSpecContext(_ctx, 6);
		enterRule(_localctx, RULE_optionsSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192); match(OPTIONS);
			setState(200);
			_errHandler.sync(this);
			int _alt365 = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt365!=2 && _alt365!=-1 ) {
			    if ( _alt365==1 ) {
			        {
			        {
			        setState(194); option();
			        setState(196); match(SEMI);
			        }
			        } 
			    }
				setState(202);
				_errHandler.sync(this);
				_alt365 = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(203); match(RBRACE);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class optionContext extends ParserRuleContext<Token> {
		public optionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final optionContext option() throws RecognitionException {
		optionContext _localctx = new optionContext(_ctx, 8);
		enterRule(_localctx, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205); id();
			setState(207); match(ASSIGN);
			setState(209); optionValue();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class optionValueContext extends ParserRuleContext<Token> {
		public optionValueContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final optionValueContext optionValue() throws RecognitionException {
		optionValueContext _localctx = new optionValueContext(_ctx, 10);
		enterRule(_localctx, RULE_optionValue);
		try {
			setState(219);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(211); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(213); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(215); match(INT);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(217); match(STAR);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class delegateGrammarsContext extends ParserRuleContext<Token> {
		public delegateGrammarsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final delegateGrammarsContext delegateGrammars() throws RecognitionException {
		delegateGrammarsContext _localctx = new delegateGrammarsContext(_ctx, 12);
		enterRule(_localctx, RULE_delegateGrammars);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221); match(IMPORT);
			setState(223); delegateGrammar();
			setState(231);
			_errHandler.sync(this);
			int _alt454 = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt454!=2 && _alt454!=-1 ) {
			    if ( _alt454==1 ) {
			        {
			        {
			        setState(225); match(COMMA);
			        setState(227); delegateGrammar();
			        }
			        } 
			    }
				setState(233);
				_errHandler.sync(this);
				_alt454 = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(234); match(SEMI);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class delegateGrammarContext extends ParserRuleContext<Token> {
		public delegateGrammarContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final delegateGrammarContext delegateGrammar() throws RecognitionException {
		delegateGrammarContext _localctx = new delegateGrammarContext(_ctx, 14);
		enterRule(_localctx, RULE_delegateGrammar);
		try {
			setState(244);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(236); id();
					setState(238); match(ASSIGN);
					setState(240); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(242); id();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class tokensSpecContext extends ParserRuleContext<Token> {
		public tokensSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final tokensSpecContext tokensSpec() throws RecognitionException {
		tokensSpecContext _localctx = new tokensSpecContext(_ctx, 16);
		enterRule(_localctx, RULE_tokensSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246); match(TOKENS);
			setState(250); 
			_errHandler.sync(this);
			int _alt489 = getInterpreter().adaptivePredict(_input,9,_ctx);
			do {
				switch ( _alt489 ) {
					case 1:
						{
						{
						setState(248); tokenSpec();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(252); 
				_errHandler.sync(this);
				_alt489 = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt489!=2 && _alt489!=-1 );
			setState(254); match(RBRACE);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class tokenSpecContext extends ParserRuleContext<Token> {
		public tokenSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final tokenSpecContext tokenSpec() throws RecognitionException {
		tokenSpecContext _localctx = new tokenSpecContext(_ctx, 18);
		enterRule(_localctx, RULE_tokenSpec);
		try {
			setState(270);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(256); id();
					setState(264);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
						case 1:
							{
							setState(258); match(ASSIGN);
							setState(260); match(STRING_LITERAL);
							}
							break;

						case 2:
							{
							}
							break;
					}
					setState(266); match(SEMI);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(268); match(RULE_REF);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class actionBlockContext extends ParserRuleContext<Token> {
		public actionBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionBlockContext actionBlock() throws RecognitionException {
		actionBlockContext _localctx = new actionBlockContext(_ctx, 20);
		enterRule(_localctx, RULE_actionBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272); match(BEGIN_ACTION);
			setState(322);
			_errHandler.sync(this);
			int _alt620 = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt620!=2 && _alt620!=-1 ) {
			    if ( _alt620==1 ) {
			        {
			        setState(320);
			        //_errHandler.sync(this);
			        switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			        	case 1:
			        		{
			        		setState(274); actionBlock();
			        		}
			        		break;

			        	case 2:
			        		{
			        		setState(276); actionExpression();
			        		}
			        		break;

			        	case 3:
			        		{
			        		setState(278); actionScopeExpression();
			        		}
			        		break;

			        	case 4:
			        		{
			        		setState(280); match(ACTION_WS);
			        		}
			        		break;

			        	case 5:
			        		{
			        		setState(282); match(ACTION_NEWLINE);
			        		}
			        		break;

			        	case 6:
			        		{
			        		setState(284); match(ACTION_COMMENT);
			        		}
			        		break;

			        	case 7:
			        		{
			        		setState(286); match(ACTION_LITERAL);
			        		}
			        		break;

			        	case 8:
			        		{
			        		setState(288); match(ACTION_TEXT);
			        		}
			        		break;

			        	case 9:
			        		{
			        		setState(290); match(ACTION_LT);
			        		}
			        		break;

			        	case 10:
			        		{
			        		setState(292); match(ACTION_GT);
			        		}
			        		break;

			        	case 11:
			        		{
			        		setState(294); match(ACTION_LPAREN);
			        		}
			        		break;

			        	case 12:
			        		{
			        		setState(296); match(ACTION_RPAREN);
			        		}
			        		break;

			        	case 13:
			        		{
			        		setState(298); match(ACTION_LBRACK);
			        		}
			        		break;

			        	case 14:
			        		{
			        		setState(300); match(ACTION_RBRACK);
			        		}
			        		break;

			        	case 15:
			        		{
			        		setState(302); match(ACTION_EQUALS);
			        		}
			        		break;

			        	case 16:
			        		{
			        		setState(304); match(ACTION_COMMA);
			        		}
			        		break;

			        	case 17:
			        		{
			        		setState(306); match(ACTION_ESCAPE);
			        		}
			        		break;

			        	case 18:
			        		{
			        		setState(308); match(ACTION_WORD);
			        		}
			        		break;

			        	case 19:
			        		{
			        		setState(310); match(ACTION_REFERENCE);
			        		}
			        		break;

			        	case 20:
			        		{
			        		setState(312); match(ACTION_COLON);
			        		}
			        		break;

			        	case 21:
			        		{
			        		setState(314); match(ACTION_COLON2);
			        		}
			        		break;

			        	case 22:
			        		{
			        		setState(316); match(ACTION_MINUS);
			        		}
			        		break;

			        	case 23:
			        		{
			        		setState(318); match(ACTION_DOT);
			        		}
			        		break;
			        }
			        } 
			    }
				setState(324);
				_errHandler.sync(this);
				_alt620 = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(325); match(END_ACTION);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class actionExpressionContext extends ParserRuleContext<Token> {
		public Token ref;;
		public Token op;;
		public Token member;;
		public actionExpressionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionExpressionContext actionExpression() throws RecognitionException {
		actionExpressionContext _localctx = new actionExpressionContext(_ctx, 22);
		enterRule(_localctx, RULE_actionExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327); _localctx.ref = match(ACTION_REFERENCE);
			setState(333);
			_errHandler.sync(this);
			int _alt635 = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt635!=2 && _alt635!=-1 ) {
			    if ( _alt635==1 ) {
			        {
			        {
			        setState(329); ignored();
			        }
			        } 
			    }
				setState(335);
				_errHandler.sync(this);
				_alt635 = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(336); _localctx.op = match(ACTION_DOT);
			setState(342);
			_errHandler.sync(this);
			int _alt642 = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt642!=2 && _alt642!=-1 ) {
			    if ( _alt642==1 ) {
			        {
			        {
			        setState(338); ignored();
			        }
			        } 
			    }
				setState(344);
				_errHandler.sync(this);
				_alt642 = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(345); _localctx.member = match(ACTION_WORD);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class actionScopeExpressionContext extends ParserRuleContext<Token> {
		public Token ref;;
		public Token neg;;
		public Token index;;
		public Token op;;
		public Token member;;
		public actionScopeExpressionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionScopeExpressionContext actionScopeExpression() throws RecognitionException {
		actionScopeExpressionContext _localctx = new actionScopeExpressionContext(_ctx, 24);
		enterRule(_localctx, RULE_actionScopeExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347); _localctx.ref = match(ACTION_REFERENCE);
			setState(353);
			_errHandler.sync(this);
			int _alt659 = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt659!=2 && _alt659!=-1 ) {
			    if ( _alt659==1 ) {
			        {
			        {
			        setState(349); ignored();
			        }
			        } 
			    }
				setState(355);
				_errHandler.sync(this);
				_alt659 = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(394);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(356); match(ACTION_LBRACK);
					setState(362);
					_errHandler.sync(this);
					int _alt665 = getInterpreter().adaptivePredict(_input,17,_ctx);
					while ( _alt665!=2 && _alt665!=-1 ) {
					    if ( _alt665==1 ) {
					        {
					        {
					        setState(358); ignored();
					        }
					        } 
					    }
						setState(364);
						_errHandler.sync(this);
						_alt665 = getInterpreter().adaptivePredict(_input,17,_ctx);
					}
					setState(374);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
						case 1:
							{
							setState(365); _localctx.neg = match(ACTION_MINUS);
							setState(371);
							_errHandler.sync(this);
							int _alt673 = getInterpreter().adaptivePredict(_input,18,_ctx);
							while ( _alt673!=2 && _alt673!=-1 ) {
							    if ( _alt673==1 ) {
							        {
							        {
							        setState(367); ignored();
							        }
							        } 
							    }
								setState(373);
								_errHandler.sync(this);
								_alt673 = getInterpreter().adaptivePredict(_input,18,_ctx);
							}
							}
							break;
					}
					setState(376); _localctx.index = match(ACTION_WORD);
					setState(382);
					_errHandler.sync(this);
					int _alt682 = getInterpreter().adaptivePredict(_input,20,_ctx);
					while ( _alt682!=2 && _alt682!=-1 ) {
					    if ( _alt682==1 ) {
					        {
					        {
					        setState(378); ignored();
					        }
					        } 
					    }
						setState(384);
						_errHandler.sync(this);
						_alt682 = getInterpreter().adaptivePredict(_input,20,_ctx);
					}
					setState(385); match(ACTION_RBRACK);
					setState(391);
					_errHandler.sync(this);
					int _alt687 = getInterpreter().adaptivePredict(_input,21,_ctx);
					while ( _alt687!=2 && _alt687!=-1 ) {
					    if ( _alt687==1 ) {
					        {
					        {
					        setState(387); ignored();
					        }
					        } 
					    }
						setState(393);
						_errHandler.sync(this);
						_alt687 = getInterpreter().adaptivePredict(_input,21,_ctx);
					}
					}
					break;
			}
			setState(396); _localctx.op = match(ACTION_COLON2);
			setState(402);
			_errHandler.sync(this);
			int _alt696 = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt696!=2 && _alt696!=-1 ) {
			    if ( _alt696==1 ) {
			        {
			        {
			        setState(398); ignored();
			        }
			        } 
			    }
				setState(404);
				_errHandler.sync(this);
				_alt696 = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(405); _localctx.member = match(ACTION_WORD);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class argActionBlockContext extends ParserRuleContext<Token> {
		public argActionBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final argActionBlockContext argActionBlock() throws RecognitionException {
		argActionBlockContext _localctx = new argActionBlockContext(_ctx, 26);
		enterRule(_localctx, RULE_argActionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407); match(BEGIN_ARG_ACTION);
			setState(413);
			_errHandler.sync(this);
			int _alt759 = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt759!=2 && _alt759!=-1 ) {
			    if ( _alt759==1 ) {
			        {
			        {
			        setState(409);
			        _input.LT(1);
			        _la = _input.LA(1);
			        if ( !(_la==ARG_ACTION_LT || _la==ARG_ACTION_GT || _la==ARG_ACTION_LPAREN || _la==ARG_ACTION_RPAREN || _la==ARG_ACTION_EQUALS || _la==ARG_ACTION_COMMA || _la==ARG_ACTION_ESCAPE || _la==ARG_ACTION_WORD || _la==ARG_ACTION_ELEMENT || _la==ARG_ACTION_TEXT || _la==ARG_ACTION_WS || _la==ARG_ACTION_NEWLINE) ) {
			        _errHandler.recoverInline(this);
			        }
			        consume();
			        }
			        } 
			    }
				setState(415);
				_errHandler.sync(this);
				_alt759 = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(416); match(END_ARG_ACTION);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class argActionParametersContext extends ParserRuleContext<Token> {
		public argActionParameterContext parameters;;
		public List<argActionParameterContext> parameters_list = new ArrayList<argActionParameterContext>();;
		public argActionParametersContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final argActionParametersContext argActionParameters() throws RecognitionException {
		argActionParametersContext _localctx = new argActionParametersContext(_ctx, 28);
		enterRule(_localctx, RULE_argActionParameters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418); match(BEGIN_ARG_ACTION);
			setState(424);
			_errHandler.sync(this);
			int _alt772 = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt772!=2 && _alt772!=-1 ) {
			    if ( _alt772==1 ) {
			        {
			        {
			        setState(420); ignored();
			        }
			        } 
			    }
				setState(426);
				_errHandler.sync(this);
				_alt772 = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(459);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(427); _localctx.parameters = argActionParameter();
					_localctx.parameters_list.add(_localctx.parameters);
					setState(433);
					_errHandler.sync(this);
					int _alt780 = getInterpreter().adaptivePredict(_input,26,_ctx);
					while ( _alt780!=2 && _alt780!=-1 ) {
					    if ( _alt780==1 ) {
					        {
					        {
					        setState(429); ignored();
					        }
					        } 
					    }
						setState(435);
						_errHandler.sync(this);
						_alt780 = getInterpreter().adaptivePredict(_input,26,_ctx);
					}
					setState(456);
					_errHandler.sync(this);
					int _alt795 = getInterpreter().adaptivePredict(_input,29,_ctx);
					while ( _alt795!=2 && _alt795!=-1 ) {
					    if ( _alt795==1 ) {
					        {
					        {
					        setState(436); match(ARG_ACTION_COMMA);
					        setState(442);
					        _errHandler.sync(this);
					        int _alt786 = getInterpreter().adaptivePredict(_input,27,_ctx);
					        while ( _alt786!=2 && _alt786!=-1 ) {
					            if ( _alt786==1 ) {
					                {
					                {
					                setState(438); ignored();
					                }
					                } 
					            }
					        	setState(444);
					        	_errHandler.sync(this);
					        	_alt786 = getInterpreter().adaptivePredict(_input,27,_ctx);
					        }
					        setState(445); _localctx.parameters = argActionParameter();
					        _localctx.parameters_list.add(_localctx.parameters);
					        setState(451);
					        _errHandler.sync(this);
					        int _alt793 = getInterpreter().adaptivePredict(_input,28,_ctx);
					        while ( _alt793!=2 && _alt793!=-1 ) {
					            if ( _alt793==1 ) {
					                {
					                {
					                setState(447); ignored();
					                }
					                } 
					            }
					        	setState(453);
					        	_errHandler.sync(this);
					        	_alt793 = getInterpreter().adaptivePredict(_input,28,_ctx);
					        }
					        }
					        } 
					    }
						setState(458);
						_errHandler.sync(this);
						_alt795 = getInterpreter().adaptivePredict(_input,29,_ctx);
					}
					}
					break;
			}
			setState(461); match(END_ARG_ACTION);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class argActionParameterContext extends ParserRuleContext<Token> {
		public argActionParameterTypeContext type;;
		public Token name;;
		public argActionParameterContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final argActionParameterContext argActionParameter() throws RecognitionException {
		argActionParameterContext _localctx = new argActionParameterContext(_ctx, 30);
		enterRule(_localctx, RULE_argActionParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(463); _localctx.type = argActionParameterType();
					}
					break;
			}
			setState(471);
			_errHandler.sync(this);
			int _alt813 = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt813!=2 && _alt813!=-1 ) {
			    if ( _alt813==1 ) {
			        {
			        {
			        setState(467); ignored();
			        }
			        } 
			    }
				setState(473);
				_errHandler.sync(this);
				_alt813 = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			setState(474); _localctx.name = match(ARG_ACTION_WORD);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class argActionParameterTypeContext extends ParserRuleContext<Token> {
		public argActionParameterTypeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final argActionParameterTypeContext argActionParameterType() throws RecognitionException {
		argActionParameterTypeContext _localctx = new argActionParameterTypeContext(_ctx, 32);
		enterRule(_localctx, RULE_argActionParameterType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(476); argActionParameterTypePart();
			setState(489);
			_errHandler.sync(this);
			int _alt833 = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt833!=2 && _alt833!=-1 ) {
			    if ( _alt833==1 ) {
			        {
			        {
			        setState(482);
			        _errHandler.sync(this);
			        int _alt829 = getInterpreter().adaptivePredict(_input,33,_ctx);
			        while ( _alt829!=2 && _alt829!=-1 ) {
			            if ( _alt829==1 ) {
			                {
			                {
			                setState(478); ignored();
			                }
			                } 
			            }
			        	setState(484);
			        	_errHandler.sync(this);
			        	_alt829 = getInterpreter().adaptivePredict(_input,33,_ctx);
			        }
			        setState(485); argActionParameterTypePart();
			        }
			        } 
			    }
				setState(491);
				_errHandler.sync(this);
				_alt833 = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class argActionParameterTypePartContext extends ParserRuleContext<Token> {
		public argActionParameterTypePartContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final argActionParameterTypePartContext argActionParameterTypePart() throws RecognitionException {
		argActionParameterTypePartContext _localctx = new argActionParameterTypePartContext(_ctx, 34);
		enterRule(_localctx, RULE_argActionParameterTypePart);
		try {
			setState(510);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(492); match(ARG_ACTION_WORD);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(494); match(ARG_ACTION_LT);
					setState(498);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
						case 1:
							{
							setState(496); argActionParameterType();
							}
							break;
					}
					setState(500); match(ARG_ACTION_GT);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(502); match(ARG_ACTION_LPAREN);
					setState(506);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
						case 1:
							{
							setState(504); argActionParameterType();
							}
							break;
					}
					setState(508); match(ARG_ACTION_RPAREN);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ignoredContext extends ParserRuleContext<Token> {
		public ignoredContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ignoredContext ignored() throws RecognitionException {
		ignoredContext _localctx = new ignoredContext(_ctx, 36);
		enterRule(_localctx, RULE_ignored);
		try {
			setState(522);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(512); match(ACTION_WS);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(514); match(ACTION_NEWLINE);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(516); match(ACTION_COMMENT);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(518); match(ARG_ACTION_WS);
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(520); match(ARG_ACTION_NEWLINE);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class actionContext extends ParserRuleContext<Token> {
		public actionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionContext action() throws RecognitionException {
		actionContext _localctx = new actionContext(_ctx, 38);
		enterRule(_localctx, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(524); match(AT);
			setState(530);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(526); actionScopeName();
					setState(528); match(COLONCOLON);
					}
					break;
			}
			setState(532); id();
			setState(534); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class actionScopeNameContext extends ParserRuleContext<Token> {
		public actionScopeNameContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final actionScopeNameContext actionScopeName() throws RecognitionException {
		actionScopeNameContext _localctx = new actionScopeNameContext(_ctx, 40);
		enterRule(_localctx, RULE_actionScopeName);
		try {
			setState(542);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(536); id();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(538); match(LEXER);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(540); match(PARSER);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class modeSpecContext extends ParserRuleContext<Token> {
		public modeSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final modeSpecContext modeSpec() throws RecognitionException {
		modeSpecContext _localctx = new modeSpecContext(_ctx, 42);
		enterRule(_localctx, RULE_modeSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(544); match(MODE);
			setState(546); id();
			setState(548); match(SEMI);
			setState(552); 
			_errHandler.sync(this);
			int _alt943 = getInterpreter().adaptivePredict(_input,41,_ctx);
			do {
				switch ( _alt943 ) {
					case 1:
						{
						{
						setState(550); rule();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(554); 
				_errHandler.sync(this);
				_alt943 = getInterpreter().adaptivePredict(_input,41,_ctx);
			} while ( _alt943!=2 && _alt943!=-1 );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class rulesContext extends ParserRuleContext<Token> {
		public rulesContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulesContext rules() throws RecognitionException {
		rulesContext _localctx = new rulesContext(_ctx, 44);
		enterRule(_localctx, RULE_rules);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(560);
			_errHandler.sync(this);
			int _alt954 = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt954!=2 && _alt954!=-1 ) {
			    if ( _alt954==1 ) {
			        {
			        {
			        setState(556); rule();
			        }
			        } 
			    }
				setState(562);
				_errHandler.sync(this);
				_alt954 = getInterpreter().adaptivePredict(_input,42,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ruleContext extends ParserRuleContext<Token> {
		public ruleContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleContext rule() throws RecognitionException {
		ruleContext _localctx = new ruleContext(_ctx, 46);
		enterRule(_localctx, RULE_rule);
		try {
			setState(567);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(563); parserRule();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(565); lexerRule();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class parserRuleContext extends ParserRuleContext<Token> {
		public Token name;;
		public argActionParametersContext parameters;;
		public parserRuleContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final parserRuleContext parserRule() throws RecognitionException {
		parserRuleContext _localctx = new parserRuleContext(_ctx, 48);
		enterRule(_localctx, RULE_parserRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
				case 1:
					{
					setState(569); match(DOC_COMMENT);
					}
					break;
			}
			setState(575);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(573); ruleModifiers();
					}
					break;
			}
			setState(577); _localctx.name = match(RULE_REF);
			setState(581);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(579); _localctx.parameters = argActionParameters();
					}
					break;
			}
			setState(585);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(583); ruleReturns();
					}
					break;
			}
			setState(589);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(587); throwsSpec();
					}
					break;
			}
			setState(593);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
				case 1:
					{
					setState(591); localsSpec();
					}
					break;
			}
			setState(595); rulePrequels();
			setState(597); match(COLON);
			setState(599); ruleBlock();
			setState(601); match(SEMI);
			setState(603); exceptionGroup();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class exceptionGroupContext extends ParserRuleContext<Token> {
		public exceptionGroupContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final exceptionGroupContext exceptionGroup() throws RecognitionException {
		exceptionGroupContext _localctx = new exceptionGroupContext(_ctx, 50);
		enterRule(_localctx, RULE_exceptionGroup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(609);
			_errHandler.sync(this);
			int _alt1105 = getInterpreter().adaptivePredict(_input,50,_ctx);
			while ( _alt1105!=2 && _alt1105!=-1 ) {
			    if ( _alt1105==1 ) {
			        {
			        {
			        setState(605); exceptionHandler();
			        }
			        } 
			    }
				setState(611);
				_errHandler.sync(this);
				_alt1105 = getInterpreter().adaptivePredict(_input,50,_ctx);
			}
			setState(614);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					{
					setState(612); finallyClause();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class exceptionHandlerContext extends ParserRuleContext<Token> {
		public exceptionHandlerContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final exceptionHandlerContext exceptionHandler() throws RecognitionException {
		exceptionHandlerContext _localctx = new exceptionHandlerContext(_ctx, 52);
		enterRule(_localctx, RULE_exceptionHandler);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616); match(CATCH);
			setState(618); argActionBlock();
			setState(620); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class finallyClauseContext extends ParserRuleContext<Token> {
		public finallyClauseContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final finallyClauseContext finallyClause() throws RecognitionException {
		finallyClauseContext _localctx = new finallyClauseContext(_ctx, 54);
		enterRule(_localctx, RULE_finallyClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(622); match(FINALLY);
			setState(624); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class rulePrequelsContext extends ParserRuleContext<Token> {
		public rulePrequelsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulePrequelsContext rulePrequels() throws RecognitionException {
		rulePrequelsContext _localctx = new rulePrequelsContext(_ctx, 56);
		enterRule(_localctx, RULE_rulePrequels);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(630);
			_errHandler.sync(this);
			int _alt1145 = getInterpreter().adaptivePredict(_input,52,_ctx);
			while ( _alt1145!=2 && _alt1145!=-1 ) {
			    if ( _alt1145==1 ) {
			        {
			        {
			        setState(626); rulePrequel();
			        }
			        } 
			    }
				setState(632);
				_errHandler.sync(this);
				_alt1145 = getInterpreter().adaptivePredict(_input,52,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class rulePrequelContext extends ParserRuleContext<Token> {
		public rulePrequelContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulePrequelContext rulePrequel() throws RecognitionException {
		rulePrequelContext _localctx = new rulePrequelContext(_ctx, 58);
		enterRule(_localctx, RULE_rulePrequel);
		try {
			setState(637);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(633); optionsSpec();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(635); ruleAction();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ruleReturnsContext extends ParserRuleContext<Token> {
		public argActionParametersContext values;;
		public ruleReturnsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleReturnsContext ruleReturns() throws RecognitionException {
		ruleReturnsContext _localctx = new ruleReturnsContext(_ctx, 60);
		enterRule(_localctx, RULE_ruleReturns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(639); match(RETURNS);
			setState(641); _localctx.values = argActionParameters();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class throwsSpecContext extends ParserRuleContext<Token> {
		public throwsSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final throwsSpecContext throwsSpec() throws RecognitionException {
		throwsSpecContext _localctx = new throwsSpecContext(_ctx, 62);
		enterRule(_localctx, RULE_throwsSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(643); match(THROWS);
			setState(645); qid();
			setState(653);
			_errHandler.sync(this);
			int _alt1226 = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt1226!=2 && _alt1226!=-1 ) {
			    if ( _alt1226==1 ) {
			        {
			        {
			        setState(647); match(COMMA);
			        setState(649); qid();
			        }
			        } 
			    }
				setState(655);
				_errHandler.sync(this);
				_alt1226 = getInterpreter().adaptivePredict(_input,54,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class localsSpecContext extends ParserRuleContext<Token> {
		public argActionParametersContext values;;
		public localsSpecContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final localsSpecContext localsSpec() throws RecognitionException {
		localsSpecContext _localctx = new localsSpecContext(_ctx, 64);
		enterRule(_localctx, RULE_localsSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(656); match(LOCALS);
			setState(658); _localctx.values = argActionParameters();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ruleActionContext extends ParserRuleContext<Token> {
		public ruleActionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleActionContext ruleAction() throws RecognitionException {
		ruleActionContext _localctx = new ruleActionContext(_ctx, 66);
		enterRule(_localctx, RULE_ruleAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(660); match(AT);
			setState(662); id();
			setState(664); actionBlock();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ruleModifiersContext extends ParserRuleContext<Token> {
		public ruleModifiersContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleModifiersContext ruleModifiers() throws RecognitionException {
		ruleModifiersContext _localctx = new ruleModifiersContext(_ctx, 68);
		enterRule(_localctx, RULE_ruleModifiers);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(668); 
			_errHandler.sync(this);
			int _alt1289 = getInterpreter().adaptivePredict(_input,55,_ctx);
			do {
				switch ( _alt1289 ) {
					case 1:
						{
						{
						setState(666); ruleModifier();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(670); 
				_errHandler.sync(this);
				_alt1289 = getInterpreter().adaptivePredict(_input,55,_ctx);
			} while ( _alt1289!=2 && _alt1289!=-1 );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ruleModifierContext extends ParserRuleContext<Token> {
		public ruleModifierContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleModifierContext ruleModifier() throws RecognitionException {
		ruleModifierContext _localctx = new ruleModifierContext(_ctx, 70);
		enterRule(_localctx, RULE_ruleModifier);
		try {
			setState(680);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(672); match(PUBLIC);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(674); match(PRIVATE);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(676); match(PROTECTED);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(678); match(FRAGMENT);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ruleBlockContext extends ParserRuleContext<Token> {
		public ruleBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleBlockContext ruleBlock() throws RecognitionException {
		ruleBlockContext _localctx = new ruleBlockContext(_ctx, 72);
		enterRule(_localctx, RULE_ruleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(682); ruleAltList();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ruleAltListContext extends ParserRuleContext<Token> {
		public ruleAltListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ruleAltListContext ruleAltList() throws RecognitionException {
		ruleAltListContext _localctx = new ruleAltListContext(_ctx, 74);
		enterRule(_localctx, RULE_ruleAltList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(684); labeledAlt();
			setState(692);
			_errHandler.sync(this);
			int _alt1356 = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt1356!=2 && _alt1356!=-1 ) {
			    if ( _alt1356==1 ) {
			        {
			        {
			        setState(686); match(OR);
			        setState(688); labeledAlt();
			        }
			        } 
			    }
				setState(694);
				_errHandler.sync(this);
				_alt1356 = getInterpreter().adaptivePredict(_input,57,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class labeledAltContext extends ParserRuleContext<Token> {
		public labeledAltContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final labeledAltContext labeledAlt() throws RecognitionException {
		labeledAltContext _localctx = new labeledAltContext(_ctx, 76);
		enterRule(_localctx, RULE_labeledAlt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(695); alternative();
			setState(701);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(697); match(POUND);
					setState(699); id();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class lexerRuleContext extends ParserRuleContext<Token> {
		public Token name;;
		public lexerRuleContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final lexerRuleContext lexerRule() throws RecognitionException {
		lexerRuleContext _localctx = new lexerRuleContext(_ctx, 78);
		enterRule(_localctx, RULE_lexerRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(705);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
				case 1:
					{
					setState(703); match(DOC_COMMENT);
					}
					break;
			}
			setState(709);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
				case 1:
					{
					setState(707); match(FRAGMENT);
					}
					break;
			}
			setState(711); _localctx.name = match(TOKEN_REF);
			setState(713); match(COLON);
			setState(715); lexerRuleBlock();
			setState(717); match(SEMI);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class lexerRuleBlockContext extends ParserRuleContext<Token> {
		public lexerRuleBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final lexerRuleBlockContext lexerRuleBlock() throws RecognitionException {
		lexerRuleBlockContext _localctx = new lexerRuleBlockContext(_ctx, 80);
		enterRule(_localctx, RULE_lexerRuleBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(719); lexerAltList();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class lexerAltListContext extends ParserRuleContext<Token> {
		public lexerAltListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final lexerAltListContext lexerAltList() throws RecognitionException {
		lexerAltListContext _localctx = new lexerAltListContext(_ctx, 82);
		enterRule(_localctx, RULE_lexerAltList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(721); lexerAlt();
			setState(729);
			_errHandler.sync(this);
			int _alt1416 = getInterpreter().adaptivePredict(_input,61,_ctx);
			while ( _alt1416!=2 && _alt1416!=-1 ) {
			    if ( _alt1416==1 ) {
			        {
			        {
			        setState(723); match(OR);
			        setState(725); lexerAlt();
			        }
			        } 
			    }
				setState(731);
				_errHandler.sync(this);
				_alt1416 = getInterpreter().adaptivePredict(_input,61,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class lexerAltContext extends ParserRuleContext<Token> {
		public lexerAltContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final lexerAltContext lexerAlt() throws RecognitionException {
		lexerAltContext _localctx = new lexerAltContext(_ctx, 84);
		enterRule(_localctx, RULE_lexerAlt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(732); lexerElements();
			setState(738);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
				case 1:
					{
					setState(734); lexerActions();
					}
					break;

				case 2:
					{
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class lexerElementsContext extends ParserRuleContext<Token> {
		public lexerElementsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final lexerElementsContext lexerElements() throws RecognitionException {
		lexerElementsContext _localctx = new lexerElementsContext(_ctx, 86);
		enterRule(_localctx, RULE_lexerElements);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(742); 
			_errHandler.sync(this);
			int _alt1441 = getInterpreter().adaptivePredict(_input,63,_ctx);
			do {
				switch ( _alt1441 ) {
					case 1:
						{
						{
						setState(740); lexerElement();
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(744); 
				_errHandler.sync(this);
				_alt1441 = getInterpreter().adaptivePredict(_input,63,_ctx);
			} while ( _alt1441!=2 && _alt1441!=-1 );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class lexerElementContext extends ParserRuleContext<Token> {
		public lexerElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final lexerElementContext lexerElement() throws RecognitionException {
		lexerElementContext _localctx = new lexerElementContext(_ctx, 88);
		enterRule(_localctx, RULE_lexerElement);
		try {
			setState(776);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(746); labeledLexerElement();
					setState(752);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
						case 1:
							{
							setState(748); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(754); lexerAtom();
					setState(760);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
						case 1:
							{
							setState(756); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;
					}
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(762); lexerBlock();
					setState(768);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
						case 1:
							{
							setState(764); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;
					}
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(770); actionBlock();
					setState(774);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
						case 1:
							{
							setState(772); match(QUESTION);
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class labeledLexerElementContext extends ParserRuleContext<Token> {
		public Token ass;;
		public labeledLexerElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final labeledLexerElementContext labeledLexerElement() throws RecognitionException {
		labeledLexerElementContext _localctx = new labeledLexerElementContext(_ctx, 90);
		enterRule(_localctx, RULE_labeledLexerElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(778); id();
			setState(784);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
				case 1:
					{
					setState(780); _localctx.ass = match(ASSIGN);
					}
					break;

				case 2:
					{
					setState(782); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;
			}
			setState(790);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
				case 1:
					{
					setState(786); lexerAtom();
					}
					break;

				case 2:
					{
					setState(788); block();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class lexerBlockContext extends ParserRuleContext<Token> {
		public lexerBlockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final lexerBlockContext lexerBlock() throws RecognitionException {
		lexerBlockContext _localctx = new lexerBlockContext(_ctx, 92);
		enterRule(_localctx, RULE_lexerBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(792); match(LPAREN);
			setState(794); lexerAltList();
			setState(796); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class lexerActionsContext extends ParserRuleContext<Token> {
		public lexerActionsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final lexerActionsContext lexerActions() throws RecognitionException {
		lexerActionsContext _localctx = new lexerActionsContext(_ctx, 94);
		enterRule(_localctx, RULE_lexerActions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(798); match(RARROW);
			setState(800); lexerAction();
			setState(808);
			_errHandler.sync(this);
			int _alt1551 = getInterpreter().adaptivePredict(_input,71,_ctx);
			while ( _alt1551!=2 && _alt1551!=-1 ) {
			    if ( _alt1551==1 ) {
			        {
			        {
			        setState(802); match(COMMA);
			        setState(804); lexerAction();
			        }
			        } 
			    }
				setState(810);
				_errHandler.sync(this);
				_alt1551 = getInterpreter().adaptivePredict(_input,71,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class lexerActionContext extends ParserRuleContext<Token> {
		public lexerActionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final lexerActionContext lexerAction() throws RecognitionException {
		lexerActionContext _localctx = new lexerActionContext(_ctx, 96);
		enterRule(_localctx, RULE_lexerAction);
		try {
			setState(821);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(811); id();
					setState(813); match(LPAREN);
					setState(815); lexerActionExpr();
					setState(817); match(RPAREN);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(819); id();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class lexerActionExprContext extends ParserRuleContext<Token> {
		public lexerActionExprContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final lexerActionExprContext lexerActionExpr() throws RecognitionException {
		lexerActionExprContext _localctx = new lexerActionExprContext(_ctx, 98);
		enterRule(_localctx, RULE_lexerActionExpr);
		try {
			setState(827);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(823); match(ID);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(825); match(INT);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class altListContext extends ParserRuleContext<Token> {
		public altListContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final altListContext altList() throws RecognitionException {
		altListContext _localctx = new altListContext(_ctx, 100);
		enterRule(_localctx, RULE_altList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(829); alternative();
			setState(837);
			_errHandler.sync(this);
			int _alt1596 = getInterpreter().adaptivePredict(_input,74,_ctx);
			while ( _alt1596!=2 && _alt1596!=-1 ) {
			    if ( _alt1596==1 ) {
			        {
			        {
			        setState(831); match(OR);
			        setState(833); alternative();
			        }
			        } 
			    }
				setState(839);
				_errHandler.sync(this);
				_alt1596 = getInterpreter().adaptivePredict(_input,74,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class alternativeContext extends ParserRuleContext<Token> {
		public alternativeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final alternativeContext alternative() throws RecognitionException {
		alternativeContext _localctx = new alternativeContext(_ctx, 102);
		enterRule(_localctx, RULE_alternative);
		try {
			setState(844);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(840); elements();
					}
					break;

				case 2:
					{
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class elementsContext extends ParserRuleContext<Token> {
		public elementContext e;;
		public List<elementContext> e_list = new ArrayList<elementContext>();;
		public elementsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementsContext elements() throws RecognitionException {
		elementsContext _localctx = new elementsContext(_ctx, 104);
		enterRule(_localctx, RULE_elements);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(848); 
			_errHandler.sync(this);
			int _alt1623 = getInterpreter().adaptivePredict(_input,76,_ctx);
			do {
				switch ( _alt1623 ) {
					case 1:
						{
						{
						setState(846); _localctx.e = element();
						_localctx.e_list.add(_localctx.e);
						}
						}
						break;
					default :
						throw new NoViableAltException(this);
				}
				setState(850); 
				_errHandler.sync(this);
				_alt1623 = getInterpreter().adaptivePredict(_input,76,_ctx);
			} while ( _alt1623!=2 && _alt1623!=-1 );
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class elementContext extends ParserRuleContext<Token> {
		public elementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementContext element() throws RecognitionException {
		elementContext _localctx = new elementContext(_ctx, 106);
		enterRule(_localctx, RULE_element);
		try {
			setState(876);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(852); labeledElement();
					setState(858);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
						case 1:
							{
							setState(854); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(860); atom();
					setState(866);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
						case 1:
							{
							setState(862); ebnfSuffix();
							}
							break;

						case 2:
							{
							}
							break;
					}
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(868); ebnf();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(870); actionBlock();
					setState(874);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
						case 1:
							{
							setState(872); match(QUESTION);
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class labeledElementContext extends ParserRuleContext<Token> {
		public idContext label;;
		public Token ass;;
		public labeledElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final labeledElementContext labeledElement() throws RecognitionException {
		labeledElementContext _localctx = new labeledElementContext(_ctx, 108);
		enterRule(_localctx, RULE_labeledElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(878); _localctx.label = id();
			setState(884);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
				case 1:
					{
					setState(880); _localctx.ass = match(ASSIGN);
					}
					break;

				case 2:
					{
					setState(882); _localctx.ass = match(PLUS_ASSIGN);
					}
					break;
			}
			setState(890);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					{
					setState(886); atom();
					}
					break;

				case 2:
					{
					setState(888); block();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ebnfContext extends ParserRuleContext<Token> {
		public ebnfContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ebnfContext ebnf() throws RecognitionException {
		ebnfContext _localctx = new ebnfContext(_ctx, 110);
		enterRule(_localctx, RULE_ebnf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(892); block();
			setState(898);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
				case 1:
					{
					setState(894); blockSuffix();
					}
					break;

				case 2:
					{
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class blockSuffixContext extends ParserRuleContext<Token> {
		public blockSuffixContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final blockSuffixContext blockSuffix() throws RecognitionException {
		blockSuffixContext _localctx = new blockSuffixContext(_ctx, 112);
		enterRule(_localctx, RULE_blockSuffix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(900); ebnfSuffix();
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ebnfSuffixContext extends ParserRuleContext<Token> {
		public ebnfSuffixContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final ebnfSuffixContext ebnfSuffix() throws RecognitionException {
		ebnfSuffixContext _localctx = new ebnfSuffixContext(_ctx, 114);
		enterRule(_localctx, RULE_ebnfSuffix);
		try {
			setState(908);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(902); match(QUESTION);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(904); match(STAR);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(906); match(PLUS);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class lexerAtomContext extends ParserRuleContext<Token> {
		public lexerAtomContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final lexerAtomContext lexerAtom() throws RecognitionException {
		lexerAtomContext _localctx = new lexerAtomContext(_ctx, 116);
		enterRule(_localctx, RULE_lexerAtom);
		try {
			setState(926);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(910); range();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(912); terminal();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(914); match(RULE_REF);
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(916); notSet();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(918); argActionBlock();
					}
					break;

				case 6:
					enterOuterAlt(_localctx, 6);
					{
					setState(920); match(DOT);
					setState(924);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
						case 1:
							{
							setState(922); elementOptions();
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class atomContext extends ParserRuleContext<Token> {
		public atomContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final atomContext atom() throws RecognitionException {
		atomContext _localctx = new atomContext(_ctx, 118);
		enterRule(_localctx, RULE_atom);
		try {
			setState(942);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(928); range();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(930); terminal();
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(932); ruleref();
					}
					break;

				case 4:
					enterOuterAlt(_localctx, 4);
					{
					setState(934); notSet();
					}
					break;

				case 5:
					enterOuterAlt(_localctx, 5);
					{
					setState(936); match(DOT);
					setState(940);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
						case 1:
							{
							setState(938); elementOptions();
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class notSetContext extends ParserRuleContext<Token> {
		public notSetContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final notSetContext notSet() throws RecognitionException {
		notSetContext _localctx = new notSetContext(_ctx, 120);
		enterRule(_localctx, RULE_notSet);
		try {
			setState(952);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(944); match(NOT);
					setState(946); setElement();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(948); match(NOT);
					setState(950); blockSet();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class blockSetContext extends ParserRuleContext<Token> {
		public blockSetContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final blockSetContext blockSet() throws RecognitionException {
		blockSetContext _localctx = new blockSetContext(_ctx, 122);
		enterRule(_localctx, RULE_blockSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(954); match(LPAREN);
			setState(956); setElement();
			setState(964);
			_errHandler.sync(this);
			int _alt1879 = getInterpreter().adaptivePredict(_input,90,_ctx);
			while ( _alt1879!=2 && _alt1879!=-1 ) {
			    if ( _alt1879==1 ) {
			        {
			        {
			        setState(958); match(OR);
			        setState(960); setElement();
			        }
			        } 
			    }
				setState(966);
				_errHandler.sync(this);
				_alt1879 = getInterpreter().adaptivePredict(_input,90,_ctx);
			}
			setState(967); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class setElementContext extends ParserRuleContext<Token> {
		public setElementContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final setElementContext setElement() throws RecognitionException {
		setElementContext _localctx = new setElementContext(_ctx, 124);
		enterRule(_localctx, RULE_setElement);
		try {
			setState(975);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(969); match(TOKEN_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(971); match(STRING_LITERAL);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(973); range();
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class blockContext extends ParserRuleContext<Token> {
		public ruleActionContext ra;;
		public List<ruleActionContext> ra_list = new ArrayList<ruleActionContext>();;
		public blockContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final blockContext block() throws RecognitionException {
		blockContext _localctx = new blockContext(_ctx, 126);
		enterRule(_localctx, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(977); match(LPAREN);
			setState(992);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
				case 1:
					{
					setState(981);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
						case 1:
							{
							setState(979); optionsSpec();
							}
							break;
					}
					setState(987);
					_errHandler.sync(this);
					int _alt1929 = getInterpreter().adaptivePredict(_input,93,_ctx);
					while ( _alt1929!=2 && _alt1929!=-1 ) {
					    if ( _alt1929==1 ) {
					        {
					        {
					        setState(983); _localctx.ra = ruleAction();
					        _localctx.ra_list.add(_localctx.ra);
					        }
					        } 
					    }
						setState(989);
						_errHandler.sync(this);
						_alt1929 = getInterpreter().adaptivePredict(_input,93,_ctx);
					}
					setState(990); match(COLON);
					}
					break;
			}
			setState(994); altList();
			setState(996); match(RPAREN);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class rulerefContext extends ParserRuleContext<Token> {
		public rulerefContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rulerefContext ruleref() throws RecognitionException {
		rulerefContext _localctx = new rulerefContext(_ctx, 128);
		enterRule(_localctx, RULE_ruleref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(998); match(RULE_REF);
			setState(1002);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
				case 1:
					{
					setState(1000); argActionBlock();
					}
					break;
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class rangeContext extends ParserRuleContext<Token> {
		public rangeContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final rangeContext range() throws RecognitionException {
		rangeContext _localctx = new rangeContext(_ctx, 130);
		enterRule(_localctx, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1004); match(STRING_LITERAL);
			setState(1006); match(RANGE);
			setState(1008); match(STRING_LITERAL);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class terminalContext extends ParserRuleContext<Token> {
		public terminalContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final terminalContext terminal() throws RecognitionException {
		terminalContext _localctx = new terminalContext(_ctx, 132);
		enterRule(_localctx, RULE_terminal);
		try {
			setState(1022);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1010); match(TOKEN_REF);
					setState(1014);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
						case 1:
							{
							setState(1012); elementOptions();
							}
							break;
					}
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1016); match(STRING_LITERAL);
					setState(1020);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
						case 1:
							{
							setState(1018); elementOptions();
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class elementOptionsContext extends ParserRuleContext<Token> {
		public elementOptionsContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementOptionsContext elementOptions() throws RecognitionException {
		elementOptionsContext _localctx = new elementOptionsContext(_ctx, 134);
		enterRule(_localctx, RULE_elementOptions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1024); match(LT);
			setState(1026); elementOption();
			setState(1034);
			_errHandler.sync(this);
			int _alt2030 = getInterpreter().adaptivePredict(_input,99,_ctx);
			while ( _alt2030!=2 && _alt2030!=-1 ) {
			    if ( _alt2030==1 ) {
			        {
			        {
			        setState(1028); match(COMMA);
			        setState(1030); elementOption();
			        }
			        } 
			    }
				setState(1036);
				_errHandler.sync(this);
				_alt2030 = getInterpreter().adaptivePredict(_input,99,_ctx);
			}
			setState(1037); match(GT);
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class elementOptionContext extends ParserRuleContext<Token> {
		public elementOptionContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final elementOptionContext elementOption() throws RecognitionException {
		elementOptionContext _localctx = new elementOptionContext(_ctx, 136);
		enterRule(_localctx, RULE_elementOption);
		try {
			setState(1051);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1039); qid();
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1041); id();
					setState(1043); match(ASSIGN);
					setState(1049);
					//_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
						case 1:
							{
							setState(1045); qid();
							}
							break;

						case 2:
							{
							setState(1047); match(STRING_LITERAL);
							}
							break;
					}
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class idContext extends ParserRuleContext<Token> {
		public idContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final idContext id() throws RecognitionException {
		idContext _localctx = new idContext(_ctx, 138);
		enterRule(_localctx, RULE_id);
		try {
			setState(1059);
			//_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
				case 1:
					enterOuterAlt(_localctx, 1);
					{
					setState(1053); match(RULE_REF);
					}
					break;

				case 2:
					enterOuterAlt(_localctx, 2);
					{
					setState(1055); match(TOKEN_REF);
					}
					break;

				case 3:
					enterOuterAlt(_localctx, 3);
					{
					setState(1057); match(TEMPLATE);
					}
					break;
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class qidContext extends ParserRuleContext<Token> {
		public qidContext(ParserRuleContext<Token> parent, int state) {
			super(parent, state);
		}
		@Override
		public void enterRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener<Token> listener) {
			if ( listener!=null ) ((GrammarParserListener)listener).exitRule(this);
		}
	}

	public final qidContext qid() throws RecognitionException {
		qidContext _localctx = new qidContext(_ctx, 140);
		enterRule(_localctx, RULE_qid);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1061); id();
			setState(1069);
			_errHandler.sync(this);
			int _alt2103 = getInterpreter().adaptivePredict(_input,103,_ctx);
			while ( _alt2103!=2 && _alt2103!=-1 ) {
			    if ( _alt2103==1 ) {
			        {
			        {
			        setState(1063); match(DOT);
			        setState(1065); id();
			        }
			        } 
			    }
				setState(1071);
				_errHandler.sync(this);
				_alt2103 = getInterpreter().adaptivePredict(_input,103,_ctx);
			}
			}
			_localctx.stop = _input.LT(-1);
		}
		catch (RecognitionException re) {
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@Override
	public String[] getTokenNames() { return tokenNames; }
	@Override
	public String[] getRuleNames() { return ruleNames; }
	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\1u\u0431\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5\2\6\7\6\2\7"+
		"\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16\7\16\2\17"+
		"\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25\7\25\2\26"+
		"\7\26\2\27\7\27\2\30\7\30\2\31\7\31\2\32\7\32\2\33\7\33\2\34\7\34\2\35"+
		"\7\35\2\36\7\36\2\37\7\37\2 \7 \2!\7!\2\"\7\"\2#\7#\2$\7$\2%\7%\2&\7&"+
		"\2\'\7\'\2(\7(\2)\7)\2*\7*\2+\7+\2,\7,\2-\7-\2.\7.\2/\7/\2\60\7\60\2\61"+
		"\7\61\2\62\7\62\2\63\7\63\2\64\7\64\2\65\7\65\2\66\7\66\2\67\7\67\28\7"+
		"8\29\79\2:\7:\2;\7;\2<\7<\2=\7=\2>\7>\2?\7?\2@\7@\2A\7A\2B\7B\2C\7C\2"+
		"D\7D\2E\7E\2F\7F\1\0\0\3\0\b\0\1\0\0\1\0\0\1\0\0\1\0\0\5\0\b\0\n\0\f\0"+
		"\u009e\t\0\1\0\0\1\0\0\5\0\b\0\n\0\f\0\u00a7\t\0\1\0\1\0\1\1\0\1\1\0\1"+
		"\1\0\1\1\0\1\1\0\3\1\b\1\1\2\0\1\2\0\1\2\0\1\2\0\3\2\b\2\1\3\0\1\3\0\1"+
		"\3\1\3\5\3\b\3\n\3\f\3\u00ca\t\3\1\3\1\3\1\4\0\1\4\0\1\4\1\4\1\5\0\1\5"+
		"\0\1\5\0\1\5\0\3\5\b\5\1\6\0\1\6\0\1\6\0\1\6\0\5\6\b\6\n\6\f\6\u00e9\t"+
		"\6\1\6\1\6\1\7\0\1\7\0\1\7\1\7\1\7\0\3\7\b\7\1\b\0\1\b\0\4\b\b\b\13\b"+
		"\f\b\u00fc\1\b\1\b\1\t\0\1\t\0\1\t\0\1\t\0\3\t\b\t\1\t\1\t\1\t\0\3\t\b"+
		"\t\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1"+
		"\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0\1\n\0"+
		"\1\n\0\5\n\b\n\n\n\f\n\u0144\t\n\1\n\1\n\1\13\0\1\13\0\5\13\b\13\n\13"+
		"\f\13\u014f\t\13\1\13\0\1\13\0\5\13\b\13\n\13\f\13\u0158\t\13\1\13\1\13"+
		"\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u0163\t\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u016c"+
		"\t\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u0175\t\f\3\f\b\f\1\f\0\1\f\0\5\f\b\f"+
		"\n\f\f\f\u0180\t\f\1\f\0\1\f\0\5\f\b\f\n\f\f\f\u0189\t\f\3\f\b\f\1\f\0"+
		"\1\f\0\5\f\b\f\n\f\f\f\u0194\t\f\1\f\1\f\1\r\0\1\r\0\5\r\b\r\n\r\f\r\u019f"+
		"\t\r\1\r\1\r\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01aa\t\16\1\16\0\1\16"+
		"\0\5\16\b\16\n\16\f\16\u01b3\t\16\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01bc"+
		"\t\16\1\16\0\1\16\0\5\16\b\16\n\16\f\16\u01c5\t\16\5\16\b\16\n\16\f\16"+
		"\u01ca\t\16\3\16\b\16\1\16\1\16\1\17\0\3\17\b\17\1\17\0\5\17\b\17\n\17"+
		"\f\17\u01d9\t\17\1\17\1\17\1\20\0\1\20\0\5\20\b\20\n\20\f\20\u01e4\t\20"+
		"\1\20\0\5\20\b\20\n\20\f\20\u01eb\t\20\1\21\0\1\21\0\1\21\0\3\21\b\21"+
		"\1\21\0\1\21\0\1\21\0\3\21\b\21\1\21\0\3\21\b\21\1\22\0\1\22\0\1\22\0"+
		"\1\22\0\1\22\0\3\22\b\22\1\23\0\1\23\0\1\23\1\23\3\23\b\23\1\23\0\1\23"+
		"\1\23\1\24\0\1\24\0\1\24\0\3\24\b\24\1\25\0\1\25\0\1\25\0\1\25\0\4\25"+
		"\b\25\13\25\f\25\u022a\1\26\0\5\26\b\26\n\26\f\26\u0232\t\26\1\27\0\1"+
		"\27\0\3\27\b\27\1\30\0\3\30\b\30\1\30\0\3\30\b\30\1\30\0\1\30\0\3\30\b"+
		"\30\1\30\0\3\30\b\30\1\30\0\3\30\b\30\1\30\0\3\30\b\30\1\30\0\1\30\0\1"+
		"\30\0\1\30\0\1\30\1\30\1\31\0\5\31\b\31\n\31\f\31\u0263\t\31\1\31\0\3"+
		"\31\b\31\1\32\0\1\32\0\1\32\1\32\1\33\0\1\33\1\33\1\34\0\5\34\b\34\n\34"+
		"\f\34\u0278\t\34\1\35\0\1\35\0\3\35\b\35\1\36\0\1\36\1\36\1\37\0\1\37"+
		"\0\1\37\0\1\37\0\5\37\b\37\n\37\f\37\u028f\t\37\1 \0\1 \1 \1!\0\1!\0\1"+
		"!\1!\1\"\0\4\"\b\"\13\"\f\"\u029e\1#\0\1#\0\1#\0\1#\0\3#\b#\1$\1$\1%\0"+
		"\1%\0\1%\0\5%\b%\n%\f%\u02b6\t%\1&\0\1&\0\1&\0\3&\b&\1\'\0\3\'\b\'\1\'"+
		"\0\3\'\b\'\1\'\0\1\'\0\1\'\0\1\'\1\'\1(\1(\1)\0\1)\0\1)\0\5)\b)\n)\f)"+
		"\u02db\t)\1*\0\1*\0\1*\0\3*\b*\1+\0\4+\b+\13+\f+\u02e8\1,\0\1,\0\1,\0"+
		"\3,\b,\1,\0\1,\0\1,\0\3,\b,\1,\0\1,\0\1,\0\3,\b,\1,\0\1,\0\3,\b,\3,\b"+
		",\1-\0\1-\0\1-\0\3-\b-\1-\0\1-\0\3-\b-\1.\0\1.\0\1.\1.\1/\0\1/\0\1/\0"+
		"\1/\0\5/\b/\n/\f/\u032a\t/\1\60\0\1\60\0\1\60\0\1\60\1\60\1\60\0\3\60"+
		"\b\60\1\61\0\1\61\0\3\61\b\61\1\62\0\1\62\0\1\62\0\5\62\b\62\n\62\f\62"+
		"\u0347\t\62\1\63\0\1\63\0\3\63\b\63\1\64\0\4\64\b\64\13\64\f\64\u0352"+
		"\1\65\0\1\65\0\1\65\0\3\65\b\65\1\65\0\1\65\0\1\65\0\3\65\b\65\1\65\0"+
		"\1\65\0\1\65\0\3\65\b\65\3\65\b\65\1\66\0\1\66\0\1\66\0\3\66\b\66\1\66"+
		"\0\1\66\0\3\66\b\66\1\67\0\1\67\0\1\67\0\3\67\b\67\18\18\19\0\19\0\19"+
		"\0\39\b9\1:\0\1:\0\1:\0\1:\0\1:\0\1:\0\1:\0\3:\b:\3:\b:\1;\0\1;\0\1;\0"+
		"\1;\0\1;\0\1;\0\3;\b;\3;\b;\1<\0\1<\0\1<\0\1<\0\3<\b<\1=\0\1=\0\1=\0\1"+
		"=\0\5=\b=\n=\f=\u03c6\t=\1=\1=\1>\0\1>\0\1>\0\3>\b>\1?\0\1?\0\3?\b?\1"+
		"?\0\5?\b?\n?\f?\u03dd\t?\1?\0\3?\b?\1?\0\1?\1?\1@\0\1@\0\3@\b@\1A\0\1"+
		"A\0\1A\1A\1B\0\1B\0\3B\bB\1B\0\1B\0\3B\bB\3B\bB\1C\0\1C\0\1C\0\1C\0\5"+
		"C\bC\nC\fC\u040c\tC\1C\1C\1D\0\1D\0\1D\0\1D\0\1D\0\3D\bD\3D\bD\1E\0\1"+
		"E\0\1E\0\3E\bE\1F\0\1F\0\1F\0\5F\bF\nF\fF\u042f\tF\1FG\0\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bd"+
		"fhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\0\1\19D\u0403"+
		"\0\u0090\1\0\0\0\1\u0430\5\uffff\0\0\2\u00b4\1\0\0\0\3\u0094\1\0\0\0\4"+
		"\u00be\1\0\0\0\5\u009b\1\0\0\0\6\u00c0\1\0\0\0\7\u00bf\1\0\0\0\7\u027e"+
		"\1\0\0\0\7\u03d6\1\0\0\0\b\u00cd\1\0\0\0\t\u00c4\1\0\0\0\n\u00db\1\0\0"+
		"\0\13\u00d2\1\0\0\0\f\u00dd\1\0\0\0\r\u00bf\1\0\0\0\16\u00f4\1\0\0\0\17"+
		"\u00e7\1\0\0\0\17\u00e6\1\0\0\0\20\u00f6\1\0\0\0\21\u00bf\1\0\0\0\22\u010e"+
		"\1\0\0\0\23\u00fb\1\0\0\0\24\u0110\1\0\0\0\25\u0141\1\0\0\0\25\u0217\1"+
		"\0\0\0\25\u026d\1\0\0\0\25\u0271\1\0\0\0\25\u0299\1\0\0\0\25\u0306\1\0"+
		"\0\0\25\u036a\1\0\0\0\26\u0147\1\0\0\0\27\u0141\1\0\0\0\30\u015b\1\0\0"+
		"\0\31\u0141\1\0\0\0\32\u0197\1\0\0\0\33\u026c\1\0\0\0\33\u039f\1\0\0\0"+
		"\33\u03eb\1\0\0\0\34\u01a2\1\0\0\0\35\u0246\1\0\0\0\35\u0282\1\0\0\0\35"+
		"\u0293\1\0\0\0\36\u01d1\1\0\0\0\37\u01b1\1\0\0\0\37\u01c3\1\0\0\0 \u01dc"+
		"\1\0\0\0!\u01d2\1\0\0\0!\u01f3\1\0\0\0!\u01fb\1\0\0\0\"\u01fe\1\0\0\0"+
		"#\u01e9\1\0\0\0#\u01e8\1\0\0\0$\u020a\1\0\0\0%\u014c\1\0\0\0%\u0155\1"+
		"\0\0\0%\u0160\1\0\0\0%\u0169\1\0\0\0%\u0172\1\0\0\0%\u017d\1\0\0\0%\u0186"+
		"\1\0\0\0%\u0191\1\0\0\0%\u01a7\1\0\0\0%\u01b0\1\0\0\0%\u01b9\1\0\0\0%"+
		"\u01c2\1\0\0\0%\u01d6\1\0\0\0%\u01e1\1\0\0\0&\u020c\1\0\0\0\'\u00bf\1"+
		"\0\0\0(\u021e\1\0\0\0)\u0210\1\0\0\0*\u0220\1\0\0\0+\u00a4\1\0\0\0,\u0230"+
		"\1\0\0\0-\u00a5\1\0\0\0.\u0237\1\0\0\0/\u0229\1\0\0\0/\u022f\1\0\0\0\60"+
		"\u023b\1\0\0\0\61\u0238\1\0\0\0\62\u0261\1\0\0\0\63\u025c\1\0\0\0\64\u0268"+
		"\1\0\0\0\65\u0260\1\0\0\0\66\u026e\1\0\0\0\67\u0267\1\0\0\08\u0276\1\0"+
		"\0\09\u0255\1\0\0\0:\u027d\1\0\0\0;\u0275\1\0\0\0<\u027f\1\0\0\0=\u024a"+
		"\1\0\0\0>\u0283\1\0\0\0?\u024e\1\0\0\0@\u0290\1\0\0\0A\u0252\1\0\0\0B"+
		"\u0294\1\0\0\0C\u027e\1\0\0\0C\u03da\1\0\0\0D\u029c\1\0\0\0E\u0240\1\0"+
		"\0\0F\u02a8\1\0\0\0G\u029d\1\0\0\0H\u02aa\1\0\0\0I\u0259\1\0\0\0J\u02ac"+
		"\1\0\0\0K\u02ab\1\0\0\0L\u02b7\1\0\0\0M\u02b4\1\0\0\0M\u02b3\1\0\0\0N"+
		"\u02c1\1\0\0\0O\u0238\1\0\0\0P\u02cf\1\0\0\0Q\u02cd\1\0\0\0R\u02d1\1\0"+
		"\0\0S\u02d0\1\0\0\0S\u031c\1\0\0\0T\u02dc\1\0\0\0U\u02d9\1\0\0\0U\u02d8"+
		"\1\0\0\0V\u02e6\1\0\0\0W\u02e2\1\0\0\0X\u0308\1\0\0\0Y\u02e7\1\0\0\0Z"+
		"\u030a\1\0\0\0[\u02f0\1\0\0\0\\\u0318\1\0\0\0]\u0300\1\0\0\0^\u031e\1"+
		"\0\0\0_\u02e3\1\0\0\0`\u0335\1\0\0\0a\u0328\1\0\0\0a\u0327\1\0\0\0b\u033b"+
		"\1\0\0\0c\u0331\1\0\0\0d\u033d\1\0\0\0e\u03e4\1\0\0\0f\u034c\1\0\0\0g"+
		"\u02bd\1\0\0\0g\u0345\1\0\0\0g\u0344\1\0\0\0h\u0350\1\0\0\0i\u034d\1\0"+
		"\0\0j\u036c\1\0\0\0k\u0351\1\0\0\0l\u036e\1\0\0\0m\u035a\1\0\0\0n\u037c"+
		"\1\0\0\0o\u036d\1\0\0\0p\u0384\1\0\0\0q\u0383\1\0\0\0r\u038c\1\0\0\0s"+
		"\u02f1\1\0\0\0s\u02f9\1\0\0\0s\u0301\1\0\0\0s\u035b\1\0\0\0s\u0363\1\0"+
		"\0\0s\u0385\1\0\0\0t\u039e\1\0\0\0u\u02f8\1\0\0\0u\u0317\1\0\0\0v\u03ae"+
		"\1\0\0\0w\u0362\1\0\0\0w\u037b\1\0\0\0x\u03b8\1\0\0\0y\u039f\1\0\0\0y"+
		"\u03af\1\0\0\0z\u03ba\1\0\0\0{\u03b9\1\0\0\0|\u03cf\1\0\0\0}\u03b9\1\0"+
		"\0\0}\u03c4\1\0\0\0}\u03c3\1\0\0\0~\u03d1\1\0\0\0\177\u0317\1\0\0\0\177"+
		"\u037b\1\0\0\0\177\u0382\1\0\0\0\u0080\u03e6\1\0\0\0\u0081\u03af\1\0\0"+
		"\0\u0082\u03ec\1\0\0\0\u0083\u039f\1\0\0\0\u0083\u03af\1\0\0\0\u0083\u03d0"+
		"\1\0\0\0\u0084\u03fe\1\0\0\0\u0085\u039f\1\0\0\0\u0085\u03af\1\0\0\0\u0086"+
		"\u0400\1\0\0\0\u0087\u039d\1\0\0\0\u0087\u03ad\1\0\0\0\u0087\u03f7\1\0"+
		"\0\0\u0087\u03fd\1\0\0\0\u0088\u041b\1\0\0\0\u0089\u040a\1\0\0\0\u0089"+
		"\u0409\1\0\0\0\u008a\u0423\1\0\0\0\u008b\u0096\1\0\0\0\u008b\u00cf\1\0"+
		"\0\0\u008b\u00ee\1\0\0\0\u008b\u00f1\1\0\0\0\u008b\u00f5\1\0\0\0\u008b"+
		"\u0108\1\0\0\0\u008b\u0216\1\0\0\0\u008b\u021f\1\0\0\0\u008b\u0224\1\0"+
		"\0\0\u008b\u0298\1\0\0\0\u008b\u02be\1\0\0\0\u008b\u0310\1\0\0\0\u008b"+
		"\u032d\1\0\0\0\u008b\u0336\1\0\0\0\u008b\u0374\1\0\0\0\u008b\u0413\1\0"+
		"\0\0\u008b\u042d\1\0\0\0\u008b\u042c\1\0\0\0\u008c\u0425\1\0\0\0\u008d"+
		"\u00dc\1\0\0\0\u008d\u028d\1\0\0\0\u008d\u028c\1\0\0\0\u008d\u041c\1\0"+
		"\0\0\u008d\u041a\1\0\0\0\u008e\u0091\5\5\0\0\u0090\u008e\1\0\0\0\u0090"+
		"\u0091\1\0\0\0\u0091\u0092\1\0\0\0\u0092\u0094\3\2\1\0\u0094\u0096\3\u008a"+
		"E\0\u0096\u009c\5 \0\0\u0098\u009b\3\4\2\0\u009a\u0098\1\0\0\0\u009b\u009e"+
		"\1\0\0\0\u009c\u009a\1\0\0\0\u009c\u009d\1\0\0\0\u009d\u009f\1\0\0\0\u009e"+
		"\u009c\1\0\0\0\u009f\u00a5\3,\26\0\u00a1\u00a4\3*\25\0\u00a3\u00a1\1\0"+
		"\0\0\u00a4\u00a7\1\0\0\0\u00a5\u00a3\1\0\0\0\u00a5\u00a6\1\0\0\0\u00a6"+
		"\u00a8\1\0\0\0\u00a7\u00a5\1\0\0\0\u00a8\u00a9\5\uffff\0\0\u00a9\1\1\0"+
		"\0\0\u00aa\u00ac\5\20\0\0\u00ac\u00b5\5\22\0\0\u00ae\u00b0\5\21\0\0\u00b0"+
		"\u00b5\5\22\0\0\u00b2\u00b5\5\22\0\0\u00b4\u00aa\1\0\0\0\u00b4\u00ae\1"+
		"\0\0\0\u00b4\u00b2\1\0\0\0\u00b5\3\1\0\0\0\u00b6\u00bf\3\6\3\0\u00b8\u00bf"+
		"\3\f\6\0\u00ba\u00bf\3\20\b\0\u00bc\u00bf\3&\23\0\u00be\u00b6\1\0\0\0"+
		"\u00be\u00b8\1\0\0\0\u00be\u00ba\1\0\0\0\u00be\u00bc\1\0\0\0\u00bf\5\1"+
		"\0\0\0\u00c0\u00c8\5\f\0\0\u00c2\u00c4\3\b\4\0\u00c4\u00c5\5 \0\0\u00c5"+
		"\u00c7\1\0\0\0\u00c6\u00c2\1\0\0\0\u00c7\u00ca\1\0\0\0\u00c8\u00c6\1\0"+
		"\0\0\u00c8\u00c9\1\0\0\0\u00c9\u00cb\1\0\0\0\u00ca\u00c8\1\0\0\0\u00cb"+
		"\u00cc\5\63\0\0\u00cc\7\1\0\0\0\u00cd\u00cf\3\u008aE\0\u00cf\u00d1\5&"+
		"\0\0\u00d1\u00d2\3\n\5\0\u00d2\t\1\0\0\0\u00d3\u00dc\3\u008cF\0\u00d5"+
		"\u00dc\5\66\0\0\u00d7\u00dc\5\65\0\0\u00d9\u00dc\5(\0\0\u00db\u00d3\1"+
		"\0\0\0\u00db\u00d5\1\0\0\0\u00db\u00d7\1\0\0\0\u00db\u00d9\1\0\0\0\u00dc"+
		"\13\1\0\0\0\u00dd\u00df\5\16\0\0\u00df\u00e7\3\16\7\0\u00e1\u00e3\5\37"+
		"\0\0\u00e3\u00e6\3\16\7\0\u00e5\u00e1\1\0\0\0\u00e6\u00e9\1\0\0\0\u00e7"+
		"\u00e5\1\0\0\0\u00e7\u00e8\1\0\0\0\u00e8\u00ea\1\0\0\0\u00e9\u00e7\1\0"+
		"\0\0\u00ea\u00eb\5 \0\0\u00eb\r\1\0\0\0\u00ec\u00ee\3\u008aE\0\u00ee\u00f0"+
		"\5&\0\0\u00f0\u00f1\3\u008aE\0\u00f1\u00f5\1\0\0\0\u00f2\u00f5\3\u008a"+
		"E\0\u00f4\u00ec\1\0\0\0\u00f4\u00f2\1\0\0\0\u00f5\17\1\0\0\0\u00f6\u00fa"+
		"\5\r\0\0\u00f8\u00fb\3\22\t\0\u00fa\u00f8\1\0\0\0\u00fb\u00fc\1\0\0\0"+
		"\u00fc\u00fa\1\0\0\0\u00fc\u00fd\1\0\0\0\u00fd\u00fe\1\0\0\0\u00fe\u00ff"+
		"\5\63\0\0\u00ff\21\1\0\0\0\u0100\u0108\3\u008aE\0\u0102\u0104\5&\0\0\u0104"+
		"\u0109\5\66\0\0\u0106\u0109\1\0\0\0\u0108\u0102\1\0\0\0\u0108\u0106\1"+
		"\0\0\0\u0109\u010a\1\0\0\0\u010a\u010b\5 \0\0\u010b\u010f\1\0\0\0\u010c"+
		"\u010f\5\4\0\0\u010e\u0100\1\0\0\0\u010e\u010c\1\0\0\0\u010f\23\1\0\0"+
		"\0\u0110\u0142\5\13\0\0\u0112\u0141\3\24\n\0\u0114\u0141\3\26\13\0\u0116"+
		"\u0141\3\30\f\0\u0118\u0141\5Y\0\0\u011a\u0141\5Z\0\0\u011c\u0141\5V\0"+
		"\0\u011e\u0141\5W\0\0\u0120\u0141\5X\0\0\u0122\u0141\5H\0\0\u0124\u0141"+
		"\5I\0\0\u0126\u0141\5J\0\0\u0128\u0141\5K\0\0\u012a\u0141\5L\0\0\u012c"+
		"\u0141\5M\0\0\u012e\u0141\5N\0\0\u0130\u0141\5O\0\0\u0132\u0141\5S\0\0"+
		"\u0134\u0141\5T\0\0\u0136\u0141\5U\0\0\u0138\u0141\5Q\0\0\u013a\u0141"+
		"\5P\0\0\u013c\u0141\5R\0\0\u013e\u0141\5G\0\0\u0140\u0112\1\0\0\0\u0140"+
		"\u0114\1\0\0\0\u0140\u0116\1\0\0\0\u0140\u0118\1\0\0\0\u0140\u011a\1\0"+
		"\0\0\u0140\u011c\1\0\0\0\u0140\u011e\1\0\0\0\u0140\u0120\1\0\0\0\u0140"+
		"\u0122\1\0\0\0\u0140\u0124\1\0\0\0\u0140\u0126\1\0\0\0\u0140\u0128\1\0"+
		"\0\0\u0140\u012a\1\0\0\0\u0140\u012c\1\0\0\0\u0140\u012e\1\0\0\0\u0140"+
		"\u0130\1\0\0\0\u0140\u0132\1\0\0\0\u0140\u0134\1\0\0\0\u0140\u0136\1\0"+
		"\0\0\u0140\u0138\1\0\0\0\u0140\u013a\1\0\0\0\u0140\u013c\1\0\0\0\u0140"+
		"\u013e\1\0\0\0\u0141\u0144\1\0\0\0\u0142\u0140\1\0\0\0\u0142\u0143\1\0"+
		"\0\0\u0143\u0145\1\0\0\0\u0144\u0142\1\0\0\0\u0145\u0146\5[\0\0\u0146"+
		"\25\1\0\0\0\u0147\u014d\5U\0\0\u0149\u014c\3$\22\0\u014b\u0149\1\0\0\0"+
		"\u014c\u014f\1\0\0\0\u014d\u014b\1\0\0\0\u014d\u014e\1\0\0\0\u014e\u0150"+
		"\1\0\0\0\u014f\u014d\1\0\0\0\u0150\u0156\5G\0\0\u0152\u0155\3$\22\0\u0154"+
		"\u0152\1\0\0\0\u0155\u0158\1\0\0\0\u0156\u0154\1\0\0\0\u0156\u0157\1\0"+
		"\0\0\u0157\u0159\1\0\0\0\u0158\u0156\1\0\0\0\u0159\u015a\5T\0\0\u015a"+
		"\27\1\0\0\0\u015b\u0161\5U\0\0\u015d\u0160\3$\22\0\u015f\u015d\1\0\0\0"+
		"\u0160\u0163\1\0\0\0\u0161\u015f\1\0\0\0\u0161\u0162\1\0\0\0\u0162\u018a"+
		"\1\0\0\0\u0163\u0161\1\0\0\0\u0164\u016a\5L\0\0\u0166\u0169\3$\22\0\u0168"+
		"\u0166\1\0\0\0\u0169\u016c\1\0\0\0\u016a\u0168\1\0\0\0\u016a\u016b\1\0"+
		"\0\0\u016b\u0176\1\0\0\0\u016c\u016a\1\0\0\0\u016d\u0173\5R\0\0\u016f"+
		"\u0172\3$\22\0\u0171\u016f\1\0\0\0\u0172\u0175\1\0\0\0\u0173\u0171\1\0"+
		"\0\0\u0173\u0174\1\0\0\0\u0174\u0177\1\0\0\0\u0175\u0173\1\0\0\0\u0176"+
		"\u016d\1\0\0\0\u0176\u0177\1\0\0\0\u0177\u0178\1\0\0\0\u0178\u017e\5T"+
		"\0\0\u017a\u017d\3$\22\0\u017c\u017a\1\0\0\0\u017d\u0180\1\0\0\0\u017e"+
		"\u017c\1\0\0\0\u017e\u017f\1\0\0\0\u017f\u0181\1\0\0\0\u0180\u017e\1\0"+
		"\0\0\u0181\u0187\5M\0\0\u0183\u0186\3$\22\0\u0185\u0183\1\0\0\0\u0186"+
		"\u0189\1\0\0\0\u0187\u0185\1\0\0\0\u0187\u0188\1\0\0\0\u0188\u018b\1\0"+
		"\0\0\u0189\u0187\1\0\0\0\u018a\u0164\1\0\0\0\u018a\u018b\1\0\0\0\u018b"+
		"\u018c\1\0\0\0\u018c\u0192\5P\0\0\u018e\u0191\3$\22\0\u0190\u018e\1\0"+
		"\0\0\u0191\u0194\1\0\0\0\u0192\u0190\1\0\0\0\u0192\u0193\1\0\0\0\u0193"+
		"\u0195\1\0\0\0\u0194\u0192\1\0\0\0\u0195\u0196\5T\0\0\u0196\31\1\0\0\0"+
		"\u0197\u019d\5\n\0\0\u0199\u019c\7\0\0\0\u019b\u0199\1\0\0\0\u019c\u019f"+
		"\1\0\0\0\u019d\u019b\1\0\0\0\u019d\u019e\1\0\0\0\u019e\u01a0\1\0\0\0\u019f"+
		"\u019d\1\0\0\0\u01a0\u01a1\5E\0\0\u01a1\33\1\0\0\0\u01a2\u01a8\5\n\0\0"+
		"\u01a4\u01a7\3$\22\0\u01a6\u01a4\1\0\0\0\u01a7\u01aa\1\0\0\0\u01a8\u01a6"+
		"\1\0\0\0\u01a8\u01a9\1\0\0\0\u01a9\u01cb\1\0\0\0\u01aa\u01a8\1\0\0\0\u01ab"+
		"\u01b1\3\36\17\0\u01ad\u01b0\3$\22\0\u01af\u01ad\1\0\0\0\u01b0\u01b3\1"+
		"\0\0\0\u01b1\u01af\1\0\0\0\u01b1\u01b2\1\0\0\0\u01b2\u01c8\1\0\0\0\u01b3"+
		"\u01b1\1\0\0\0\u01b4\u01ba\5>\0\0\u01b6\u01b9\3$\22\0\u01b8\u01b6\1\0"+
		"\0\0\u01b9\u01bc\1\0\0\0\u01ba\u01b8\1\0\0\0\u01ba\u01bb\1\0\0\0\u01bb"+
		"\u01bd\1\0\0\0\u01bc\u01ba\1\0\0\0\u01bd\u01c3\3\36\17\0\u01bf\u01c2\3"+
		"$\22\0\u01c1\u01bf\1\0\0\0\u01c2\u01c5\1\0\0\0\u01c3\u01c1\1\0\0\0\u01c3"+
		"\u01c4\1\0\0\0\u01c4\u01c7\1\0\0\0\u01c5\u01c3\1\0\0\0\u01c6\u01b4\1\0"+
		"\0\0\u01c7\u01ca\1\0\0\0\u01c8\u01c6\1\0\0\0\u01c8\u01c9\1\0\0\0\u01c9"+
		"\u01cc\1\0\0\0\u01ca\u01c8\1\0\0\0\u01cb\u01ab\1\0\0\0\u01cb\u01cc\1\0"+
		"\0\0\u01cc\u01cd\1\0\0\0\u01cd\u01ce\5E\0\0\u01ce\35\1\0\0\0\u01cf\u01d2"+
		"\3 \20\0\u01d1\u01cf\1\0\0\0\u01d1\u01d2\1\0\0\0\u01d2\u01d7\1\0\0\0\u01d3"+
		"\u01d6\3$\22\0\u01d5\u01d3\1\0\0\0\u01d6\u01d9\1\0\0\0\u01d7\u01d5\1\0"+
		"\0\0\u01d7\u01d8\1\0\0\0\u01d8\u01da\1\0\0\0\u01d9\u01d7\1\0\0\0\u01da"+
		"\u01db\5@\0\0\u01db\37\1\0\0\0\u01dc\u01e9\3\"\21\0\u01de\u01e1\3$\22"+
		"\0\u01e0\u01de\1\0\0\0\u01e1\u01e4\1\0\0\0\u01e2\u01e0\1\0\0\0\u01e2\u01e3"+
		"\1\0\0\0\u01e3\u01e5\1\0\0\0\u01e4\u01e2\1\0\0\0\u01e5\u01e8\3\"\21\0"+
		"\u01e7\u01e2\1\0\0\0\u01e8\u01eb\1\0\0\0\u01e9\u01e7\1\0\0\0\u01e9\u01ea"+
		"\1\0\0\0\u01ea!\1\0\0\0\u01eb\u01e9\1\0\0\0\u01ec\u01ff\5@\0\0\u01ee\u01f2"+
		"\59\0\0\u01f0\u01f3\3 \20\0\u01f2\u01f0\1\0\0\0\u01f2\u01f3\1\0\0\0\u01f3"+
		"\u01f4\1\0\0\0\u01f4\u01ff\5:\0\0\u01f6\u01fa\5;\0\0\u01f8\u01fb\3 \20"+
		"\0\u01fa\u01f8\1\0\0\0\u01fa\u01fb\1\0\0\0\u01fb\u01fc\1\0\0\0\u01fc\u01ff"+
		"\5<\0\0\u01fe\u01ec\1\0\0\0\u01fe\u01ee\1\0\0\0\u01fe\u01f6\1\0\0\0\u01ff"+
		"#\1\0\0\0\u0200\u020b\5Y\0\0\u0202\u020b\5Z\0\0\u0204\u020b\5V\0\0\u0206"+
		"\u020b\5C\0\0\u0208\u020b\5D\0\0\u020a\u0200\1\0\0\0\u020a\u0202\1\0\0"+
		"\0\u020a\u0204\1\0\0\0\u020a\u0206\1\0\0\0\u020a\u0208\1\0\0\0\u020b%"+
		"\1\0\0\0\u020c\u0212\5\60\0\0\u020e\u0210\3(\24\0\u0210\u0211\5\36\0\0"+
		"\u0211\u0213\1\0\0\0\u0212\u020e\1\0\0\0\u0212\u0213\1\0\0\0\u0213\u0214"+
		"\1\0\0\0\u0214\u0216\3\u008aE\0\u0216\u0217\3\24\n\0\u0217\'\1\0\0\0\u0218"+
		"\u021f\3\u008aE\0\u021a\u021f\5\20\0\0\u021c\u021f\5\21\0\0\u021e\u0218"+
		"\1\0\0\0\u021e\u021a\1\0\0\0\u021e\u021c\1\0\0\0\u021f)\1\0\0\0\u0220"+
		"\u0222\5\34\0\0\u0222\u0224\3\u008aE\0\u0224\u0228\5 \0\0\u0226\u0229"+
		"\3.\27\0\u0228\u0226\1\0\0\0\u0229\u022a\1\0\0\0\u022a\u0228\1\0\0\0\u022a"+
		"\u022b\1\0\0\0\u022b+\1\0\0\0\u022c\u022f\3.\27\0\u022e\u022c\1\0\0\0"+
		"\u022f\u0232\1\0\0\0\u0230\u022e\1\0\0\0\u0230\u0231\1\0\0\0\u0231-\1"+
		"\0\0\0\u0232\u0230\1\0\0\0\u0233\u0238\3\60\30\0\u0235\u0238\3N\'\0\u0237"+
		"\u0233\1\0\0\0\u0237\u0235\1\0\0\0\u0238/\1\0\0\0\u0239\u023c\5\5\0\0"+
		"\u023b\u0239\1\0\0\0\u023b\u023c\1\0\0\0\u023c\u023f\1\0\0\0\u023d\u0240"+
		"\3D\"\0\u023f\u023d\1\0\0\0\u023f\u0240\1\0\0\0\u0240\u0241\1\0\0\0\u0241"+
		"\u0245\5\4\0\0\u0243\u0246\3\34\16\0\u0245\u0243\1\0\0\0\u0245\u0246\1"+
		"\0\0\0\u0246\u0249\1\0\0\0\u0247\u024a\3<\36\0\u0249\u0247\1\0\0\0\u0249"+
		"\u024a\1\0\0\0\u024a\u024d\1\0\0\0\u024b\u024e\3>\37\0\u024d\u024b\1\0"+
		"\0\0\u024d\u024e\1\0\0\0\u024e\u0251\1\0\0\0\u024f\u0252\3@ \0\u0251\u024f"+
		"\1\0\0\0\u0251\u0252\1\0\0\0\u0252\u0253\1\0\0\0\u0253\u0255\38\34\0\u0255"+
		"\u0257\5\35\0\0\u0257\u0259\3H$\0\u0259\u025b\5 \0\0\u025b\u025c\3\62"+
		"\31\0\u025c\61\1\0\0\0\u025d\u0260\3\64\32\0\u025f\u025d\1\0\0\0\u0260"+
		"\u0263\1\0\0\0\u0261\u025f\1\0\0\0\u0261\u0262\1\0\0\0\u0262\u0266\1\0"+
		"\0\0\u0263\u0261\1\0\0\0\u0264\u0267\3\66\33\0\u0266\u0264\1\0\0\0\u0266"+
		"\u0267\1\0\0\0\u0267\63\1\0\0\0\u0268\u026a\5\31\0\0\u026a\u026c\3\32"+
		"\r\0\u026c\u026d\3\24\n\0\u026d\65\1\0\0\0\u026e\u0270\5\32\0\0\u0270"+
		"\u0271\3\24\n\0\u0271\67\1\0\0\0\u0272\u0275\3:\35\0\u0274\u0272\1\0\0"+
		"\0\u0275\u0278\1\0\0\0\u0276\u0274\1\0\0\0\u0276\u0277\1\0\0\0\u02779"+
		"\1\0\0\0\u0278\u0276\1\0\0\0\u0279\u027e\3\6\3\0\u027b\u027e\3B!\0\u027d"+
		"\u0279\1\0\0\0\u027d\u027b\1\0\0\0\u027e;\1\0\0\0\u027f\u0281\5\26\0\0"+
		"\u0281\u0282\3\34\16\0\u0282=\1\0\0\0\u0283\u0285\5\30\0\0\u0285\u028d"+
		"\3\u008cF\0\u0287\u0289\5\37\0\0\u0289\u028c\3\u008cF\0\u028b\u0287\1"+
		"\0\0\0\u028c\u028f\1\0\0\0\u028d\u028b\1\0\0\0\u028d\u028e\1\0\0\0\u028e"+
		"?\1\0\0\0\u028f\u028d\1\0\0\0\u0290\u0292\5\27\0\0\u0292\u0293\3\34\16"+
		"\0\u0293A\1\0\0\0\u0294\u0296\5\60\0\0\u0296\u0298\3\u008aE\0\u0298\u0299"+
		"\3\24\n\0\u0299C\1\0\0\0\u029a\u029d\3F#\0\u029c\u029a\1\0\0\0\u029d\u029e"+
		"\1\0\0\0\u029e\u029c\1\0\0\0\u029e\u029f\1\0\0\0\u029fE\1\0\0\0\u02a0"+
		"\u02a9\5\24\0\0\u02a2\u02a9\5\25\0\0\u02a4\u02a9\5\23\0\0\u02a6\u02a9"+
		"\5\17\0\0\u02a8\u02a0\1\0\0\0\u02a8\u02a2\1\0\0\0\u02a8\u02a4\1\0\0\0"+
		"\u02a8\u02a6\1\0\0\0\u02a9G\1\0\0\0\u02aa\u02ab\3J%\0\u02abI\1\0\0\0\u02ac"+
		"\u02b4\3L&\0\u02ae\u02b0\5+\0\0\u02b0\u02b3\3L&\0\u02b2\u02ae\1\0\0\0"+
		"\u02b3\u02b6\1\0\0\0\u02b4\u02b2\1\0\0\0\u02b4\u02b5\1\0\0\0\u02b5K\1"+
		"\0\0\0\u02b6\u02b4\1\0\0\0\u02b7\u02bd\3f\63\0\u02b9\u02bb\5\61\0\0\u02bb"+
		"\u02be\3\u008aE\0\u02bd\u02b9\1\0\0\0\u02bd\u02be\1\0\0\0\u02beM\1\0\0"+
		"\0\u02bf\u02c2\5\5\0\0\u02c1\u02bf\1\0\0\0\u02c1\u02c2\1\0\0\0\u02c2\u02c5"+
		"\1\0\0\0\u02c3\u02c6\5\17\0\0\u02c5\u02c3\1\0\0\0\u02c5\u02c6\1\0\0\0"+
		"\u02c6\u02c7\1\0\0\0\u02c7\u02c9\5\3\0\0\u02c9\u02cb\5\35\0\0\u02cb\u02cd"+
		"\3P(\0\u02cd\u02ce\5 \0\0\u02ceO\1\0\0\0\u02cf\u02d0\3R)\0\u02d0Q\1\0"+
		"\0\0\u02d1\u02d9\3T*\0\u02d3\u02d5\5+\0\0\u02d5\u02d8\3T*\0\u02d7\u02d3"+
		"\1\0\0\0\u02d8\u02db\1\0\0\0\u02d9\u02d7\1\0\0\0\u02d9\u02da\1\0\0\0\u02da"+
		"S\1\0\0\0\u02db\u02d9\1\0\0\0\u02dc\u02e2\3V+\0\u02de\u02e3\3^/\0\u02e0"+
		"\u02e3\1\0\0\0\u02e2\u02de\1\0\0\0\u02e2\u02e0\1\0\0\0\u02e3U\1\0\0\0"+
		"\u02e4\u02e7\3X,\0\u02e6\u02e4\1\0\0\0\u02e7\u02e8\1\0\0\0\u02e8\u02e6"+
		"\1\0\0\0\u02e8\u02e9\1\0\0\0\u02e9W\1\0\0\0\u02ea\u02f0\3Z-\0\u02ec\u02f1"+
		"\3r9\0\u02ee\u02f1\1\0\0\0\u02f0\u02ec\1\0\0\0\u02f0\u02ee\1\0\0\0\u02f1"+
		"\u0309\1\0\0\0\u02f2\u02f8\3t:\0\u02f4\u02f9\3r9\0\u02f6\u02f9\1\0\0\0"+
		"\u02f8\u02f4\1\0\0\0\u02f8\u02f6\1\0\0\0\u02f9\u0309\1\0\0\0\u02fa\u0300"+
		"\3\\.\0\u02fc\u0301\3r9\0\u02fe\u0301\1\0\0\0\u0300\u02fc\1\0\0\0\u0300"+
		"\u02fe\1\0\0\0\u0301\u0309\1\0\0\0\u0302\u0306\3\24\n\0\u0304\u0307\5"+
		"\'\0\0\u0306\u0304\1\0\0\0\u0306\u0307\1\0\0\0\u0307\u0309\1\0\0\0\u0308"+
		"\u02ea\1\0\0\0\u0308\u02f2\1\0\0\0\u0308\u02fa\1\0\0\0\u0308\u0302\1\0"+
		"\0\0\u0309Y\1\0\0\0\u030a\u0310\3\u008aE\0\u030c\u0311\5&\0\0\u030e\u0311"+
		"\5*\0\0\u0310\u030c\1\0\0\0\u0310\u030e\1\0\0\0\u0311\u0316\1\0\0\0\u0312"+
		"\u0317\3t:\0\u0314\u0317\3~?\0\u0316\u0312\1\0\0\0\u0316\u0314\1\0\0\0"+
		"\u0317[\1\0\0\0\u0318\u031a\5!\0\0\u031a\u031c\3R)\0\u031c\u031d\5\"\0"+
		"\0\u031d]\1\0\0\0\u031e\u0320\5#\0\0\u0320\u0328\3`\60\0\u0322\u0324\5"+
		"\37\0\0\u0324\u0327\3`\60\0\u0326\u0322\1\0\0\0\u0327\u032a\1\0\0\0\u0328"+
		"\u0326\1\0\0\0\u0328\u0329\1\0\0\0\u0329_\1\0\0\0\u032a\u0328\1\0\0\0"+
		"\u032b\u032d\3\u008aE\0\u032d\u032f\5!\0\0\u032f\u0331\3b\61\0\u0331\u0332"+
		"\5\"\0\0\u0332\u0336\1\0\0\0\u0333\u0336\3\u008aE\0\u0335\u032b\1\0\0"+
		"\0\u0335\u0333\1\0\0\0\u0336a\1\0\0\0\u0337\u033c\5\64\0\0\u0339\u033c"+
		"\5\65\0\0\u033b\u0337\1\0\0\0\u033b\u0339\1\0\0\0\u033cc\1\0\0\0\u033d"+
		"\u0345\3f\63\0\u033f\u0341\5+\0\0\u0341\u0344\3f\63\0\u0343\u033f\1\0"+
		"\0\0\u0344\u0347\1\0\0\0\u0345\u0343\1\0\0\0\u0345\u0346\1\0\0\0\u0346"+
		"e\1\0\0\0\u0347\u0345\1\0\0\0\u0348\u034d\3h\64\0\u034a\u034d\1\0\0\0"+
		"\u034c\u0348\1\0\0\0\u034c\u034a\1\0\0\0\u034dg\1\0\0\0\u034e\u0351\3"+
		"j\65\0\u0350\u034e\1\0\0\0\u0351\u0352\1\0\0\0\u0352\u0350\1\0\0\0\u0352"+
		"\u0353\1\0\0\0\u0353i\1\0\0\0\u0354\u035a\3l\66\0\u0356\u035b\3r9\0\u0358"+
		"\u035b\1\0\0\0\u035a\u0356\1\0\0\0\u035a\u0358\1\0\0\0\u035b\u036d\1\0"+
		"\0\0\u035c\u0362\3v;\0\u035e\u0363\3r9\0\u0360\u0363\1\0\0\0\u0362\u035e"+
		"\1\0\0\0\u0362\u0360\1\0\0\0\u0363\u036d\1\0\0\0\u0364\u036d\3n\67\0\u0366"+
		"\u036a\3\24\n\0\u0368\u036b\5\'\0\0\u036a\u0368\1\0\0\0\u036a\u036b\1"+
		"\0\0\0\u036b\u036d\1\0\0\0\u036c\u0354\1\0\0\0\u036c\u035c\1\0\0\0\u036c"+
		"\u0364\1\0\0\0\u036c\u0366\1\0\0\0\u036dk\1\0\0\0\u036e\u0374\3\u008a"+
		"E\0\u0370\u0375\5&\0\0\u0372\u0375\5*\0\0\u0374\u0370\1\0\0\0\u0374\u0372"+
		"\1\0\0\0\u0375\u037a\1\0\0\0\u0376\u037b\3v;\0\u0378\u037b\3~?\0\u037a"+
		"\u0376\1\0\0\0\u037a\u0378\1\0\0\0\u037bm\1\0\0\0\u037c\u0382\3~?\0\u037e"+
		"\u0383\3p8\0\u0380\u0383\1\0\0\0\u0382\u037e\1\0\0\0\u0382\u0380\1\0\0"+
		"\0\u0383o\1\0\0\0\u0384\u0385\3r9\0\u0385q\1\0\0\0\u0386\u038d\5\'\0\0"+
		"\u0388\u038d\5(\0\0\u038a\u038d\5)\0\0\u038c\u0386\1\0\0\0\u038c\u0388"+
		"\1\0\0\0\u038c\u038a\1\0\0\0\u038ds\1\0\0\0\u038e\u039f\3\u0082A\0\u0390"+
		"\u039f\3\u0084B\0\u0392\u039f\5\4\0\0\u0394\u039f\3x<\0\u0396\u039f\3"+
		"\32\r\0\u0398\u039c\5-\0\0\u039a\u039d\3\u0086C\0\u039c\u039a\1\0\0\0"+
		"\u039c\u039d\1\0\0\0\u039d\u039f\1\0\0\0\u039e\u038e\1\0\0\0\u039e\u0390"+
		"\1\0\0\0\u039e\u0392\1\0\0\0\u039e\u0394\1\0\0\0\u039e\u0396\1\0\0\0\u039e"+
		"\u0398\1\0\0\0\u039fu\1\0\0\0\u03a0\u03af\3\u0082A\0\u03a2\u03af\3\u0084"+
		"B\0\u03a4\u03af\3\u0080@\0\u03a6\u03af\3x<\0\u03a8\u03ac\5-\0\0\u03aa"+
		"\u03ad\3\u0086C\0\u03ac\u03aa\1\0\0\0\u03ac\u03ad\1\0\0\0\u03ad\u03af"+
		"\1\0\0\0\u03ae\u03a0\1\0\0\0\u03ae\u03a2\1\0\0\0\u03ae\u03a4\1\0\0\0\u03ae"+
		"\u03a6\1\0\0\0\u03ae\u03a8\1\0\0\0\u03afw\1\0\0\0\u03b0\u03b2\5\62\0\0"+
		"\u03b2\u03b9\3|>\0\u03b4\u03b6\5\62\0\0\u03b6\u03b9\3z=\0\u03b8\u03b0"+
		"\1\0\0\0\u03b8\u03b4\1\0\0\0\u03b9y\1\0\0\0\u03ba\u03bc\5!\0\0\u03bc\u03c4"+
		"\3|>\0\u03be\u03c0\5+\0\0\u03c0\u03c3\3|>\0\u03c2\u03be\1\0\0\0\u03c3"+
		"\u03c6\1\0\0\0\u03c4\u03c2\1\0\0\0\u03c4\u03c5\1\0\0\0\u03c5\u03c7\1\0"+
		"\0\0\u03c6\u03c4\1\0\0\0\u03c7\u03c8\5\"\0\0\u03c8{\1\0\0\0\u03c9\u03d0"+
		"\5\3\0\0\u03cb\u03d0\5\66\0\0\u03cd\u03d0\3\u0082A\0\u03cf\u03c9\1\0\0"+
		"\0\u03cf\u03cb\1\0\0\0\u03cf\u03cd\1\0\0\0\u03d0}\1\0\0\0\u03d1\u03e0"+
		"\5!\0\0\u03d3\u03d6\3\6\3\0\u03d5\u03d3\1\0\0\0\u03d5\u03d6\1\0\0\0\u03d6"+
		"\u03db\1\0\0\0\u03d7\u03da\3B!\0\u03d9\u03d7\1\0\0\0\u03da\u03dd\1\0\0"+
		"\0\u03db\u03d9\1\0\0\0\u03db\u03dc\1\0\0\0\u03dc\u03de\1\0\0\0\u03dd\u03db"+
		"\1\0\0\0\u03de\u03e1\5\35\0\0\u03e0\u03d5\1\0\0\0\u03e0\u03e1\1\0\0\0"+
		"\u03e1\u03e2\1\0\0\0\u03e2\u03e4\3d\62\0\u03e4\u03e5\5\"\0\0\u03e5\177"+
		"\1\0\0\0\u03e6\u03ea\5\4\0\0\u03e8\u03eb\3\32\r\0\u03ea\u03e8\1\0\0\0"+
		"\u03ea\u03eb\1\0\0\0\u03eb\u0081\1\0\0\0\u03ec\u03ee\5\66\0\0\u03ee\u03f0"+
		"\5.\0\0\u03f0\u03f1\5\66\0\0\u03f1\u0083\1\0\0\0\u03f2\u03f6\5\3\0\0\u03f4"+
		"\u03f7\3\u0086C\0\u03f6\u03f4\1\0\0\0\u03f6\u03f7\1\0\0\0\u03f7\u03ff"+
		"\1\0\0\0\u03f8\u03fc\5\66\0\0\u03fa\u03fd\3\u0086C\0\u03fc\u03fa\1\0\0"+
		"\0\u03fc\u03fd\1\0\0\0\u03fd\u03ff\1\0\0\0\u03fe\u03f2\1\0\0\0\u03fe\u03f8"+
		"\1\0\0\0\u03ff\u0085\1\0\0\0\u0400\u0402\5$\0\0\u0402\u040a\3\u0088D\0"+
		"\u0404\u0406\5\37\0\0\u0406\u0409\3\u0088D\0\u0408\u0404\1\0\0\0\u0409"+
		"\u040c\1\0\0\0\u040a\u0408\1\0\0\0\u040a\u040b\1\0\0\0\u040b\u040d\1\0"+
		"\0\0\u040c\u040a\1\0\0\0\u040d\u040e\5%\0\0\u040e\u0087\1\0\0\0\u040f"+
		"\u041c\3\u008cF\0\u0411\u0413\3\u008aE\0\u0413\u0419\5&\0\0\u0415\u041a"+
		"\3\u008cF\0\u0417\u041a\5\66\0\0\u0419\u0415\1\0\0\0\u0419\u0417\1\0\0"+
		"\0\u041a\u041c\1\0\0\0\u041b\u040f\1\0\0\0\u041b\u0411\1\0\0\0\u041c\u0089"+
		"\1\0\0\0\u041d\u0424\5\4\0\0\u041f\u0424\5\3\0\0\u0421\u0424\5\33\0\0"+
		"\u0423\u041d\1\0\0\0\u0423\u041f\1\0\0\0\u0423\u0421\1\0\0\0\u0424\u008b"+
		"\1\0\0\0\u0425\u042d\3\u008aE\0\u0427\u0429\5-\0\0\u0429\u042c\3\u008a"+
		"E\0\u042b\u0427\1\0\0\0\u042c\u042f\1\0\0\0\u042d\u042b\1\0\0\0\u042d"+
		"\u042e\1\0\0\0\u042e\u008d\1\0\0\0\u042f\u042d\1\0\0\0h\u0090\1\u009c"+
		"\1\u00a5\1\u00b4\1\u00be\1\u00c8\1\u00db\1\u00e7\1\u00f4\1\u00fc\1\u0108"+
		"\1\u010e\1\u0140\1\u0142\1\u014d\1\u0156\1\u0161\1\u016a\1\u0173\1\u0176"+
		"\1\u017e\1\u0187\1\u018a\1\u0192\1\u019d\1\u01a8\1\u01b1\1\u01ba\1\u01c3"+
		"\1\u01c8\1\u01cb\1\u01d1\1\u01d7\1\u01e2\1\u01e9\1\u01f2\1\u01fa\1\u01fe"+
		"\1\u020a\1\u0212\1\u021e\1\u022a\1\u0230\1\u0237\1\u023b\1\u023f\1\u0245"+
		"\1\u0249\1\u024d\1\u0251\1\u0261\1\u0266\1\u0276\1\u027d\1\u028d\1\u029e"+
		"\1\u02a8\1\u02b4\1\u02bd\1\u02c1\1\u02c5\1\u02d9\1\u02e2\1\u02e8\1\u02f0"+
		"\1\u02f8\1\u0300\1\u0306\1\u0308\1\u0310\1\u0316\1\u0328\1\u0335\1\u033b"+
		"\1\u0345\1\u034c\1\u0352\1\u035a\1\u0362\1\u036a\1\u036c\1\u0374\1\u037a"+
		"\1\u0382\1\u038c\1\u039c\1\u039e\1\u03ac\1\u03ae\1\u03b8\1\u03c4\1\u03cf"+
		"\1\u03d5\1\u03db\1\u03e0\1\u03ea\1\u03f6\1\u03fc\1\u03fe\1\u040a\1\u0419"+
		"\1\u041b\1\u0423\1\u042d\1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		//org.antlr.v4.tool.DOTGenerator dot = new org.antlr.v4.tool.DOTGenerator(null);
		//System.out.println(dot.getDOT(_ATN.decisionToState.get(0), ruleNames, false));
		//System.out.println(dot.getDOT(_ATN.ruleToStartState[2], ruleNames, false));
	}
}