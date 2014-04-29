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

		// a little awkward, but there is one weird case where paren checking is messed up
//		if (getErrors() != "The entered formula is a wff.") {
//			return false;
//		}
 		if (containsRedundantQuantifiers()) {
 			setErrors("Redundant quantifiers.");
 			return false;
 		}
//		if (parenthesesNotMatched()) {
//			setErrors("Parentheses mismatched.");
//			return false;
//		}
		return true;
	}
	
	/** Returns true iff the same variable is quantified more than once. */
//	public boolean containsRedundantQuantifiers() {
//		String formula = text;
//		String quantifiers = "∀ V ∃ #";
//		char variable;
//		int positionAt=0;
//		if (formula.contains("∀") || formula.contains("V") || formula.contains("∃") || formula.contains("#")) {
//			for (int i=0; !quantifiers.contains(formula.charAt(i) + ""); i++){
//				positionAt=i;
//				}
//			variable = formula.charAt(positionAt+1);
//			for (int i=positionAt+1; i<formula.length(); i++) {
//				
//			}
//			
//		}
//		return false;
//	}
	public boolean containsRedundantQuantifiers() {	
		LinkedList<Character> formula = new LinkedList<Character>();
		StringBox box = new StringBox();
		ParseTree tree = new QfWffParser(new CommonTokenStream(new QfWffLexer(new ANTLRInputStream(text)))).formula();
		QfWffTreeListener listener = new QfWffTreeListener(box);
		new ParseTreeWalker().walk(listener, tree);
		ArrayList<Character> currentLeftNegations = listener.getCurrentLeftNegations();
		System.out.println(currentLeftNegations.toString());
		for (char symbol : currentLeftNegations) {
			if (symbol == '(') {
				formula.add(symbol);
				System.out.println("symbol = ( " + formula.toString());
			}
			if (symbol == ')') {
				if (formula.getLast() != '(' && !formula.isEmpty()) {
					formula.removeLast();
					System.out.println("symbol = ) & r " + formula.toString());

				}
				formula.removeLast();	
				System.out.println("symbol = ) " + formula.toString());

			}
			if (symbol != '(' && symbol != ')') {
				if (formula.contains(symbol)) {
					return true;
				}
				formula.add(symbol);
				System.out.println("symbol = " + symbol + " " + formula.toString());
			}
		}
		return false;
	}
	
//	/** Returns true iff the there are mismatched parentheses. */
//	public boolean parenthesesNotMatched() {		
//		QfWffParser parser = new QfWffParser(new CommonTokenStream(new QfWffLexer(new ANTLRInputStream(text))));
//		parser.removeErrorListeners();
//		parser.setErrorHandler(new DefaultErrorStrategy());
//		
//		ParseTree tree = parser.formula();
////		System.out.println(tree.getText());
////		ParseTree tree = ((QfWffParser) getParser()).formula();
//		StringBox box = new StringBox();
//		QfWffTreeListener treeListener = new QfWffTreeListener(box);
//		new ParseTreeWalker().walk(treeListener, tree);
////		System.out.println(treeListener.parenthesesNotMatched());
//		return box.isSet();
//	}

	// Super basic test
	public static void main(String[] args) {
//		QfWffChecker qfwc = new QfWffChecker("(∀x)(∃z)[Hx.(∃y)(Fy.Gxy).(∃y)(Iy.Gxy)->(∃y)((Fy v Iy).Gyx)]");
		QfWffChecker qfwc = new QfWffChecker("(∀x)Hx.(∃y)((Fy).(∃y)(Iy))");
		System.out.println(qfwc.isWff());
		qfwc.guiTree();
		System.out.println(qfwc.getErrors());
	}
}

