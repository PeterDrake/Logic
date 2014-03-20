package edu.lclark.logic;

import org.antlr.v4.runtime.*;

public class WffChecker {
	
	private ANTLRInputStream input;
	private CommonTokenStream tokens;
	private RuleContext tree;
	private Parser parser;
		
	private WffCheckerListener errorListener;

	public String printTree() {
		return getTree().toStringTree(parser);
	}
	
	// Opens a dialogue box with the parser tree broken down
	public void guiTree() {
			getTree().inspect(parser);
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
		return getErrorListener().getErrors();
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
}

