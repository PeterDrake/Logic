package edu.lclark.logic;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

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
	
//	Recognition Exception:
//	No Viable Alt Exception:
//	Lexer No Viable Alt Exception:
//	Input Mismatch Exception:
//	Failed Predicate Exception:

}
