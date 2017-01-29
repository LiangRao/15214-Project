package edu.cmu.cs.cs214.hw2.expression;

import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;
import edu.cmu.cs.cs214.hw2.operator.UnaryOperator;

public class UnaryExpresssion implements Expression{
	private Expression num;
	private UnaryOperator unaryOperator;
	
	public UnaryExpresssion(Expression num, UnaryOperator unaryOperator){
	this.num = num;
	this.unaryOperator = unaryOperator;
}

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
