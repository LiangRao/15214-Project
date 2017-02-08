package edu.cmu.cs.cs214.hw3.strategypattern;

public interface ProblemSolver<T> {
	void solveProblem(T[] array);
}