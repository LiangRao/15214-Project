package edu.cmu.cs.cs214.hw3.strategypattern;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HeapGeneratorTest {
	private HeapGenerator<String> heapGenerator;
	AnagramSolver solver;

	@Before
	public void setUp() throws Exception {
		String filePath = "src/main/resources/words.txt";
		solver = new AnagramSolver(filePath);
		heapGenerator = new HeapGenerator<>(solver);
	}

	@After
	public void tearDown() throws Exception {
	}

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
