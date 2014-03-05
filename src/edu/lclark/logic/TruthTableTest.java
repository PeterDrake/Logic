package edu.lclark.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TruthTableTest {
    
	TruthTable table;
    
	@Before public void initialize() {
	    table = new TruthTable("pqr");
	}

	public void testNumLetters() {
		assertEquals(3, table.getNumLetters());
		// fail("Not yet implemented");
	}
	
	@Test
	public void testGetValue() {
	    System.out.println(table.toString());
		assertEquals(true, table.getColumn(0).getValue(0));
		assertEquals(false, table.getColumn(4).getValue(0));
	}

}
