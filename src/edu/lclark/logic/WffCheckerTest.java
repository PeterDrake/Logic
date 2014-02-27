package edu.lclark.logic;

import static org.junit.Assert.*;

import org.junit.*;

public class WffCheckerTest {

	WffChecker wc;
	WffChecker wc2;
	
	@Before
    public void setUp() {
		wc = new WffChecker();
		wc2 = new WffChecker();
	}
	
	@After
	public void tearDown() {
		wc = null;
		wc2 = null;
	}
	
	@Test
	public void WffChecker1() {
		assertTrue(wc.setInputString("p . q"));
	}
	
	@Test
	public void WffChecker2() {
		assertFalse(wc.setInputString("p .^ q"));
	}
	
	@Test
	public void WffChecker3() {
		assertFalse(wc.setInputString("p q"));
	}
	
	@Test
	public void WffChecker4() {
		assertTrue(wc.setInputString("-(-((p).(-(q))))"));
	}
	
	@Test
	public void WffChecker5() {
		assertEquals(wc.setInputString("-(-((p).(-(q))))"), wc2.setInputString("-(-(p.-q))"));
	}
	
	@Test
	public void WffChecker6() {
		assertTrue(wc.setInputString("p -> q -> r"));
	}
	
	@Test
	public void WffChecker7() {
		assertTrue(wc.setInputString("-p.q ⋁ r → s.t"));
	}

}
