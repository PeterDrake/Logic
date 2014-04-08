package edu.lclark.logic;

import org.antlr.v4.runtime.*;

public class QfWffChecker extends WffChecker {
		
	// Basically a constructor, but because of the way
	// ANTLR works we can't do that unless we pass a string
	// to the constructor which is not optimal
	
	public QfWffChecker(String is) {
		setInput(new ANTLRInputStream(is));
		setLexer(new QfWffLexer(getInput())); 
		setTokens(new CommonTokenStream(getLexer())); 
		setParser(new QfWffParser(getTokens()));
		
		super.swapParserErrorHandling(getParser());
		
		setWff(checkWff());
	}
	
	public boolean checkWff() {
		// There will be a RuntimeException if there is invalid syntax, so we catch it 
		try {
			setTree(((QfWffParser) getParser()).formula());
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
//		QfWffChecker qfwc = new QfWffChecker("-((∀x)((∃y)(-Fxy)))");
		QfWffChecker qfwc = new QfWffChecker("-Fx");
		System.out.println(qfwc.isWff());
		System.out.println(qfwc.printTree());
//		System.out.println(qfwc.getErrors());
	}
}

