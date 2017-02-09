package edu.cmu.cs.cs214.hw3.strategypattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;

/**
 * A class to solve Cryptarithm problem
 * 
 * @author raoliang
 *
 */
public class CryptarithmSolver implements ProblemSolver<Integer> {
	private CryptarithmExpression crypExpr;
	private Expression leftExp;
	private Expression rightExp;
	private Set<Map<String, Integer>> resultSet = new HashSet<>();

	/**
	 * Return the result of Cryptarithm problem
	 * 
	 * @return the result of Cryptarithm problem
	 */
	public Set<Map<String, Integer>> getResultSet() {
		return resultSet;
	}

	/**
	 * A constructor
	 * 
	 * @param crypExpr
	 *            the expression of Cryptarithm
	 */
	public CryptarithmSolver(CryptarithmExpression crypExpr) {
		this.crypExpr = crypExpr;
		this.leftExp = crypExpr.leftExpression();
		this.rightExp = crypExpr.rightExpression();
	}

	@Override
	public void solveProblem(Integer[] array) {
		// get the reflection from letter to number
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, VariableExpression> lettersMap = crypExpr.getLetters();
		List<String> lettersList = crypExpr.getLettersList();
		for (int i = 0; i < array.length; i++) {
			String key = lettersList.get(i);
			map.put(key, array[i]);
			lettersMap.get(key).store(array[i]);
		}
		String[] firstLetter = crypExpr.getFirstLetters();
		for (int i = 0, len = firstLetter.length; i < len; i++) {
			String letter = firstLetter[i];
			int tem = map.get(letter);
			if (tem == 0) {
				return;
			}
		}
		double leftExpVal = leftExp.eval();
		double rightExpVal = rightExp.eval();
		if (leftExpVal == rightExpVal) {
			resultSet.add(map);

		}

	}
}
