package edu.lclark.logic;

import org.antlr.v4.runtime.*;

/**
 * 
 * This is the basic WffChecker class.
 * A new instance of WffChecker should be created
 * to check new wffs. Actually checking wffs should
 * be done using the classes that extend WffChecker. 
 *
 */
public class WffChecker {

	/**
	 * Whether or not we believe the entered formula
	 * is a wff. Defaults to false.
	 */
	private boolean wff = false;
	
	private ANTLRInputStream input;
	private CommonTokenStream tokens;
	private RuleContext tree;
	private Parser parser;
	private Lexer lexer;

	private WffCheckerErrorListener errorListener;

	/**
	 * Prints out the tree for the given wff
	 */
	public void printTree() {
		try {
			System.out.println(tree.toStringTree(parser));
		} catch (NullPointerException re) {
			// error
		}
	}

	/**
	 * Uses ANTLR to open a dialogue box
	 * with the parse tree down
	 */
	public void guiTree() {
		try {
			tree.inspect(parser);
		} catch (NullPointerException re) {
			// error
		}
	}
	
	/**
	 * Swaps the error strategy and listener for the
	 * supplied parser with WffCheckerErrorStrategy
	 * and WffCheckerErrorListener
	 * @param parser
	 */
	public void swapParserErrorHandling(Parser parser) {
		// Removes error listeners from the parser (gets rid of ANTLR error output!)
		parser.removeErrorListeners(); // remove ConsoleErrorListener

		// Throw exceptions instead of correcting errors
		parser.setErrorHandler(new WffCheckerErrorStrategy());

		setErrorListener(new WffCheckerErrorListener());
		parser.addErrorListener(getErrorListener());
	}
	
	/**
	 * Returns a string from the error listener
	 * by default, the string is "The entered formula is a wff."
	 * if there are errors, the string will instead be
	 * filled with error text supplied by ANTLR
	 * Note: this only works with WffCheckerErrorListener
	 * @return String
	 */
	public String getErrors() {
		return errorListener.getErrors();
	}
	
	/**
	 * Sets the error text for the error listener to
	 * the given string
	 * Note: this only works with WffCheckerErrorListener
	 * @param errors
	 */
	public void setErrors(String errors) {
		errorListener.setErrors(errors);
	}

	/**
	 * Gets the position of the first error in the
	 * entered formula.
	 * @return int
	 */
	public int getErrorPositionInLine() {
		return errorListener.getErrorPositionInLine();
	}
	
	/**
	 * Returns the error listener instance that
	 * corresponds to this WffChecker instance
	 * @return WffCheckerErrorListener
	 */
	public WffCheckerErrorListener getErrorListener() {
		return errorListener;
	}

	/**
	 * Sets the error listener instance that
	 * corresponds to this WffChecker instance
	 * @param
	 */
	public void setErrorListener(WffCheckerErrorListener errorListener) {
		this.errorListener = errorListener;
	}

	/**
	 * Returns the formula that was input
	 * @return input
	 */
	public ANTLRInputStream getInput() {
		return input;
	}

	/**
	 * Sets the formula to be checked
	 * @param input
	 */
	public void setInput(ANTLRInputStream input) {
		this.input = input;
	}

	public CommonTokenStream getTokens() {
		return tokens;
	}

	public void setTokens(CommonTokenStream tokens) {
		this.tokens = tokens;
	}

	public RuleContext getTree() {
		return tree;
	}

	public void setTree(RuleContext tree) {
		this.tree = tree;
	}

	public Parser getParser() {
		return parser;
	}

	/**
	 * @param parser
	 */
	public void setParser(Parser parser) {
		this.parser = parser;
	}

	/**
	 * @return lexer
	 */
	public Lexer getLexer() {
		return lexer;
	}

	/**
	 * @param lexer
	 */
	public void setLexer(Lexer lexer) {
		this.lexer = lexer;
	}

	/**
	 * @return wff
	 */
	public boolean isWff() {
		return wff;
	}

	/**
	 * @param wff
	 */
	public void setWff(boolean wff) {
		this.wff = wff;
	}
}
