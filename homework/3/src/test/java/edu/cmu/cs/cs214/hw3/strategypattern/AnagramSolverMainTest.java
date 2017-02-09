package edu.cmu.cs.cs214.hw3.strategypattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the AnagramSolverMain class
 * 
 * @author raoliang
 *
 */
public class AnagramSolverMainTest {
	private AnagramSolverMain anagramMain;

	/**
	 * Called before each test case method
	 * 
	 * @throws Exception
	 *             the exception when method is fail
	 */
	@Before
	public void setUp() throws Exception {
		anagramMain = new AnagramSolverMain();
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
	 * Test the main method can print out the correct answer
	 */
	@Test
	public void test() {
		String[] strings = { "words.txt", "aplpe", "acdsdf" };
		anagramMain.main(strings);
	}

}
