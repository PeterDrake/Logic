package edu.lclark.logic;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public class WffCheckerErrorStrategy extends DefaultErrorStrategy{

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
		String msg = "Negated constituent not a Wff or incorrect usage of negation";
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
		System.out.println(tokens.getText());
		System.out.println(input);
		//Checks for parentheis and bracket errors
		if (!tokens.getText().startsWith("(") && tokens.getText().endsWith(")")){
			msg += "Right parenthesis lacks mate "
					+ escapeWSAndQuote(input);	
		}else if (tokens.getText().startsWith("(") && !tokens.getText().endsWith(")")){
			msg += " and/or left parenthesis is not closed"
					+ escapeWSAndQuote(input);
		}
		if(tokens.getText().startsWith("[") && !tokens.getText().endsWith("]")){
			msg += " and/or Unnecessary brackets"
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
	
	@Override
	protected void reportUnwantedToken(@NotNull Parser recognizer) {
		if (inErrorRecoveryMode(recognizer)) {
			return;
		}

		beginErrorCondition(recognizer);

		Token t = recognizer.getCurrentToken();
		//String tokenName = getTokenErrorDisplay(t);
		//IntervalSet expecting = getExpectedTokens(recognizer);
		String msg = "Unnecessary brackets";
		recognizer.notifyErrorListeners(t, msg, null);
	}


	// Recognition Exception:
	// No Viable Alt Exception:
	// Lexer No Viable Alt Exception:
	// Input Mismatch Exception:
	// Failed Predicate Exception:

}
