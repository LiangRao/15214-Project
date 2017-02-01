package edu.cmu.cs.cs214.hw2.operator;

/**
 * Computing the result of subtraction
 * @author raoliang
 *
 */
public class Subtraction implements BinaryOperator{

	@Override
	public double apply(double arg1, double arg2) {
		return arg1 -arg2;
	}
	
	@Override	
	public String toString(){
		return "-";		
	}
}
