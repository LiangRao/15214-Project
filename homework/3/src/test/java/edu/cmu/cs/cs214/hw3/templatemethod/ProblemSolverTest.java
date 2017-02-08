package edu.cmu.cs.cs214.hw3.templatemethod;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProblemSolverTest {
	private ProblemSolver<Integer> problemSolver;

	@Before
	public void setUp() throws Exception {
		problemSolver = new ProblemSolver<Integer>() {

			@Override
			void solveProblem(Integer[] array) {
				// TODO Auto-generated method stub

			}
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
