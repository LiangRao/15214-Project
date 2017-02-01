package edu.cmu.cs.cs214.hw2.termcalc;


import edu.cmu.cs.cs214.hw2.expression.BinaryExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumExpression;
import edu.cmu.cs.cs214.hw2.expression.UnaryExpression;
import edu.cmu.cs.cs214.hw2.operator.AbsoluteValue;
import edu.cmu.cs.cs214.hw2.operator.Addition;
import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;
import edu.cmu.cs.cs214.hw2.operator.Division;
import edu.cmu.cs.cs214.hw2.operator.Exponentiation;
import edu.cmu.cs.cs214.hw2.operator.Multiplication;
import edu.cmu.cs.cs214.hw2.operator.Negation;
import edu.cmu.cs.cs214.hw2.operator.Subtraction;
import edu.cmu.cs.cs214.hw2.operator.UnaryOperator;

/**
 * implement the ExpressionMaker interface
 * @author raoliang
 *
 */
public class ExpressionMakerImpl implements ExpressionMaker{

	@Override
	public Expression sumExpression(Expression addend1, Expression addend2) {
		BinaryOperator add = new Addition();
	    Expression sum = new BinaryExpression(addend1, addend2, add);
		return sum;
	}

	@Override
	public Expression differenceExpression(Expression op1, Expression op2) {
		BinaryOperator sub = new Subtraction();
		Expression difference = new BinaryExpression(op1, op2, sub);
		return difference;
	}

	@Override
	public Expression productExpression(Expression factor1, Expression factor2) {
        BinaryOperator mul = new Multiplication();
        Expression product = new BinaryExpression(factor1, factor2, mul);
        return product;
        
	}

	@Override
	public Expression divisionExpression(Expression dividend, Expression divisor) {
		BinaryOperator div = new Division();
		Expression division = new BinaryExpression(dividend, divisor, div);
		return division;
	}

	@Override
	public Expression exponentiationExpression(Expression base, Expression exponent) {
		BinaryOperator exponentiation = new Exponentiation();
		Expression expResult= new BinaryExpression(base, exponent, exponentiation);
		return expResult;
	}

	@Override
	public Expression negationExpression(Expression operand) {
		UnaryOperator neg = new Negation();
		Expression negResult = new UnaryExpression(operand, neg);
		return negResult;
	}

	@Override
	public Expression absoluteValueExpression(Expression value) {
		UnaryOperator absolute = new AbsoluteValue();
		Expression absoluteResult = new UnaryExpression(value, absolute);
		return absoluteResult;
	}

	@Override
	public Expression numberExpression(double value) {
		Expression num = new NumExpression(value);
		return num;
	}

}
