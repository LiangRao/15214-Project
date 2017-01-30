package edu.cmu.cs.cs214.hw2.termcalc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw2.expression.BinaryExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumExpression;
import edu.cmu.cs.cs214.hw2.expression.UnaryExpression;
import edu.cmu.cs.cs214.hw2.operator.AbsoluteValue;
import edu.cmu.cs.cs214.hw2.operator.Multiplication;
import edu.cmu.cs.cs214.hw2.operator.Subtraction;

public class ExpressionMakerImplTest {
	private ExpressionMakerImpl expressionMakerImpl;

	@Before
	public void setUp() throws Exception {
		expressionMakerImpl = new ExpressionMakerImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSumExpression() {
		Expression addend1 = new NumExpression(1.24);
	    Expression addend2 = new BinaryExpression(new NumExpression(123.4), new NumExpression(324.2), new Multiplication());		
		assertEquals(40007.52, expressionMakerImpl.sumExpression(addend1, addend2).eval(),0);
	}
	
	@Test
	public void testDifferenceExpression() {
		Expression op1 = new NumExpression(400.134);
	    Expression op2 = new BinaryExpression(new NumExpression(123.4), new NumExpression(324.2), new Multiplication());		
		assertEquals(-39606.146, expressionMakerImpl.differenceExpression(op1, op2).eval(),0);
	}
	
	@Test
	public void testProductExpression(){
		Expression factor1 = new BinaryExpression(new NumExpression(200.12), new NumExpression(5.1), new Subtraction());
		Expression exp = new UnaryExpression(new NumExpression(-9.2), new AbsoluteValue());
		Expression factor2 = new BinaryExpression(exp, new NumExpression(3), new Multiplication());
		assertEquals(5382.552, expressionMakerImpl.productExpression(factor1, factor2).eval(), 0);
	}
	
	@Test
	public void testDivisionExpression(){
		Expression divisor = new NumExpression(3);
		Expression dividend = new NumExpression(431.3);
	    assertEquals(143.767,expressionMakerImpl.divisionExpression(dividend, divisor).eval(), 0.001);		
	}
	
	@Test
	public void testExponentiationExpression(){
		Expression base = new NumExpression(14.1);
		Expression exponent = new NumExpression(3);	
		assertEquals(2803.221, expressionMakerImpl.exponentiationExpression(base, exponent).eval(),0);
	}
	
	@Test
	public void testNegationExpression(){
		Expression operand = new NumExpression(543.43);
		assertEquals(-543.43, expressionMakerImpl.negationExpression(operand).eval(), 0);
	}
	
	@Test
	public void testAbsoluteValueExpression(){
		Expression value = new NumExpression(-4.3321);
		assertEquals(4.3321, expressionMakerImpl.absoluteValueExpression(value).eval(), 0);
	}

	@Test
	public void testnumberExpression(){
		assertEquals(4.22133, expressionMakerImpl.numberExpression(4.22133).eval(), 0);
	}
    
}
