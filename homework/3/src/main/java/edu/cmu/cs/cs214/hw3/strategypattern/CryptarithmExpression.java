package edu.cmu.cs.cs214.hw3.strategypattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.cmu.cs.cs214.hw2.expression.BinaryExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumExpression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;
import edu.cmu.cs.cs214.hw2.operator.Addition;
import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;
import edu.cmu.cs.cs214.hw2.operator.Multiplication;
import edu.cmu.cs.cs214.hw2.operator.Subtraction;

public class CryptarithmExpression {
	private String[] crypExpr;
	private String[] firstLetters;
	private Map<String, VariableExpression> letters = new HashMap<String, VariableExpression>();
	private List<String> lettersList = new ArrayList<String>();

	public List<String> getLettersList() {
		return lettersList;
	}

	public Map<String, VariableExpression> getLetters() {
		return letters;
	}

	public String[] getFirstLetters() {
		return firstLetters;
	}

	/**
	 * return the value of cryExpr variable
	 * 
	 * @return the value of cryExpr variable
	 */
	public String[] getCrypExpr() {
		return crypExpr;
	}

	/**
	 * Constructor set a String[] for Cryptarithm Expression
	 * 
	 * @param crypExpr
	 */
	public CryptarithmExpression(String[] crypExpr) {
		Set<String> lettersSet = new HashSet<String>();
		Set<String> firstLetter = new HashSet<String>();
		int equaCount = 0;
		// check if the cryptarithms is valid
		for (int i = 0, len = crypExpr.length; i < len; i += 2) {
			String temp = crypExpr[i];
			char[] chs = temp.toCharArray();
			String[] lettersTemp = temp.split("");
			firstLetter.add(lettersTemp[0]);
			for (int j = 0, len2 = lettersTemp.length; j < len2; j++) {
				lettersSet.add(lettersTemp[j]);
			}
			for (int j = 0, len3 = chs.length; j < len3; j++) {
				if (!Character.isAlphabetic(chs[j])) {
					throw new IllegalStateException("The input cryptarithms is invalid!");
				}
			}

		}
		// check if the total number of letters is more than 10
		if (lettersSet.size() > 10) {
			throw new IllegalStateException("The total letters in cryptarithms are more than 10.");
		}
		// check if the cryptarithms is valid
		for (int i = 1, len = crypExpr.length; i < len; i += 2) {
			if (!(crypExpr[i].equals("+") || crypExpr[i].equals("-") || crypExpr[i].equals("*")
					|| crypExpr[i].equals("="))) {
				throw new IllegalStateException("The input cryptarithms is invalid!");
			}
			if (crypExpr[i].equals("=")) {
				equaCount++;
			}
		}
		if (equaCount != 1) {
			throw new IllegalStateException("The input cryptarithms is invalid!");
		}
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(lettersSet);
		for (int i = 0; i < tempList.size(); i++) {
			String tempString = tempList.get(i);
			lettersList.add(tempString);
			letters.put(tempString, new VariableExpression(tempString));
		}
		// the assignment of crypExpr variable
		this.firstLetters = firstLetter.toArray(new String[0]);
		this.crypExpr = crypExpr;

	}

	/**
	 * Return Expression for left part
	 * 
	 * @return Expression for left part
	 */
	public Expression leftExpression() {
		List<String> leftExpr = new ArrayList<String>();
		for (int i = 0, len = crypExpr.length; i < len; i++) {
			if (!(crypExpr[i].equals("="))) {
				leftExpr.add(crypExpr[i]);
			} else {
				break;
			}
		}
		BinaryOperator binaryOperator;
		Expression var = VarExprEval(leftExpr.get(0));
		Expression exp = var;
		for (int i = 1, len = leftExpr.size(); i < len; i += 2) {
			Expression varTemp = VarExprEval(leftExpr.get(i + 1));
			Expression num2 = varTemp;
			String tempString = leftExpr.get(i);
			if (tempString.equals("+")) {
				binaryOperator = new Addition();
			} else if (tempString.equals("*")) {
				binaryOperator = new Multiplication();
			} else {
				binaryOperator = new Subtraction();
			}
			exp = new BinaryExpression(exp, num2, binaryOperator);
		}

		return exp;

	}

	/**
	 * Expression for right part
	 * 
	 * @return Expression for left part
	 */
	public Expression rightExpression() {
		List<String> rightExpr = new ArrayList<String>();
		for (int i = crypExpr.length - 1; i >= 0; i--) {
			if (!crypExpr[i].equals("=")) {
				rightExpr.add(crypExpr[i]);
			} else {
				break;
			}
		}
		BinaryOperator binaryOperator;
		Expression var = VarExprEval(rightExpr.get(0));
		Expression exp = var;
		for (int i = 1, len = rightExpr.size(); i < len; i += 2) {
			Expression varTemp = VarExprEval(rightExpr.get(i + 1));
			Expression num2 = varTemp;
			String tempString = rightExpr.get(i);
			if (tempString.equals("+")) {
				binaryOperator = new Addition();
			} else if (tempString.equals("*")) {
				binaryOperator = new Multiplication();
			} else {
				binaryOperator = new Subtraction();
			}
			exp = new BinaryExpression(exp, num2, binaryOperator);
		}
		return exp;
	}

	private Expression VarExprEval(String var) {
		String[] temp = var.split("");
		int len = temp.length;
		VariableExpression var1 = letters.get(temp[0]);
		Expression expression = var1;
		expression = new BinaryExpression(expression, new NumExpression(Math.pow(10, len - 1)), new Multiplication());
		for (int i = 1; i < len; i++) {
			VariableExpression varTemp = letters.get(temp[i]);
			expression = new BinaryExpression(expression,
					new BinaryExpression(varTemp, new NumExpression(Math.pow(10, len - i - 1)), new Multiplication()),
					new Addition());
		}
		return expression;
	}

	/**
	 * return true if the two Cryptarithm Expression is equal, or return false;
	 * 
	 * @return return true if the two Cryptarithm Expression is equal, or return
	 *         false;
	 */
	public boolean equal(CryptarithmExpression expre) {
		Arrays.sort(expre.getCrypExpr());
		Arrays.sort(crypExpr);
		if (Arrays.equals(expre.getCrypExpr(), crypExpr)) {
			return true;
		} else {
			return false;
		}
	}

}
