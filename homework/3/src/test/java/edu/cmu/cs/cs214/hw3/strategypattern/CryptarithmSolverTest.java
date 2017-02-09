package edu.cmu.cs.cs214.hw3.strategypattern;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the CryptarithmSolver class
 * 
 * @author raoliang
 *
 */
public class CryptarithmSolverTest {
	private CryptarithmSolver crypSolver;

	/**
	 * Called before each test case method
	 * 
	 * @throws Exception
	 *             the exception when method is fail
	 */
	@Before
	public void setUp() throws Exception {
		String[] crypExpr = { "SEND", "+", "MORE", "=", "MONEY" };
		CryptarithmExpression crypExpression = new CryptarithmExpression(crypExpr);
		crypSolver = new CryptarithmSolver(crypExpression);
	}

	/**
	 * Called after each test case method.
	 * 
	 * @throws Exception
	 *             throw Exception when the method fail
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Testing the method in CryptarithmSolver can solve the Cryptarithm problem
	 * successful
	 */
	@Test
	public void test() {
		Integer[] array = { 9, 5, 6, 7, 1, 0, 8, 2 };
		crypSolver.solveProblem(array);
		Set<Map<String, Integer>> resultSet = crypSolver.getResultSet();
		assertEquals(1, resultSet.size());
		Integer[] array1 = { 5, 9, 6, 7, 1, 0, 8, 2 };
		crypSolver.solveProblem(array1);
		Set<Map<String, Integer>> resultSet1 = crypSolver.getResultSet();
		assertEquals(1, resultSet1.size());
		Integer[] array2 = { 0, 9, 6, 7, 1, 5, 8, 2 };
		crypSolver.solveProblem(array2);
		Set<Map<String, Integer>> resultSet2 = crypSolver.getResultSet();
		assertEquals(1, resultSet1.size());

	}

}
