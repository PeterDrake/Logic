package edu.lclark.logic;

import static org.junit.Assert.*;

import org.junit.*;

public class TfWffCheckerTest {

	TfWffChecker wc;
	TfWffChecker wc2;

	@After
	public void tearDown() {
		wc = null;
		wc2 = null;
	}

	@Test
	public void WffChecker1() {
		wc = new TfWffChecker("p . q");
		wc.checkWff();
	}

	@Test
	public void WffChecker2() {
		wc = new TfWffChecker("p .^ q");
		assertFalse(wc.checkWff());
	}

	@Test
	public void WffChecker3() {
		wc = new TfWffChecker("p q");
		assertFalse(wc.checkWff());
	}

	@Test
	public void WffChecker4() {
		wc = new TfWffChecker("-(-((p).(-(q))))");
		assertTrue(wc.checkWff());
	}

	@Test
	public void WffChecker5() {
		wc = new TfWffChecker("-(-((p).(-(q))))");
		wc2 = new TfWffChecker("-(-(p.-q))");
		assertEquals(wc.checkWff(), wc2.checkWff());
	}

	@Test
	public void WffChecker6() {
		wc = new TfWffChecker("p -> q -> r");
		assertFalse(wc.checkWff());
	}

	@Test
	public void WffChecker7() {
		wc = new TfWffChecker("-p.q ⋁ r → s.t");
		assertTrue(wc.checkWff());
	}

	@Test
	public void WffChecker8() {
		wc = new TfWffChecker("p -> q <-> r");
		assertFalse(wc.checkWff());
	}

	@Test
	public void WffChecker9() {
		wc = new TfWffChecker("p ⋁ t → [(q → r.t) ↔ s]");
		wc2 = new TfWffChecker("p ⋁ t → [q → (r.t ↔ s)]");
		assertEquals(wc.checkWff(), wc2.checkWff());
	}

}
