package edu.cmu.cs.cs214.hw3.subset;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SubsetGeneratorTest {
	private SubsetGenerator subGen;

	@Before
	public void setUp() throws Exception {
		subGen = new SubsetGenerator();
	}

	@After
	public void tearDown() throws Exception {
	}

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
	}

	@Test(expected = IllegalStateException.class)
	public void testException() {
		subGen.subset(11);
	}

}
