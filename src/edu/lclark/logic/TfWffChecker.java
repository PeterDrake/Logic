package edu.lclark.logic;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class TfWffChecker extends WffChecker {
	
	private TfWffLexer lexer;
	private TfWffParser parser;
	
	public TfWffChecker(String is) {
		setInput(new ANTLRInputStream(is));
		lexer = new TfWffLexer(getInput()); 
		setTokens(new CommonTokenStream(lexer)); 
		parser = new TfWffParser(getTokens());
		
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
		TfWffChecker tfwc = new TfWffChecker("p->q)");
		System.out.println(tfwc.checkWff());
		System.out.println(tfwc.getErrors());
	}

	
}
