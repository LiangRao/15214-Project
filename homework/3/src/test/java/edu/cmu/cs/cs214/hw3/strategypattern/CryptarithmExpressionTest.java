package edu.cmu.cs.cs214.hw3.strategypattern;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw2.expression.VariableExpression;

public class CryptarithmExpressionTest {
	private CryptarithmExpression crypExpression;
	private CryptarithmExpression crypExpression2;
	private CryptarithmExpression crypExpression3;
	private CryptarithmExpression expression1;
	private CryptarithmExpression expression2;
	private CryptarithmExpression expression3;
	private CryptarithmExpression expression4;

	@Before
	public void setUp() throws Exception {
		String[] crypExpr = { "SEND", "+", "MORE", "=", "MONEY", "*", "ME" };
		String[] crypExpr1 = { "SEND", "+", "MORE", "=", "MONEY" };
		crypExpression = new CryptarithmExpression(crypExpr);
		crypExpression2 = new CryptarithmExpression(crypExpr);
		crypExpression3 = new CryptarithmExpression(crypExpr1);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String[] firstLetters = crypExpression.getFirstLetters();
		String[] except = { "S", "M" };
		assertArrayEquals(except, firstLetters);
		List<String> letters = new ArrayList<String>();
		letters.add("S");
		letters.add("E");
		letters.add("N");
		letters.add("D");
		letters.add("M");
		letters.add("O");
		letters.add("R");
		letters.add("Y");
		List<String> letterList = crypExpression.getLettersList();
		assertEquals(letters, letterList);

		Map<String, VariableExpression> lettersMap = crypExpression.getLetters();
		String s = "S";

		assertTrue(s.equals(lettersMap.get("S").name()));
	}

	@Test
	public void testLeftExp() {
		String leftExp = crypExpression.leftExpression().toString();
		assertTrue(leftExp
				.equals("(((((S*1000.0)+(E*100.0))+(N*10.0))+(D*1.0))+((((M*1000.0)+(O*100.0))+(R*10.0))+(E*1.0)))"));
	}

	@Test
	public void testRightExp() {
		String rightExp = crypExpression.rightExpression().toString();
		String aString = "(((M*10.0)+(E*1.0))*(((((M*10000.0)+(O*1000.0))+(N*100.0))+(E*10.0))+(Y*1.0)))";
		assertTrue(rightExp.equals(aString));
	}

	@Test
	public void testEqual() {
		assertTrue(crypExpression.equal(crypExpression2));
		assertFalse(crypExpression.equal(crypExpression3));
	}

	@Test(expected = IllegalStateException.class)
	public void testLettersNum() {
		String[] crypExpr = { "SEND", "+", "MORE", "=", "MONEYWDCEFS" };
		expression1 = new CryptarithmExpression(crypExpr);
	}

	@Test(expected = IllegalStateException.class)
	public void testCharLegal() {
		String[] crypExpr = { "SEND1", "+", "MORE", "=", "MONEY" };
		expression2 = new CryptarithmExpression(crypExpr);
	}

	@Test(expected = IllegalStateException.class)
	public void testHaveEquation() {
		String[] crypExpr = { "SEND1", "+", "MORE" };
		expression3 = new CryptarithmExpression(crypExpr);
	}

	@Test(expected = IllegalStateException.class)
	public void testOperatorVaild() {
		String[] crypExpr = { "SEND1", "#", "MORE", "=", "MONEY" };
		expression4 = new CryptarithmExpression(crypExpr);
	}
}
