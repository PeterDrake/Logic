package edu.lclark.logic;

/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/

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
