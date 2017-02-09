package edu.cmu.cs.cs214.hw3.subset;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the SubsetGenerator class
 * 
 * @author raoliang
 *
 */
public class SubsetGeneratorTest {
	private SubsetGenerator subGen;

	/**
	 * Called before each test case method
	 * 
	 * @throws Exception
	 *             the exception when method is fail
	 */
	@Before
	public void setUp() throws Exception {
		subGen = new SubsetGenerator();
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
	 * Test the subset method can generate all correct subsets of a array
	 */
	@Test
	public void test() {
		List<Integer[]> result = subGen.subset(9);
		assertEquals(10, result.size());
		Integer[] temp = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		assertEquals(Arrays.toString(temp), Arrays.toString(result.get(0)));

		Set<Integer[]> testSet = new HashSet<Integer[]>();
		for (int i = 0, len = result.size(); i < len; i++) {
			testSet.add(result.get(i));
		}
		assertEquals(10, testSet.size());

		List<Integer[]> result2 = subGen.subset(3);
		assertEquals(120, result2.size());

	}

	/**
	 * Test the subset method will throw exception if the input subset size is
	 * larger than the array's size
	 */
	@Test(expected = IllegalStateException.class)
	public void testException() {
		subGen.subset(11);
	}

}
