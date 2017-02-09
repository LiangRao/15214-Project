package edu.cmu.cs.cs214.hw3.strategypattern;

/**
 * Implementing this interface allows to solve a specific problem with a array
 * 
 * @author raoliang
 *
 * @param <T>
 */
public interface ProblemSolver<T> {
	/**
	 * Solve a specific problem with array
	 * 
	 * @param array
	 *            a array to solve specific problem
	 */
	void solveProblem(T[] array);
}