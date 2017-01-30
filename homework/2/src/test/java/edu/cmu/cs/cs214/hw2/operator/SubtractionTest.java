package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SubtractionTest {
	private BinaryOperator subtraction;

	@Before
	public void setUp() throws Exception {
		subtraction = new Subtraction();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApply() {
		assertEquals(-11.0, subtraction.apply(-9, 2), 0);
	}

	@Test
	public void testSubtraction(){
         assertTrue("-".equals(subtraction.toString()));
	}
}
