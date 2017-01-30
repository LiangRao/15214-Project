package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DivisionTest {
	BinaryOperator division;

	@Before
	public void setUp() throws Exception {
		division = new Division();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEval() {
		assertEquals(3.0, division.apply(9, 3), 0);
		
	}
	
	@Test
	public void testToString(){
		assertTrue("/".equals(division.toString()));
	}

}
