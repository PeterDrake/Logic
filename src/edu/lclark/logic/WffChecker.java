package edu.lclark.logic;

import org.antlr.v4.runtime.*;

/*import edu.lclark.logic.target.WffLexer;
import edu.lclark.logic.target.WffParser;
*/
public class WffChecker {

	private boolean wff = false;
	
	private ANTLRInputStream input;
	private CommonTokenStream tokens;
	private RuleContext tree;
	private Parser parser;
	private Lexer lexer;

	private WffCheckerListener errorListener;

	public String printTree() {
		try {
			return tree.toStringTree(parser);
		} catch (NullPointerException re) {
			// error
			return null;
		}
	}

	// Opens a dialogue box with the parser tree broken down
	public void guiTree() {
		try {
			tree.inspect(parser);
		} catch (NullPointerException re) {
			// error
		}
	}
	
	public void swapParserErrorHandling(Parser parser) {
		// Removes error listeners from the parser (gets rid of ANTLR error output!)
		parser.removeErrorListeners(); // remove ConsoleErrorListener

		// Throw exceptions instead of correcting errors
		parser.setErrorHandler(new WffCheckerErrorStrategy());

		setErrorListener(new WffCheckerListener());
		parser.addErrorListener(getErrorListener());
	}
		
	public String getErrors() {
		return errorListener.getErrors();
	}

	public int getErrorPositionInLine() {
		return errorListener.getErrorPositionInLine();
	}
	
	public WffCheckerListener getErrorListener() {
		return errorListener;
	}

	public void setErrorListener(WffCheckerListener errorListener) {
		this.errorListener = errorListener;
	}

	public ANTLRInputStream getInput() {
		return input;
	}

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

	public void setParser(Parser parser) {
		this.parser = parser;
	}

	public Lexer getLexer() {
		return lexer;
	}

	public void setLexer(Lexer lexer) {
		this.lexer = lexer;
	}

	public boolean isWff() {
		return wff;
	}

	public void setWff(boolean wff) {
		this.wff = wff;
	}
}
