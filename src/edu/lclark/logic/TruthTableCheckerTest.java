package edu.lclark.logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TruthTableCheckerTest {

	boolean[] set1 = { true, true, false };
	TruthTableChecker checker = new TruthTableChecker("p q", set1);

	@Test
	public void compareTest() {
		boolean[] set2 = { true, true, false };
		boolean[] matches = { true, true, true };
		// System.out.println(checker.compare(set2).toString());
		for (int i = 0; i < matches.length; i++) {
			assertEquals(matches[i], checker.compare(set2)[i]);
		}
		set2[2] = true;
		matches[2] = false;
		for (int i = 0; i < matches.length; i++) {
			assertEquals(matches[i], checker.compare(set2)[i]);
		}

	}

	@Test
	public void conjunctionTest() {
		boolean[][] letterValues = { { true, false, true, false },
				{ true, true, false, false } };
		boolean[] correctValues = { true, false, false, false };
		for (int i = 0; i < correctValues.length; i++) {
			assertEquals(correctValues[i],
					checker.conjunction(2, letterValues, 0, 1)[i]);
		}
	}

	@Test
	public void disjunctionTest() {
		boolean[][] letterValues = { { true, false, true, false },
				{ true, true, false, false } };
		boolean[] correctValues = { true, true, true, false };
		for (int i = 0; i < correctValues.length; i++) {
			assertEquals(correctValues[i],
					checker.disjunction(2, letterValues, 0, 1)[i]);
		}
	}

	@Test
	public void conditionalTest() {
		boolean[][] letterValues = { { true, false, true, false },
				{ true, true, false, false } };
		boolean[] correctValues = { true, true, false, true };
		for (int i = 0; i < correctValues.length; i++) {
			assertEquals(correctValues[i],
					checker.conditional(2, letterValues, 0, 1)[i]);
		}
	}

	@Test
	public void biconditionalTest() {
		boolean[][] letterValues = { { true, false, true, false },
				{ true, true, false, false } };
		boolean[] correctValues = { true, false, false, true };
		for (int i = 0; i < correctValues.length; i++) {
			assertEquals(correctValues[i],
					checker.biconditional(2, letterValues, 0, 1)[i]);
		}
	}

	@Test
	public void evaluateFormula() {
		String formula = "p";
		boolean[] expectedValues = { true, false };
		for (int i = 0; i < expectedValues.length; i++) {
			assertEquals(expectedValues, checker.evaluateFormula(formula));
		}
	}
}