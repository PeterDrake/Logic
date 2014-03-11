package edu.lclark.logic;

import java.util.*;
import java.util.List;
import org.antlr.v4.runtime.*;

public class WffCheckerListener extends BaseErrorListener {

	public String errors = "The entered formula is a wff.";

	public String getErrors() {		
		return errors;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
		List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
		Collections.reverse(stack);
		errors = "Error at character position: " + charPositionInLine + ", " + msg;
	}
}