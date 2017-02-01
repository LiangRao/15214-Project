package edu.cmu.cs.cs214.hw2.expression;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw2.operator.Addition;

/**
 * A test for BinaryExpression
 * @author raoliang
 *
 */
public class BinaryExpressionTest {
	private BinaryExpression binaryExpression;

	/**
	 * Called before each test case method
	 * @throws Exception throw a exception if the method failed
	 */
	@Before
	public void setUp() throws Exception {
		binaryExpression = new BinaryExpression(new NumExpression(1),new NumExpression(2),new Addition());
	}

	 /**
     * Called after each test case method.
     * @throws Exception  throw Exception when the method fail
     */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test the eval() method
	 */
	@Test
	public void testEval() {
		double result = binaryExpression.eval();
		double except = 3.0;
         assertEquals(except, result, 0);
	}
	
	/**
	 * Test the toString() method
	 */
	@Test
	public void testToString(){
		String exp = "(1.0+2.0)"; 
		assertTrue(exp.equals(binaryExpression.toString()));
	}

}
