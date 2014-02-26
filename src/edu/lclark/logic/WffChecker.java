package edu.lclark.logic;

import org.antlr.v4.runtime.*;

public class WffChecker {
	
	private ANTLRInputStream input;
	private wffLexer lexer;
	private CommonTokenStream tokens;
	private wffParser parser;
	private RuleContext tree;

	public static class BailwffLexer extends wffLexer {
		public BailwffLexer(CharStream input) {
			super(input);
		}

		public void recover(LexerNoViableAltException e) {
			throw new RuntimeException(e); // Bail out }
		}
	}
	
	public boolean isValidSyntax() {
		try {
			tree = parser.prog();
		}
		catch (RuntimeException re) {
			return false;
		}
		return true;
	}
	
	public void printTree() {
		System.out.println(tree.toStringTree(parser));
	}
	
	public void guiTree() {
		tree.inspect(parser);
	}
	
	public void setInputString(String is) {
		input = new ANTLRInputStream(is);
		lexer = new wffLexer(input); 
		tokens = new CommonTokenStream(lexer); 
		parser = new wffParser(tokens);
		parser.setErrorHandler(new BailErrorStrategy());
	}
}

