package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for Addition
 * @author raoliang
 *
 */
public class AdditionTest {
	private Addition addtion;

	/**
	 * Called before each test case method
	 * @throws Exception throw a exception if the method failed
	 */
	@Before
	public void setUp() throws Exception {
		addtion =new Addition();
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
		double sum = addtion.apply(100, 3);
		assertEquals(103.0, sum, 0);
	}
	
	/**
	 * Test the toString() method
	 */
	@Test
	public void testToString(){
		assertTrue("+".equals(addtion.toString()));
	}

}
