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
		wc.checkWff();
	}
	
	@Test
	public void WffChecker1() {
		wc = new QfWffChecker("(∀x) Fx");
		wc.checkWff();
	}

	@Test
	public void WffChecker2() {
		wc = new QfWffChecker("(∀xy) Fxy");
		assertTrue(wc.checkWff());
	}

	@Test
	public void WffChecker3() {
		wc = new QfWffChecker("(∀x) Fxy");
		assertFalse(wc.checkWff());
	}

	@Test
	public void WffChecker4() {
		wc = new QfWffChecker("(∀x)(Fy)");
		assertTrue(wc.checkWff());
	}

	@Test
	public void WffChecker5() {
		wc = new QfWffChecker("-((∀x)((∃y)(-Fxy)))");
		wc2 = new QfWffChecker("-(∀x)(∃y)-Fxy");
		assertEquals(wc.checkWff(), wc2.checkWff());
	}

	@Test
	public void WffChecker6() {
		wc = new QfWffChecker("(∃y) Fx");
		assertTrue(wc.checkWff());
	}

	@Test
	public void WffChecker7() {
		wc = new QfWffChecker("-(∀x)(∃y) Fy");
		assertTrue(wc.checkWff());
	}

	@Test
	public void WffChecker8() {
		wc = new QfWffChecker("(∀x) G(x) . (∃x) G(x)");
		assertTrue(wc.checkWff());
	}

	@Test
	public void WffChecker9() {
		wc = new QfWffChecker("(∀x)G(x)F(x)");
		assertFalse(wc.checkWff());
	}

	@Test
	public void WffChecker10() {
		wc = new QfWffChecker("F(∃x)");
		assertFalse(wc.checkWff());
	}

}
