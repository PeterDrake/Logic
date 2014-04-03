package edu.lclark.logic;

import org.antlr.v4.runtime.*;

public class QfWffChecker extends WffChecker {
	
	private QfWffLexer lexer;
	private QfWffParser parser;
	
	// Basically a constructor, but because of the way
	// ANTLR works we can't do that unless we pass a string
	// to the constructor which is not optimal
	
	public QfWffChecker(String is) {
		setInput(new ANTLRInputStream(is));
		lexer = new QfWffLexer(getInput()); 
		setTokens(new CommonTokenStream(lexer)); 
		parser = new QfWffParser(getTokens());
		
		super.swapParserErrorHandling(parser);
	}
	
	public boolean checkWff() {
		// There will be a RuntimeException if there is invalid syntax, so we catch it 
		try {
			setTree(parser.formula());
		}
		catch (RuntimeException re) {
			return false;
		}

		// a little awkward, but there is one weird case where paren checking is messed up
		if (getErrors() != "The entered formula is a wff.") {
			return false;
		}

		return true;
	}
	
	// Super basic test
	public static void main(String[] args) {
		QfWffChecker qfwc = new QfWffChecker("(∀x) (Fx) -> (Gx)");
		System.out.println(qfwc.checkWff());
		System.out.println(qfwc.getErrors());
	}
}

