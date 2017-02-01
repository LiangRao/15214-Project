package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for subtraction
 * @author raoliang
 *
 */
public class SubtractionTest {
	private BinaryOperator subtraction;

	/**
	 * Called before each test case method
	 * @throws Exception throw a exception if the method failed
	 */
	@Before
	public void setUp() throws Exception {
		subtraction = new Subtraction();
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
		assertEquals(-11.0, subtraction.apply(-9, 2), 0);
	}

	/**
	 * Test the toString() method
	 */
	@Test
	public void testSubtraction(){
         assertTrue("-".equals(subtraction.toString()));
	}
}
