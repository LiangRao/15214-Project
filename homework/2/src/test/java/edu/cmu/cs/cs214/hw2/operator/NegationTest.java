package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NegationTest {
	UnaryOperator negation;

	@Before
	public void setUp() throws Exception {
		negation = new Negation();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApply() {
		assertEquals(-123.0, negation.apply(123), 0);
	}
	
	@Test 
	public void testToString(){
        assertTrue("-(Neg)".equals(negation.toString()));
	}
}
