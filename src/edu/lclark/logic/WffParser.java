package edu.lclark.logic;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
// Generated from src/edu/lclark/logic/Wff.g4 by ANTLR 4.2

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WffParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, FORALL=2, EXISTS=3, LEFTPAREN=4, RIGHTPAREN=5, BICONDITIONAL=6, 
		CONDITIONAL=7, CONJUNCTION=8, INCLUSIVE_OR=9, NEGATION=10, TRUTH=11, FALSITY=12, 
		LETTERS=13, WS=14, ErrorChar=15;
	public static final String[] tokenNames = {
		"<INVALID>", "'''", "FORALL", "EXISTS", "LEFTPAREN", "RIGHTPAREN", "BICONDITIONAL", 
		"CONDITIONAL", "CONJUNCTION", "INCLUSIVE_OR", "NEGATION", "TRUTH", "FALSITY", 
		"LETTERS", "WS", "ErrorChar"
	};
	public static final int
		RULE_formula = 0, RULE_biconditional = 1, RULE_conditional = 2, RULE_disjunction = 3, 
		RULE_conjunction = 4, RULE_negation = 5, RULE_parentheses = 6, RULE_atom = 7, 
		RULE_letters = 8;
	public static final String[] ruleNames = {
		"formula", "biconditional", "conditional", "disjunction", "conjunction", 
		"negation", "parentheses", "atom", "letters"
	};

	@Override
	public String getGrammarFileName() { return "Wff.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public WffParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormulaContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(WffParser.EOF, 0); }
		public BiconditionalContext biconditional() {
			return getRuleContext(BiconditionalContext.class,0);
		}
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(18); biconditional();
				}
				break;

			case 2:
				{
				setState(19); conditional();
				}
				break;
			}
			setState(22); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BiconditionalContext extends ParserRuleContext {
		public TerminalNode BICONDITIONAL(int i) {
			return getToken(WffParser.BICONDITIONAL, i);
		}
		public List<DisjunctionContext> disjunction() {
			return getRuleContexts(DisjunctionContext.class);
		}
		public List<TerminalNode> BICONDITIONAL() { return getTokens(WffParser.BICONDITIONAL); }
		public DisjunctionContext disjunction(int i) {
			return getRuleContext(DisjunctionContext.class,i);
		}
		public BiconditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_biconditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).enterBiconditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).exitBiconditional(this);
		}
	}

	public final BiconditionalContext biconditional() throws RecognitionException {
		BiconditionalContext _localctx = new BiconditionalContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_biconditional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24); disjunction();
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BICONDITIONAL) {
				{
				{
				setState(25); match(BICONDITIONAL);
				setState(26); disjunction();
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalContext extends ParserRuleContext {
		public List<DisjunctionContext> disjunction() {
			return getRuleContexts(DisjunctionContext.class);
		}
		public TerminalNode CONDITIONAL() { return getToken(WffParser.CONDITIONAL, 0); }
		public DisjunctionContext disjunction(int i) {
			return getRuleContext(DisjunctionContext.class,i);
		}
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).exitConditional(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_conditional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32); disjunction();
			setState(33); match(CONDITIONAL);
			setState(34); disjunction();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DisjunctionContext extends ParserRuleContext {
		public List<TerminalNode> INCLUSIVE_OR() { return getTokens(WffParser.INCLUSIVE_OR); }
		public List<ConjunctionContext> conjunction() {
			return getRuleContexts(ConjunctionContext.class);
		}
		public TerminalNode INCLUSIVE_OR(int i) {
			return getToken(WffParser.INCLUSIVE_OR, i);
		}
		public ConjunctionContext conjunction(int i) {
			return getRuleContext(ConjunctionContext.class,i);
		}
		public DisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).enterDisjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).exitDisjunction(this);
		}
	}

	public final DisjunctionContext disjunction() throws RecognitionException {
		DisjunctionContext _localctx = new DisjunctionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_disjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); conjunction();
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INCLUSIVE_OR) {
				{
				{
				setState(37); match(INCLUSIVE_OR);
				setState(38); conjunction();
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConjunctionContext extends ParserRuleContext {
		public NegationContext negation(int i) {
			return getRuleContext(NegationContext.class,i);
		}
		public List<TerminalNode> CONJUNCTION() { return getTokens(WffParser.CONJUNCTION); }
		public TerminalNode CONJUNCTION(int i) {
			return getToken(WffParser.CONJUNCTION, i);
		}
		public List<NegationContext> negation() {
			return getRuleContexts(NegationContext.class);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).exitConjunction(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_conjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44); negation();
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CONJUNCTION) {
				{
				{
				setState(45); match(CONJUNCTION);
				setState(46); negation();
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NegationContext extends ParserRuleContext {
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public TerminalNode NEGATION() { return getToken(WffParser.NEGATION, 0); }
		public NegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).exitNegation(this);
		}
	}

	public final NegationContext negation() throws RecognitionException {
		NegationContext _localctx = new NegationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_negation);
		try {
			setState(55);
			switch (_input.LA(1)) {
			case NEGATION:
				enterOuterAlt(_localctx, 1);
				{
				setState(52); match(NEGATION);
				setState(53); parentheses();
				}
				break;
			case LEFTPAREN:
			case TRUTH:
			case FALSITY:
			case LETTERS:
				enterOuterAlt(_localctx, 2);
				{
				setState(54); parentheses();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenthesesContext extends ParserRuleContext {
		public BiconditionalContext biconditional() {
			return getRuleContext(BiconditionalContext.class,0);
		}
		public TerminalNode RIGHTPAREN() { return getToken(WffParser.RIGHTPAREN, 0); }
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public TerminalNode LEFTPAREN() { return getToken(WffParser.LEFTPAREN, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public ParenthesesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parentheses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).enterParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).exitParentheses(this);
		}
	}

	public final ParenthesesContext parentheses() throws RecognitionException {
		ParenthesesContext _localctx = new ParenthesesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_parentheses);
		try {
			setState(65);
			switch (_input.LA(1)) {
			case LEFTPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(57); match(LEFTPAREN);
				setState(60);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(58); biconditional();
					}
					break;

				case 2:
					{
					setState(59); conditional();
					}
					break;
				}
				setState(62); match(RIGHTPAREN);
				}
				break;
			case TRUTH:
			case FALSITY:
			case LETTERS:
				enterOuterAlt(_localctx, 2);
				{
				setState(64); atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode FALSITY() { return getToken(WffParser.FALSITY, 0); }
		public LettersContext letters() {
			return getRuleContext(LettersContext.class,0);
		}
		public TerminalNode TRUTH() { return getToken(WffParser.TRUTH, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_atom);
		try {
			setState(70);
			switch (_input.LA(1)) {
			case TRUTH:
				enterOuterAlt(_localctx, 1);
				{
				setState(67); match(TRUTH);
				}
				break;
			case FALSITY:
				enterOuterAlt(_localctx, 2);
				{
				setState(68); match(FALSITY);
				}
				break;
			case LETTERS:
				enterOuterAlt(_localctx, 3);
				{
				setState(69); letters();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LettersContext extends ParserRuleContext {
		public TerminalNode LETTERS() { return getToken(WffParser.LETTERS, 0); }
		public LettersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).enterLetters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof WffListener ) ((WffListener)listener).exitLetters(this);
		}
	}

	public final LettersContext letters() throws RecognitionException {
		LettersContext _localctx = new LettersContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_letters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); match(LETTERS);
			setState(74);
			_la = _input.LA(1);
			if (_la==1) {
				{
				setState(73); match(1);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\21O\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\5\2"+
		"\27\n\2\3\2\3\2\3\3\3\3\3\3\7\3\36\n\3\f\3\16\3!\13\3\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\7\5*\n\5\f\5\16\5-\13\5\3\6\3\6\3\6\7\6\62\n\6\f\6\16\6\65"+
		"\13\6\3\7\3\7\3\7\5\7:\n\7\3\b\3\b\3\b\5\b?\n\b\3\b\3\b\3\b\5\bD\n\b\3"+
		"\t\3\t\3\t\5\tI\n\t\3\n\3\n\5\nM\n\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2"+
		"\2O\2\26\3\2\2\2\4\32\3\2\2\2\6\"\3\2\2\2\b&\3\2\2\2\n.\3\2\2\2\f9\3\2"+
		"\2\2\16C\3\2\2\2\20H\3\2\2\2\22J\3\2\2\2\24\27\5\4\3\2\25\27\5\6\4\2\26"+
		"\24\3\2\2\2\26\25\3\2\2\2\27\30\3\2\2\2\30\31\7\2\2\3\31\3\3\2\2\2\32"+
		"\37\5\b\5\2\33\34\7\b\2\2\34\36\5\b\5\2\35\33\3\2\2\2\36!\3\2\2\2\37\35"+
		"\3\2\2\2\37 \3\2\2\2 \5\3\2\2\2!\37\3\2\2\2\"#\5\b\5\2#$\7\t\2\2$%\5\b"+
		"\5\2%\7\3\2\2\2&+\5\n\6\2\'(\7\13\2\2(*\5\n\6\2)\'\3\2\2\2*-\3\2\2\2+"+
		")\3\2\2\2+,\3\2\2\2,\t\3\2\2\2-+\3\2\2\2.\63\5\f\7\2/\60\7\n\2\2\60\62"+
		"\5\f\7\2\61/\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\13\3"+
		"\2\2\2\65\63\3\2\2\2\66\67\7\f\2\2\67:\5\16\b\28:\5\16\b\29\66\3\2\2\2"+
		"98\3\2\2\2:\r\3\2\2\2;>\7\6\2\2<?\5\4\3\2=?\5\6\4\2><\3\2\2\2>=\3\2\2"+
		"\2?@\3\2\2\2@A\7\7\2\2AD\3\2\2\2BD\5\20\t\2C;\3\2\2\2CB\3\2\2\2D\17\3"+
		"\2\2\2EI\7\r\2\2FI\7\16\2\2GI\5\22\n\2HE\3\2\2\2HF\3\2\2\2HG\3\2\2\2I"+
		"\21\3\2\2\2JL\7\17\2\2KM\7\3\2\2LK\3\2\2\2LM\3\2\2\2M\23\3\2\2\2\13\26"+
		"\37+\639>CHL";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}