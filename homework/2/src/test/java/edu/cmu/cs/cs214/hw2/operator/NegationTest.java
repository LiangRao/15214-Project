package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for Negation
 * @author raoliang
 *
 */
public class NegationTest {
	private UnaryOperator negation;

	/**
	 * Called before each test case method
	 * @throws Exception throw a exception if the method failed
	 */
	@Before
	public void setUp() throws Exception {
		negation = new Negation();
	}

	/**
     * Called after each test case method.
     * @throws Exception  throw Exception when the method fail
     */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test the apply() method
	 */
	@Test
	public void testApply() {
		assertEquals(-123.0, negation.apply(123), 0);
	}
	
	/**
	 * Test the toString() method
	 */
	@Test 
	public void testToString(){
        assertTrue("-(Neg)".equals(negation.toString()));
	}
}
