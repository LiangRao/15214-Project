package edu.cmu.cs.cs214.hw3.iterator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the HeapGenerator of iterator pattern
 * 
 * @author raoliang
 *
 */
public class HeapGeneratorTest {
	private HeapGenerator<Integer> heapGenerator;

	/**
	 * Called before each test case method
	 * 
	 * @throws Exception
	 *             the exception when method is fail
	 */
	@Before
	public void setUp() throws Exception {
		heapGenerator = new HeapGenerator<Integer>();
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
	 * Testing the Heap's algorithm permutation generator can generate the
	 * correct permutations
	 */
	@Test
	public void test() {
		Integer[] test = {};
		assertEquals(0, heapGenerator.perm(test, test.length).size());

		Integer[] list = { 1, 2, 3, 4 };
		List<Integer[]> result = heapGenerator.perm(list, 4);
		assertEquals(24, result.size());
		Iterator<Integer[]> iterator = heapGenerator.iterator();
		assertEquals(Arrays.toString(result.get(0)), Arrays.toString(iterator.next()));
		assertEquals(Arrays.toString(result.get(1)), Arrays.toString(iterator.next()));
		assertEquals(true, iterator.hasNext());
		for (int i = 0, len = result.size() - 2; i < len; i++) {
			iterator.next();
		}
		assertEquals(false, iterator.hasNext());

		Set<Integer[]> testSet = new HashSet<Integer[]>();
		for (int i = 0, len = result.size(); i < len; i++) {
			testSet.add(result.get(i));
		}
		assertEquals(24, testSet.size());

	}

}
