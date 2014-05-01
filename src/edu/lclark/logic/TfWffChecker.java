package edu.lclark.logic;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class TfWffChecker extends WffChecker {
	
	/**
	 * Constructor for TfWffChecker.
	 * Sets a bunch of parameters defined in WffChecker.
	 * @param is
	 */
	public TfWffChecker(String is) {
		setInput(new ANTLRInputStream(is));
		setLexer(new TfWffLexer(getInput())); 
		setTokens(new CommonTokenStream(getLexer())); 
		setParser(new TfWffParser(getTokens()));
		
		super.swapParserErrorHandling(getParser());
		setWff(checkWff());
	}
	
	/**
	 * Checks whether the entered formula
	 * is a wff. Return based on that belief. 
	 */
	public boolean checkWff() {
		// There will be a RuntimeException if there is invalid syntax, so we catch it 
		try {
			setTree(((TfWffParser) getParser()).formula());
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
}
