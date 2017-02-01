package edu.cmu.cs.cs214.hw2.expression;

import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;

/**
 * A expression with BinaryOperator
 * @author raoliang
 *
 */
public class BinaryExpression implements Expression{

	private Expression num1;
	private Expression num2;
	private BinaryOperator binaryOperator;
	
	/**
	 * A constructor
	 * @param num1 the first expression 
	 * @param num2 the second expression
	 * @param binaryOperator the binary operator for the two expression
	 */
	public BinaryExpression(Expression num1, Expression num2, BinaryOperator binaryOperator){
	this.num1 = num1;
	this.num2 = num2;
	this.binaryOperator = binaryOperator;
	}
	
    @Override
	public double eval() {
		return binaryOperator.apply(num1.eval(), num2.eval());
	}
	
	@Override
	public String toString(){
		String operatorRepre = binaryOperator.toString();
		String exp = "("+num1.toString()+operatorRepre+num2.toString()+")";
		return exp;
		
	}

}
