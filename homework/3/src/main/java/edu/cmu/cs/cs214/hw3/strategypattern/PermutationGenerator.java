package edu.cmu.cs.cs214.hw3.strategypattern;

public class PermutationGenerator<T> {
	ProblemSolver<T> problem;
	// private List<T[]> resultList = new ArrayList<T[]>();

	public PermutationGenerator(ProblemSolver<T> problem) {
		this.problem = problem;
	}

	void perm(T[] list, int size) {
		T[] result = null;
		if (size == 3) {

			problem.solveProblem(list);
			return;
		} else {
			for (int i = 0; i < size; i++) {
				perm(list, size - 1);
				if (size % 2 == 1) {
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
