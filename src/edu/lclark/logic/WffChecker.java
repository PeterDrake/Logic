package edu.lclark.logic;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class WffChecker {
	
	private ANTLRInputStream input;
	private wffLexer lexer;
	private CommonTokenStream tokens;
	private wffParser parser;

	public static class BailwffLexer extends wffLexer {
		public BailwffLexer(CharStream input) {
			super(input);
		}

		public void recover(LexerNoViableAltException e) {
			throw new RuntimeException(e); // Bail out }
		}
	}
	
	public boolean isValidSyntaxTree() {
		try {
			ParseTree tree = parser.prog();
			System.out.println(tree.toStringTree(parser));
		}
		catch (RuntimeException re) {
			System.out.println("Invalid syntax");
			return false;
		}
		return true;
	}
	
	public boolean isValidSyntax() {
		try {
			parser.prog();
		}
		catch (RuntimeException re) {
			System.out.println("Invalid syntax");
			return false;
		}
		return true;
	}
	
	public WffChecker(String is) {
		input = new ANTLRInputStream(is); 
		lexer = new wffLexer(input); 
		tokens = new CommonTokenStream(lexer); 
		parser = new wffParser(tokens);
		//parser.setErrorHandler(new BailErrorStrategy());
	}
	
	public static void main(String[] args) throws Exception {
		
		ANTLRInputStream input;
		wffLexer lexer;
		CommonTokenStream tokens;
		wffParser parser;
		
		String is = "pvq";
		
		input = new ANTLRInputStream(is); 
		lexer = new wffLexer(input); 
		tokens = new CommonTokenStream(lexer); 
		parser = new wffParser(tokens);
		parser.setErrorHandler(new BailErrorStrategy());
		try {
		parser.prog();
		}
		catch (RuntimeException re) {
			System.out.println("Invalid syntax");
		}
	}
}

