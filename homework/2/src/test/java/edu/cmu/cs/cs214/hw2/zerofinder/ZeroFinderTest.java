package edu.cmu.cs.cs214.hw2.zerofinder;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw2.expression.BinaryExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumExpression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;
import edu.cmu.cs.cs214.hw2.operator.Multiplication;
import edu.cmu.cs.cs214.hw2.operator.Subtraction;

/**
 * Test for ZeroFinder
 * @author raoliang
 *
 */
public class ZeroFinderTest {
	private ZeroFinder zeroFinder;

	/**
	 * Called before each test case method
	 * @throws Exception throw a exception if the method failed
	 */
	@Before
	public void setUp() throws Exception {
		zeroFinder = new ZeroFinder();
	}

	/**
     * Called after each test case method.
     * @throws Exception  throw Exception when the method fail
     */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test the zero() method
	 */
	@Test
	public void testZero() {
		VariableExpression x  = new VariableExpression("x");
		Expression exp =new BinaryExpression(x, x, new  Multiplication());
		Expression fn = new BinaryExpression(exp, new NumExpression(2), new Subtraction());
		double approxZero = 1;
		assertEquals(1.41, zeroFinder.zero(fn, x, approxZero, 0.01), 0.01);
	}

}
