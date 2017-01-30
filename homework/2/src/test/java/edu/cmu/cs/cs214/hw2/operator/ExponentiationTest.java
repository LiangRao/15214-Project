package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExponentiationTest {
	private BinaryOperator exponentiation;

	@Before
	public void setUp() throws Exception {
		exponentiation = new Exponentiation();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApply() {
		assertEquals(39.0625, exponentiation.apply(2.5, 4), 0);
	}
	
	@Test
	public void testToString(){
        assertTrue("^".equals(exponentiation.toString()));

	}
}
