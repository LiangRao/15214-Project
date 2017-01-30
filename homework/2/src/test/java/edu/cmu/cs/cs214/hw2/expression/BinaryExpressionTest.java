package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw2.operator.Addition;

public class BinaryExpressionTest {
	private BinaryExpression binaryExpression;

	@Before
	public void setUp() throws Exception {
		binaryExpression = new BinaryExpression(new NumExpression(1),new NumExpression(2),new Addition());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEval() {
		double result = binaryExpression.eval();
		double except = 3.0;
         assertEquals(except, result, 0);
	}
	
	@Test
	public void testToString(){
		String exp = "(1.0+2.0)"; 
		assertTrue(exp.equals(binaryExpression.toString()));
	}

}
