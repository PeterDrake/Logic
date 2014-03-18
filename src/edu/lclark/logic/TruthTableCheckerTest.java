package edu.lclark.logic;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		formula = "p.q.r";
	}
	
	@Test
	public void getTopLevelOperatorsTest() {
		String formula = "p.q";
		ArrayList<String> ops = new ArrayList<String>();
		ops.add(".");
		assertEquals(ops, checker.getTopLevelOperators(formula));
		formula = "(pvq) . (p->q) v s";
		ops = new ArrayList<String>();
		ops.add(".");
		ops.add("v");
		assertEquals(ops, checker.getTopLevelOperators(formula));
		formula = "(p.q) <-> r";
		ops = new ArrayList<String>();
		ops.add("<->");
		assertEquals(ops, checker.getTopLevelOperators(formula));
		formula = "(p.q) -> r";
		ops = new ArrayList<String>();
		ops.add("->");
		assertEquals(ops, checker.getTopLevelOperators(formula));
		formula = "(p.q) <-> -r";
		ops = new ArrayList<String>();
		ops.add("<->");
		ops.add("-");
		assertEquals(ops, checker.getTopLevelOperators(formula));
		
	}
	
	@Test
	public void isOperatorTest() {
		String op = ".";
		assertTrue(checker.isOperator(op));
	}

}