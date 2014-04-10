package edu.lclark.logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class QfWffCheckerTest {

	QfWffChecker wc;
	QfWffChecker wc2;

	@After
	public void tearDown() {
		wc = null;
		wc2 = null;
	}

	@Test
	public void WffChecker0() {
		wc = new QfWffChecker("Fx");
		wc.isWff();
	}

	@Test
	public void WffChecker1() {
		wc = new QfWffChecker("F₂xy");
		wc.isWff();
	}

	@Test
	public void WffChecker2() {
		wc = new QfWffChecker("(∀x) Fx");
		assertTrue(wc.isWff());
	}

	@Test
	public void WffChecker3() {
		wc = new QfWffChecker("(∀x) Fxy");
		assertTrue(wc.isWff());
	}

	@Test
	public void WffChecker4() {
		wc = new QfWffChecker("(∀x)(Fy)");
		assertTrue(wc.isWff());
	}

	@Test
	public void WffChecker5() {
		wc = new QfWffChecker("-((∀x)((∃y)(-Fxy)))");
		wc2 = new QfWffChecker("-(∀x)(∃y)-Fxy");
		assertEquals(wc.isWff(), wc2.isWff());
	}

	@Test
	public void WffChecker6() {
		wc = new QfWffChecker("(∃y) Fx");
		assertTrue(wc.isWff());
	}

	@Test
	public void WffChecker7() {
		wc = new QfWffChecker("-(∀x)(∃y) Fy");
		assertTrue(wc.isWff());
	}

	@Test
	public void WffChecker8() {
		wc = new QfWffChecker("(∀x) G(x) . (∃x) G(x)");
		assertTrue(wc.isWff());
	}

	@Test
	public void WffChecker9() {
		wc = new QfWffChecker("(∀x)G(x)F(x)");
		assertFalse(wc.isWff());
	}

	@Test
	public void WffChecker10() {
		wc = new QfWffChecker("F(∃x)");
		assertFalse(wc.isWff());
	}

	@Test
	public void WffChecker11() {
		wc = new QfWffChecker("Fx . p");
		assertTrue(wc.isWff());
	}
	
	@Test
	public void WffChecker12() {
		wc = new QfWffChecker("p");
		assertTrue(wc.isWff());
	}
	
	@Test
	public void WffChecker13() {
		wc = new QfWffChecker("∀xFx v (p -> q)");
		assertTrue(wc.isWff());
	}
	
	@Test
	public void WffChecker14() {
		wc = new QfWffChecker("..");
		assertFalse(wc.isWff());
	}
	
	@Test
	public void WffChecker15() {
		wc = new QfWffChecker("∀x Fx -p");
		assertFalse(wc.isWff());
	}
	
	@Test
	public void testRedundantQuantification() {
		wc = new QfWffChecker("Vx Fx");
		assertFalse(wc.containsRedundantQuantifiers());
		wc = new QfWffChecker("Vx #y Vx Fx");
		assertTrue(wc.containsRedundantQuantifiers());
	}
}
