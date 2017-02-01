package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for AbsoluteValue
 * @author raoliang
 *
 */
public class AbsoluteValueTest {
	private UnaryOperator absolute;

	/**
	 * Called before each test case method
	 * @throws Exception throw a exception if the method failed
	 */
	@Before
	public void setUp() throws Exception {
		absolute = new AbsoluteValue();
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
		assertEquals(11.22, absolute.apply(-11.22), 0);
		assertEquals(21.21, absolute.apply(21.21), 0);
	}
	
	/**
	 * Test the toString() method
	 */
	@Test
	public void testToString(){
		assertTrue("| |".equals(absolute.toString()));
	}

}
