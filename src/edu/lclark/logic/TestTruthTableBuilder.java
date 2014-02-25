package edu.lclark.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTruthTableBuilder {
    
	TruthTableBuilder table;
    
	@Before public void initialize() {
	    table = new TruthTableBuilder("pqr");
	}
	
	
	public void testNumLetters() {
		assertEquals(3, table.getNumLetters());
		// fail("Not yet implemented");
	}
	
	@Test
	public void testGetValue() {
	    System.out.println(table.toString());
		assertEquals(true, table.getValue(0, 0));
		assertEquals(false, table.getValue(4, 0));
	}

}
