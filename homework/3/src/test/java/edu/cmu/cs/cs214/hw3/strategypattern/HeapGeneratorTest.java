package edu.cmu.cs.cs214.hw3.strategypattern;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for HeapGenerator in strategy pattern
 * 
 * @author raoliang
 *
 */
public class HeapGeneratorTest {
	private HeapGenerator<String> heapGenerator;
	private AnagramSolver solver;

	/**
	 * Called before each test case method
	 * 
	 * @throws Exception
	 *             the exception when method is fail
	 */
	@Before
	public void setUp() throws Exception {
		String filePath = "src/main/resources/words.txt";
		solver = new AnagramSolver(filePath);
		heapGenerator = new HeapGenerator<>(solver);
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
	 * Testing the permutation generator can generate correct permutations
	 */
	@Test
	public void test() {
		String[] strings = { "w", "o", "l", "d", "r" };
		heapGenerator.perm(strings, strings.length);
		Set<String> set = solver.getResult();
		assertEquals(1, set.size());
		List<String> list = new ArrayList<String>();
		list.addAll(set);
		String result = "world";
		assertEquals(result, list.get(0));

	}

}
