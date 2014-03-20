package edu.lclark.logic;

import org.antlr.v4.runtime.*;

public class QfWffChecker extends WffChecker {
	
	private ANTLRInputStream input;
	private CommonTokenStream tokens;
	private QfLexer lexer;
	private QfParser parser;
	private RuleContext tree;
	
	// Basically a constructor, but because of the way
	// ANTLR works we can't do that unless we pass a string
	// to the constructor which is not optimal
	
	public boolean setInputString(String is) {
		input = new ANTLRInputStream(is);
		lexer = new QfLexer(input); 
		tokens = new CommonTokenStream(lexer); 
		parser = new QfParser(tokens);
		
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
		QfWffChecker qfc = new QfWffChecker();
		System.out.println(qfc.setInputString("(∀x) (Fx) -> (Gx)"));
		System.out.println(qfc.getErrors());
	}
}

