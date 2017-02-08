package edu.cmu.cs.cs214.hw3.strategypattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;

public class CryptarithmSolver implements ProblemSolver<Integer> {
	private CryptarithmExpression crypExpr;
	private Expression leftExp;
	private Expression rightExp;
	private Set<Map<String, Integer>> resultSet = new HashSet<>();

	public Set<Map<String, Integer>> getResultSet() {
		return resultSet;
	}

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

	// private void variableEval(VariableExpression var, Map<String, Integer>
	// map) {
	// String name = var.name();
	// String[] letters = name.split("");
	// double value = 0.0;
	// for (int i = 0, len = letters.length; i < len; i++) {
	// value += map.get(letters[i]) * Math.pow(10, letters.length - i);
	// }
	// var.store(value);
	//
	// }
}

// map.put("S", 9);
// map.put("E", 5);
// map.put("N", 6);
// map.put("D", 7);
// map.put("M", 1);
// map.put("O", 0);
// map.put("R", 8);
// map.put("Y", 2);
// System.out.println("error" + map);
