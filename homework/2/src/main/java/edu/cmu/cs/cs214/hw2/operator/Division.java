package edu.cmu.cs.cs214.hw2.operator;

/**
 * computing the result of division
 * @author raoliang
 *
 */
public class Division implements BinaryOperator{

	@Override
	public double apply(double arg1, double arg2) {
		// TODO Auto-generated method stub
		return arg1/arg2;
	}
	
	@Override	
	public String toString(){
		return "/";		
	}

}
