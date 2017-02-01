package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw2.operator.AbsoluteValue;
import edu.cmu.cs.cs214.hw2.operator.Negation;

/**
 * Test for UnaryExpression
 * @author raoliang
 *
 */
public class UnaryExpressionTest {
	private UnaryExpression unaryExpression;
	private UnaryExpression unaryExpression2;

	/**
	 * Called before each test case method
	 * @throws Exception throw a exception if the method failed
	 */
	@Before
	public void setUp() throws Exception {
		unaryExpression = new UnaryExpression(new NumExpression(8), new Negation());
		unaryExpression2 = new UnaryExpression(new NumExpression(-6), new AbsoluteValue());
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
		assertEquals(-8.0, unaryExpression.eval(), 0);
		assertEquals(6.0, unaryExpression2.eval(), 0);
	}
	
	/**
	 * Test the toString() method
	 */
	@Test
	public void testToString(){
		String exp = "-(8.0)";
		//String aString = UnaryExpression.toString();
		String exp2 = "|-6.0|";
		assertTrue(exp.equals(unaryExpression.toString()));
		assertTrue(exp2.equals(unaryExpression2.toString()));
	}
}
