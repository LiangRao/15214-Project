package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw2.operator.AbsoluteValue;
import edu.cmu.cs.cs214.hw2.operator.Negation;

public class UnaryExpressionTest {
	private UnaryExpression unaryExpression;
	private UnaryExpression unaryExpression2;

	@Before
	public void setUp() throws Exception {
		unaryExpression = new UnaryExpression(new NumExpression(8), new Negation());
		unaryExpression2 = new UnaryExpression(new NumExpression(-6), new AbsoluteValue());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEval() {
		assertEquals(-8.0, unaryExpression.eval(), 0);
		assertEquals(6.0, unaryExpression2.eval(), 0);
	}

	@Test
	public void testToString(){
		String exp = "-(8.0)";
		//String aString = UnaryExpression.toString();
		String exp2 = "|-6.0|";
		assertTrue(exp.equals(unaryExpression.toString()));
		assertTrue(exp2.equals(unaryExpression2.toString()));
	}
}
