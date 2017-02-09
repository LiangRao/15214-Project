package edu.cmu.cs.cs214.hw3.strategypattern;

/**
 * The class is to generate all permutations with Heap's algorithm
 * 
 * @author raoliang
 *
 * @param <T>
 */
public class HeapGenerator<T> {
	private ProblemSolver<T> problem;

	/**
	 * A constructor
	 * 
	 * @param problem
	 *            a specific problem needs to be solved
	 */
	public HeapGenerator(ProblemSolver<T> problem) {
		this.problem = problem;
	}

	/**
	 * Generate all permutations of a specific array
	 * 
	 * @param list
	 *            a array needs to generate all permutations
	 * @param size
	 *            size of the array
	 */
	void perm(T[] list, int size) {
		if (size == 1) {
			problem.solveProblem(list);

		} else {
			for (int i = 0; i < size; i++) {
				perm(list, size - 1);
				if ((size & 1) == 1) {
					T temp = list[0];
					list[0] = list[size - 1];
					list[size - 1] = temp;
				} else {
					T temp = list[i];
					list[i] = list[size - 1];
					list[size - 1] = temp;
				}
			}
		}
	}

}
