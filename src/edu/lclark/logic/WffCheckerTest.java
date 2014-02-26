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
		wc.setInputString("p . q");
		assertTrue(wc.isValidSyntax());
	}
	
	@Test
	public void WffChecker2() {
		wc.setInputString("p .^ q");
		assertFalse(wc.isValidSyntax());
	}
	
	@Test
	public void WffChecker3() {
		wc.setInputString("p q");
		assertFalse(wc.isValidSyntax());
	}
	
	@Test
	public void WffChecker4() {
		wc.setInputString("-(-((p).(-(q))))");
		assertTrue(wc.isValidSyntax());
	}
	
	@Test
	public void WffChecker5() {
		wc.setInputString("-(-((p).(-(q))))");
		wc2.setInputString("--(p.-q)");
		assertEquals(wc.isValidSyntax(), wc2.isValidSyntax());
	}

}
