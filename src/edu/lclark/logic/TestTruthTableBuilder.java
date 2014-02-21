package edu.lclark.logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTruthTableBuilder {

	TruthTableBuilder table = new TruthTableBuilder("p") ;
	
	@Test
	public void testNumLetters() {
		assertEquals(1, table.getNumLetters());
		// fail("Not yet implemented");
	}
	
	@Test
	public void testGetValue() {
		assertEquals(true, table.getValue(0, 0)) ;
	}

}
