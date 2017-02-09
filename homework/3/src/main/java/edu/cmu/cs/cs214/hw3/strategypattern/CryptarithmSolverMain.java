package edu.cmu.cs.cs214.hw3.strategypattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.cmu.cs.cs214.hw3.subset.SubsetGenerator;

/**
 * The main class to solve Cryptarithm problem
 * 
 * @author raoliang
 *
 */
public class CryptarithmSolverMain {
	/**
	 * The main class to solve Cryptarithm problem
	 * 
	 * @param args
	 *            the Cryptarithm Expression needs to be solved
	 */
	public static void main(String[] args) {
		SubsetGenerator subset = new SubsetGenerator();
		CryptarithmExpression expression = new CryptarithmExpression(args);
		String[] strings = expression.getCrypExpr();
		CryptarithmSolver solver = new CryptarithmSolver(expression);
		int n = expression.getLetters().size();
		List<Integer[]> subsetList = subset.subset(n);
		HeapGenerator<Integer> permGenerator = new HeapGenerator<Integer>(solver);
		for (int i = 0, size = subsetList.size(); i < size; i++) {
			Integer[] tempArray = subsetList.get(i);
			permGenerator.perm(tempArray, n);
		}
		List<Map<String, Integer>> resultList = new ArrayList<Map<String, Integer>>();
		resultList.addAll(solver.getResultSet());
		System.out.println(resultList.size() + " solution(s):");
		for (int i = 0; i < resultList.size(); i++) {
			System.out.println(resultList.get(i));
		}

	}

}
