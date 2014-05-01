package edu.lclark.logic;

import java.util.ArrayList;
import java.util.LinkedList;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class QfWffChecker extends WffChecker {
		
	ParseTreeWalker walker;
	
	/** Text of the potential Wff being checked. */
	private String text;
	
	// Basically a constructor, but because of the way
	// ANTLR works we can't do that unless we pass a string
	// to the constructor which is not optimal
	
	public QfWffChecker(String is) {
		text = is;
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

 		if (containsRedundantQuantifiers()) {
 			setErrors("Redundant quantifiers.");
 			return false;
 		}

		return true;
	}
	
	/** Returns true iff the same variable is quantified more than once. */
	public boolean containsRedundantQuantifiers() {	
		LinkedList<Character> formula = new LinkedList<Character>();
		ParseTree tree = new QfWffParser(new CommonTokenStream(new QfWffLexer(new ANTLRInputStream(text)))).formula();
		QfWffTreeListener listener = new QfWffTreeListener();
		new ParseTreeWalker().walk(listener, tree);
		ArrayList<Character> currentLeftNegations = listener.getCurrentLeftNegations();
//		System.out.println(currentLeftNegations.toString());
		for (char symbol : currentLeftNegations) {
			if (symbol == '(') {
				formula.add(symbol);
//				System.out.println("symbol = ( " + formula.toString());
			}
			if (symbol == ')') {
				if (formula.getLast() != '(' && !formula.isEmpty()) {
					formula.removeLast();
//					System.out.println("symbol = ) & r " + formula.toString());

				}
				formula.removeLast();	
//				System.out.println("symbol = ) " + formula.toString());

			}
			if (symbol != '(' && symbol != ')') {
				if (formula.contains(symbol)) {
					return true;
				}
				formula.add(symbol);
//				System.out.println("symbol = " + symbol + " " + formula.toString());
			}
		}
		return false;
	}
}

