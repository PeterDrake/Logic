package edu.lclark.logic;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;

public class WffCheckerErrorStrategy extends DefaultErrorStrategy {

	@Override
	public void recover(Parser recognizer, RecognitionException e) {
		for (ParserRuleContext context = recognizer.getContext(); context != null; context = context
				.getParent()) {
			context.exception = e;
		}
		throw new ParseCancellationException(e);
	}

	@Override
	public Token recoverInline(Parser recognizer) throws RecognitionException {
		InputMismatchException e = new InputMismatchException(recognizer);
		for (ParserRuleContext context = recognizer.getContext(); context != null; context = context
				.getParent()) {
			context.exception = e;
		}
		throw new ParseCancellationException(e);
	}

	@Override
	public void reportNoViableAlternative(@NotNull Parser recognizer,
			@NotNull NoViableAltException e) {
		String msg = "";
		TokenStream tokens = recognizer.getInputStream();
		String input;
		if (tokens != null) {
			if (e.getStartToken().getType() == Token.EOF)
				input = "<EOF>";
			else
				input = tokens
						.getText(e.getStartToken(), e.getOffendingToken());
		} else {
			input = "<unknown input>";
		}
		/*if (input.){
			msg = ""
					+ escapeWSAndQuote(input);
		}*/
		//Checks for parentheis and bracket errors
		if (!input.startsWith("(") && input.endsWith(")")){
			msg = "Right parenthesis lacks mate "
					+ escapeWSAndQuote(input);	
		}
		if(!input.startsWith("[") && input.endsWith("]")){
			msg = "Right bracket lacks mate "
					+ escapeWSAndQuote(input);
		}
		if (input.startsWith("(") && !input.endsWith(")")){
			msg = "Left parenthesis is not closed"
					+ escapeWSAndQuote(input);
		}
		if(input.startsWith("[") && !input.endsWith("]")){
			msg = "Left bracket not closed "
					+ escapeWSAndQuote(input);
		}
		System.out.println(e.getOffendingToken().toString());
		
		recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
	}

	@Override
	protected void reportInputMismatch(@NotNull Parser recognizer,
			@NotNull InputMismatchException e) {
		String msg = "Grouping ambiguity";
		recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
	}


	// Recognition Exception:
	// No Viable Alt Exception:
	// Lexer No Viable Alt Exception:
	// Input Mismatch Exception:
	// Failed Predicate Exception:

}
