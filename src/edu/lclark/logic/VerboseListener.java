package edu.lclark.logic;

import org.antlr.v4.runtime.*;
import java.util.*;



public class VerboseListener extends BaseErrorListener {

	String errtext = "Valid WFF";

	public String getErrorText() {
		return errtext;
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
		errtext = "line "+line+":"+charPositionInLine+" at "+
				offendingSymbol+": "+msg;
	}

}
