// Generated from src/edu/lclark/logic/Wff.g4 by ANTLR 4.2
package edu.lclark.logic;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WffLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, FORALL=2, EXISTS=3, LEFTPAREN=4, RIGHTPAREN=5, BICONDITIONAL=6, 
		CONDITIONAL=7, CONJUNCTION=8, INCLUSIVE_OR=9, NEGATION=10, TRUTH=11, FALSITY=12, 
		LETTERS=13, WS=14, ErrorChar=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'''", "FORALL", "EXISTS", "LEFTPAREN", "RIGHTPAREN", "BICONDITIONAL", 
		"CONDITIONAL", "CONJUNCTION", "INCLUSIVE_OR", "NEGATION", "TRUTH", "FALSITY", 
		"LETTERS", "WS", "ErrorChar"
	};
	public static final String[] ruleNames = {
		"T__0", "FORALL", "EXISTS", "LEFTPAREN", "RIGHTPAREN", "BICONDITIONAL", 
		"CONDITIONAL", "CONJUNCTION", "INCLUSIVE_OR", "NEGATION", "TRUTH", "FALSITY", 
		"LETTERS", "WS", "ErrorChar"
	};


	public WffLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Wff.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\21J\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7\61\n\7\3\b\3\b\3\b\3\b\5"+
		"\b\67\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\2\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21\3\2\r\4\2XX\u2202\u2202\4\2%%\u2205\u2205"+
		"\4\2**]]\4\2++__\5\2((\60\60``\4\2xx\u22c3\u22c3\5\2//\u0080\u0080\u00ae"+
		"\u00ae\4\2\63\63\u22a6\u22a6\4\2\62\62\u22a7\u22a7\5\2CGRVrv\5\2\13\f"+
		"\17\17\"\"M\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2"+
		"\2\5#\3\2\2\2\7%\3\2\2\2\t\'\3\2\2\2\13)\3\2\2\2\r\60\3\2\2\2\17\66\3"+
		"\2\2\2\218\3\2\2\2\23:\3\2\2\2\25<\3\2\2\2\27>\3\2\2\2\31@\3\2\2\2\33"+
		"B\3\2\2\2\35D\3\2\2\2\37H\3\2\2\2!\"\7)\2\2\"\4\3\2\2\2#$\t\2\2\2$\6\3"+
		"\2\2\2%&\t\3\2\2&\b\3\2\2\2\'(\t\4\2\2(\n\3\2\2\2)*\t\5\2\2*\f\3\2\2\2"+
		"+\61\7\u2196\2\2,-\7>\2\2-.\7/\2\2.\61\7@\2\2/\61\7\u2263\2\2\60+\3\2"+
		"\2\2\60,\3\2\2\2\60/\3\2\2\2\61\16\3\2\2\2\62\67\7\u2194\2\2\63\64\7/"+
		"\2\2\64\67\7@\2\2\65\67\7\u2285\2\2\66\62\3\2\2\2\66\63\3\2\2\2\66\65"+
		"\3\2\2\2\67\20\3\2\2\289\t\6\2\29\22\3\2\2\2:;\t\7\2\2;\24\3\2\2\2<=\t"+
		"\b\2\2=\26\3\2\2\2>?\t\t\2\2?\30\3\2\2\2@A\t\n\2\2A\32\3\2\2\2BC\t\13"+
		"\2\2C\34\3\2\2\2DE\t\f\2\2EF\3\2\2\2FG\b\17\2\2G\36\3\2\2\2HI\13\2\2\2"+
		"I \3\2\2\2\5\2\60\66\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}