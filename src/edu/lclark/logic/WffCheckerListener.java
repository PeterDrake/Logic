package edu.lclark.logic;

import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.*;

public class WffCheckerListener extends BaseErrorListener {

	private String errors = "The entered formula is a wff.";
	private int errorPositionInLine = 0; 
	
	public int getErrorPositionInLine() {
		return errorPositionInLine;
	}
	public String getErrors() {		
		return errors;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer,
			Object offendingSymbol,
			int line, int charPositionInLine,
			String msg,
			RecognitionException e)
	{
		List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
		Collections.reverse(stack);
		errors = "charPosition :" + charPositionInLine + " " + msg;
		errorPositionInLine = charPositionInLine;
	}


}
