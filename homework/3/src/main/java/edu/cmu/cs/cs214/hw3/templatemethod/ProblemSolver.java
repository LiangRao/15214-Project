package edu.cmu.cs.cs214.hw3.templatemethod;

public abstract class ProblemSolver<T> {
	abstract void solveProblem(T[] array);

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
