package edu.lclark.logic;

import org.antlr.v4.runtime.*;

public class WffChecker {
	
	private ANTLRInputStream input;
	private wffLexer lexer;
	private CommonTokenStream tokens;
	private wffParser parser;
	private RuleContext tree;
	
	private VerboseListener errorListener;

	// This overrides the lexer's error messages
	public static class BailwffLexer extends wffLexer {
		public BailwffLexer(CharStream input) {
			super(input);
		}
		
		@Override
		public void recover(LexerNoViableAltException e) {
			throw new RuntimeException(e); // Bail out }
		}
	}

	public String printTree() {
		return tree.toStringTree(parser);
	}
	
	// Opens a dialogue box with the parser tree broken down
	
	public void guiTree() {
		tree.inspect(parser);
	}
	
	public String getErrors() {
		return errorListener.getErrorText();
	}
	
	/** 
	 * Basically a constructor, but because of the way
	 * ANTLR works we can't do that unless we pass a string
	 * to the constructor which is not optimal
	 */
	public boolean setInputString(String is) {
		input = new ANTLRInputStream(is);
		lexer = new wffLexer(input); 
		tokens = new CommonTokenStream(lexer); 
		parser = new wffParser(tokens);
		
		// Removes error listeners from the lexer (gets rid of ANTLR error output!)
		lexer.removeErrorListeners();
		
		// Removes error listeners from the parser (gets rid of ANTLR error output!)
		parser.removeErrorListeners();
		
		// Sets the error handler strategy BailErrorStrategy is an example
		// from the ANTLR reference, basically just throws exceptions
//		parser.setErrorHandler(new BailErrorStrategy());
		
		errorListener = new VerboseListener();
		parser.addErrorListener(errorListener);
		
		// There will be a RuntimeException if there is invalid syntax, so we catch it 
		try {
			tree = parser.prog();
		}
		catch (RuntimeException re) {
//			System.out.println("invalid");
			return false;
		}
		return true;
	}
	
	// Super basic test
	public static void main(String[] args) {
		WffChecker wc = new WffChecker();
		wc.setInputString("pq");
		System.out.println(wc.getErrors());
	}
}

