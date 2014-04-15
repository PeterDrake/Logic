package edu.lclark.logic;

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

		
//		super.swapParserErrorHandling(getParser());
		
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
//		if (getErrors() != "The entered formula is a wff.") {
//			return false;
//		}
		if (containsRedundantQuantifiers()) {
			return false;
		}
		return true;
	}
	
	/** Returns true iff the same variable is quantified more than once. */
	public boolean containsRedundantQuantifiers() {		
		ParseTree tree = new QfWffParser(new CommonTokenStream(new QfWffLexer(new ANTLRInputStream(text)))).formula();
		StringBox box = new StringBox();
		new ParseTreeWalker().walk(new QfWffTreeListener(box), tree);
		return box.isSet();
	}

	// Super basic test
	public static void main(String[] args) {
//		QfWffChecker qfwc = new QfWffChecker("-((∀x)((∃y)(-Fxy)))");
		QfWffChecker qfwc = new QfWffChecker("(∀x)(Fy)");
		System.out.println(qfwc.isWff());
		System.out.println(qfwc.printTree());
		System.out.println(qfwc.getErrors());
	}
}

