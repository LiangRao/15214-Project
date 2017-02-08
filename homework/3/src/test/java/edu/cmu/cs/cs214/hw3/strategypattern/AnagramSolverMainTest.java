package edu.cmu.cs.cs214.hw3.strategypattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnagramSolverMainTest {
	AnagramSolverMain anagramMain;

	@Before
	public void setUp() throws Exception {
		anagramMain = new AnagramSolverMain();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String[] strings = { "words.txt", "aplpe" };
		anagramMain.main(strings);
	}

}
