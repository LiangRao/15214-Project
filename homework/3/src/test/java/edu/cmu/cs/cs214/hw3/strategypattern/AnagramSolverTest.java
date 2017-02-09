package edu.cmu.cs.cs214.hw3.strategypattern;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the AnagramSolver class
 * 
 * @author raoliang
 *
 */
public class AnagramSolverTest {
	private AnagramSolver anagramSolver;

	/**
	 * Called before each test case method
	 * 
	 * @throws Exception
	 *             the exception when method is fail
	 */
	@Before
	public void setUp() throws Exception {
		String filePath = "src/main/resources/words.txt";
		anagramSolver = new AnagramSolver(filePath);
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
	 * Test the AnagramSolver can solve the current problem
	 */
	@Test
	public void test() {
		String[] array = { "a", "l", "e", "p", "p" };
		List<String> list = new ArrayList<String>();
		list.addAll(anagramSolver.getResult());
		assertEquals(0, list.size());

		anagramSolver.solveProblem(array);
		list.addAll(anagramSolver.getResult());
		assertEquals(0, list.size());

		String[] array2 = { "a", "p", "p", "l", "e" };
		anagramSolver.solveProblem(array2);
		list.addAll(anagramSolver.getResult());
		assertEquals(1, list.size());
		assertEquals("apple", list.get(0));
	}

}
