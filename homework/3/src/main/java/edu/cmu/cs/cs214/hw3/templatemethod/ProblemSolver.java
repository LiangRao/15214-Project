package edu.cmu.cs.cs214.hw3.templatemethod;

/**
 * A abstract class for template pattern, is used to generate permutations and
 * solves a specific problem with the permutation
 * 
 * @author raoliang
 *
 * @param <T>
 *            type of datas which need to be permutated and then solve a
 *            specific problem
 */
public abstract class ProblemSolver<T> {
	/**
	 * To check if a array is valid to solver a specific problem
	 * 
	 * @param array
	 *            a array to solve specific problem
	 */
	abstract public void solveProblem(T[] array);

	/**
	 * Generate all permutations of a array
	 * 
	 * @param list
	 *            a array needed to permutate
	 * @param size
	 *            the size of the array
	 */
	void perm(T[] list, int size) {
		T[] result = null;
		if (size == 1) {
			// System.out.println(Arrays.toString(list));
			solveProblem(list);

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
