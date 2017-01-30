package edu.cmu.cs.cs214.hw2.zerofinder;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw2.expression.BinaryExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumExpression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;
import edu.cmu.cs.cs214.hw2.operator.Multiplication;
import edu.cmu.cs.cs214.hw2.operator.Subtraction;

public class ZeroFinderTest {
	private ZeroFinder zeroFinder;

	@Before
	public void setUp() throws Exception {
		zeroFinder = new ZeroFinder();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testZero() {
		VariableExpression x  = new VariableExpression("x");
		Expression exp =new BinaryExpression(x, x, new  Multiplication());
		Expression fn = new BinaryExpression(exp, new NumExpression(2), new Subtraction());
		double approxZero = 1;
		zeroFinder.zero(fn, x, approxZero, 0.01);
		
	}

}
