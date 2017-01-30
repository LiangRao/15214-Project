package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw2.operator.Addition;
import edu.cmu.cs.cs214.hw2.operator.Multiplication;
import edu.cmu.cs.cs214.hw2.operator.Subtraction;

public class DerivativeExpressionTest {
	private DerivativeExpression derivativeExpression;

	@Before
	public void setUp() throws Exception {
		VariableExpression variable = new VariableExpression("x");
		variable.store(3.0);
		Expression exp = new BinaryExpression(variable, variable, new Multiplication());
		Expression exp2 = new BinaryExpression(variable, new NumExpression(3.0), new Multiplication());
		Expression exp3 = new BinaryExpression(exp, exp2, new Subtraction());
		Expression fn = new  BinaryExpression(exp3, new NumExpression(2), new Addition());
		derivativeExpression = new DerivativeExpression(fn, variable);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEval() {
		 assertEquals(3.0, derivativeExpression.eval(), 0.0001);
	}
	
	@Test
	public void testToString(){
		String exp = "((((x*x)-(x*3.0))+2.0))'(x=3.0)="+derivativeExpression.eval();
		String a=derivativeExpression.toString();
		assertTrue(exp.equals(derivativeExpression.toString()));
		
	}

}
