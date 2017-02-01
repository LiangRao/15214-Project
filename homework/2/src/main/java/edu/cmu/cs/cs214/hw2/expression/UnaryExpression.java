package edu.cmu.cs.cs214.hw2.expression;

import edu.cmu.cs.cs214.hw2.operator.UnaryOperator;

/**
 * A expression with Unary operator
 * @author raoliang
 *
 */
public class UnaryExpression implements Expression{
	private Expression num;
	private UnaryOperator unaryOperator;
	
	/**
	 * A constructor for the class
	 * @param num the parameter for the constructor
	 * @param unaryOperator the parameter for the constructor
	 */ 
	public UnaryExpression(Expression num, UnaryOperator unaryOperator){
	this.num = num;
	this.unaryOperator = unaryOperator;
}

	@Override
	public double eval() {
		return unaryOperator.apply(num.eval());
	}
	
	@Override
	public String toString(){
		String operator = unaryOperator.toString();
		String result;
		if(operator.equals("| |")){
			result = "|"+num.toString()+"|";
			return result;
		}else{
			result = "-("+num.toString()+")";
			return result;
		}
	}
}
