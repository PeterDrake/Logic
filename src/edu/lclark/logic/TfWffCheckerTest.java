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
		wc.isWff();
	}

	@Test
	public void WffChecker2() {
		wc = new TfWffChecker("p .^ q");
		assertFalse(wc.isWff());
	}

	@Test
	public void WffChecker3() {
		wc = new TfWffChecker("p q");
		assertFalse(wc.isWff());
	}

	@Test
	public void WffChecker4() {
		wc = new TfWffChecker("-(-((p).(-(q))))");
		assertTrue(wc.isWff());
	}

	@Test
	public void WffChecker5() {
		wc = new TfWffChecker("-(-((p).(-(q))))");
		wc2 = new TfWffChecker("-(-(p.-q))");
		assertEquals(wc.isWff(), wc2.isWff());
	}

	@Test
	public void WffChecker6() {
		wc = new TfWffChecker("p -> q -> r");
		assertFalse(wc.isWff());
	}

	@Test
	public void WffChecker7() {
		wc = new TfWffChecker("-p.q ⋁ r → s.t");
		assertTrue(wc.isWff());
	}

	@Test
	public void WffChecker8() {
		wc = new TfWffChecker("p -> q <-> r");
		assertFalse(wc.isWff());
	}

	@Test
	public void WffChecker9() {
		wc = new TfWffChecker("p ⋁ t → [(q → r.t) ↔ s]");
		wc2 = new TfWffChecker("p ⋁ t → [q → (r.t ↔ s)]");
		assertEquals(wc.isWff(), wc2.isWff());
	}

}
