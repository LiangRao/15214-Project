package edu.cmu.cs.cs214.hw3.strategypattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CryptarithmSolverMainTest {
	CryptarithmSolverMain solverMain;

	@Before
	public void setUp() throws Exception {
		solverMain = new CryptarithmSolverMain();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String[] args = { "JEDER", "+", "LIEBT", "=", "BERLIN" };
		solverMain.main(args);
	}

}
