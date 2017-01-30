package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MultiplicationTest {
	private BinaryOperator multiplication;

	@Before
	public void setUp() throws Exception {
		multiplication = new Multiplication();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApply() {
		assertEquals(8.75, multiplication.apply(3.5, 2.5), 0);
	}
	
	@Test
	public void testToString(){
		assertTrue("*".equals(multiplication.toString()));
	}

}
