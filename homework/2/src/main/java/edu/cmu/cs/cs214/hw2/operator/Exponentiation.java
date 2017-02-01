package edu.cmu.cs.cs214.hw2.operator;

/**
 * computing the result of exponent arithmetic
 * @author raoliang
 *
 */
public class Exponentiation implements BinaryOperator{

	@Override
	public double apply(double arg1, double arg2) {
		// TODO Auto-generated method stub
		return Math.pow(arg1, arg2);
	}

	@Override	
	public String toString(){
		return "^";		
	}
	
}
