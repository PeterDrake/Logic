package edu.lclark.logic;

import org.antlr.v4.runtime.*;

public class WffChecker {
	
	private ANTLRInputStream input;
	private CommonTokenStream tokens;
	private WffLexer lexer;
	private WffParser parser;
	private RuleContext tree;
		
	private WffCheckerListener errorListener;

	public String printTree() {
		return tree.toStringTree(parser);
	}
	
	// Opens a dialogue box with the parser tree broken down
	
	public void guiTree() {
			tree.inspect(parser);
	}
	
	public String getErrors() {
		return getErrorListener().getErrors();
	}
	
	public int getErrorPositionInLine() {
		return getErrorListener().getErrorPositionInLine();
	}
	
	// Basically a constructor, but because of the way
	// ANTLR works we can't do that unless we pass a string
	// to the constructor which is not optimal
	
	public boolean setInputString(String is) {
		input = new ANTLRInputStream(is);
		lexer = new WffLexer(input); 
		tokens = new CommonTokenStream(lexer); 
		parser = new WffParser(tokens);
		
		// Removes error listeners from the lexer (gets rid of ANTLR error output!)
		// lexer.removeErrorListeners(); // remove ConsoleErrorListener
		
		// Removes error listeners from the parser (gets rid of ANTLR error output!)
		parser.removeErrorListeners(); // remove ConsoleErrorListener
		
		// Sets the error handler strategy BailErrorStrategy is an example
		// from the ANTLR reference, basically just throws exceptions
		parser.setErrorHandler(new WffCheckerErrorStrategy());
		
		setErrorListener(new WffCheckerListener());
		parser.addErrorListener(getErrorListener());
		
		// There will be a RuntimeException if there is invalid syntax, so we catch it 
		try {
			tree = parser.formula();
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
		WffChecker wc = new WffChecker();
		System.out.println(wc.setInputString("p->q)"));
		System.out.println(wc.getErrors());
	}

	public WffCheckerListener getErrorListener() {
		return errorListener;
	}

	public void setErrorListener(WffCheckerListener errorListener) {
		this.errorListener = errorListener;
	}
}

