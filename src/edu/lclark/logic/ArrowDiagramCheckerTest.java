/**
 * 
 */
package edu.lclark.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author student
 * 
 */
public class ArrowDiagramCheckerTest {

	ArrowDiagramChecker checker;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		checker = new ArrowDiagramChecker("(∀x) (∃y) Fxy");
	}

	/**
	 * Test method for
	 * {@link edu.lclark.logic.ArrowDiagramChecker#setUD(java.lang.String)}.
	 */
	@Test
	public void testSetUD() {
		checker.setUD("UD = {1, 2, 3}");
		assertEquals(3, checker.getUD().size());
		assertTrue(checker.getUD().contains(1));
		assertTrue(checker.getUD().contains(2));
		assertTrue(checker.getUD().contains(3));
		assertFalse(checker.getUD().contains(4));
	}

	/**
	 * Test method for
	 * {@link edu.lclark.logic.ArrowDiagramChecker#makeArrows(java.lang.String)}
	 * .
	 */
	@Test
	public void testMakeArrows() {
		checker.setArrows("F = {(1,1), (2,2), (1,3)}");
		assertEquals(3, checker.getArrows().size());
		assertEquals(1, checker.getArrows().get(0).getStart());
		assertEquals(1, checker.getArrows().get(0).getEnd());

		assertEquals(2, checker.getArrows().get(1).getStart());
		assertEquals(2, checker.getArrows().get(1).getEnd());

		assertEquals(1, checker.getArrows().get(2).getStart());
		assertEquals(3, checker.getArrows().get(2).getEnd());

		//checker.setArrows("F = {(1,4)}");

	}

}
