package edu.cmu.cs.cs214.hw3.strategypattern;

public class HeapGenerator<T> {
	ProblemSolver<T> problem;

	public HeapGenerator(ProblemSolver<T> problem) {
		this.problem = problem;
	}

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
