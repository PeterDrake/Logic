package edu.lclark.logic;

import org.antlr.v4.runtime.*;

public class WffChecker {
	
	private ANTLRInputStream input;
	private CommonTokenStream tokens;
	private wffLexer lexer;
	private wffParser parser;
	private RuleContext tree;
		
	private VerboseListener errorListener;

	public String printTree() {
		return tree.toStringTree(parser);
	}
	
	// Opens a dialogue box with the parser tree broken down
	
	public void guiTree() {
		try {
			tree.inspect(parser);
		} catch (RuntimeException re) {
			
		}
	}
	
	public String getErrors() {
		return errorListener.getErrors();
	}
	
	// Basically a constructor, but because of the way
	// ANTLR works we can't do that unless we pass a string
	// to the constructor which is not optimal
	
	public boolean setInputString(String is) {
		input = new ANTLRInputStream(is);
		lexer = new wffLexer(input); 
		tokens = new CommonTokenStream(lexer); 
		parser = new wffParser(tokens);
		
		// Removes error listeners from the lexer (gets rid of ANTLR error output!)
//		lexer.removeErrorListeners(); // remove ConsoleErrorListener
		
		// Removes error listeners from the parser (gets rid of ANTLR error output!)
		parser.removeErrorListeners(); // remove ConsoleErrorListener
		
		// Sets the error handler strategy BailErrorStrategy is an example
		// from the ANTLR reference, basically just throws exceptions
		parser.setErrorHandler(new BailErrorStrategy());
		
		errorListener = new VerboseListener();
		parser.addErrorListener(errorListener);
		
		// There will be a RuntimeException if there is invalid syntax, so we catch it 
		try {
			tree = parser.prog();
		}
		catch (RuntimeException re) {
			return false;
		}
		return true;
	}
	
	// Super basic test
	public static void main(String[] args) {
		WffChecker wc = new WffChecker();
		System.out.println(wc.setInputString("pq"));
		System.out.println(wc.getErrors());
	}
}

