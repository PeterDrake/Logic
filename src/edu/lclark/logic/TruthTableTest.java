package edu.lclark.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TruthTableTest {

	TruthTable table;

	@Before
	public void initialize() {
		table = new TruthTable("pqr");
	}

	@Test
	public void testNumLetters() {
		assertEquals(3, table.getNumLetters());
	}

	@Test
	public void testGetValue() {
		System.out.println(table.toString());
		assertEquals(true, table.getValue(0, 0));
		assertEquals(false, table.getValue(4, 0));
	}

}
