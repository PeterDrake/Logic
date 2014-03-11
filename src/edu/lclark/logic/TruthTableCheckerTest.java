package edu.lclark.logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TruthTableCheckerTest {

	boolean[][] calculatedValues = { { true, true, false, false },
			{ true, false, true, false } };
	char[] letters = { 'p', 'q' };
	TruthTableChecker checker = new TruthTableChecker("p . q",
			calculatedValues, letters);

	@Test
	public void getColumnCalculatedValuesTest() {
		boolean[] expected = { true, true, false, false };
		assertEquals(-1, checker.compare(expected, checker.getColumnCalculatedValues('p')));
		boolean[] expected1 = { true, false, true, false };
		assertEquals(-1, checker.compare(expected1, checker.getColumnCalculatedValues('q')));
	}

	@Test
	public void compareTest() {
		boolean[] set1 = { true, true, false, false };
		boolean[] set2 = { true, false, true, false };
		assertEquals(1, checker.compare(set1, set2));
	}

	@Test
	public void conjunctionTest() {
		boolean[] set1 = { true, true, false, false };
		boolean[] set2 = { true, false, true, false };
		boolean[] expected = { true, false, false, false };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], checker.conjunction(set1, set2)[i]);
		}
	}

	@Test
	public void disjunctionTest() {
		boolean[] set1 = { true, true, false, false };
		boolean[] set2 = { true, false, true, false };
		boolean[] expected = { true, true, true, false };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], checker.disjunction(set1, set2)[i]);
		}
	}

	@Test
	public void conditionalTest() {
		boolean[] set1 = { true, true, false, false };
		boolean[] set2 = { true, false, true, false };
		boolean[] expected = { true, false, true, true };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], checker.conditional(set1, set2)[i]);
		}
	}

	@Test
	public void biconditionalTest() {
		boolean[] set1 = { true, true, false, false };
		boolean[] set2 = { true, false, true, false };
		boolean[] expected = { true, false, false, true };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], checker.biconditional(set1, set2)[i]);
		}
	}

	@Test
	public void evaluateFormulaTest() {
		String formula = "p.q";
		boolean[] expectedValues = { true, false, false, false };
		assertEquals(-1, checker.compare(checker.evaluateFormula(formula),
				expectedValues));
	}
	
	@Test
	public void numOperatorsTest(){
		String string = "p . q" ;
		assertEquals(1, checker.numOperators(string));
		String string2 = "p.q v r";
		assertEquals(2, checker.numOperators(string2));
		String string3 = "p.q.r .s. t";
		assertEquals(4, checker.numOperators(string3));
		String string4 = "(q.r v (r v s . t) -> s.t) <-> (q.s.r.t)";
		assertEquals(10, checker.numOperators(string4));
	}
}