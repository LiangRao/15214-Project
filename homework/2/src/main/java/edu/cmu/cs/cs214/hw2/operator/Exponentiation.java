package edu.cmu.cs.cs214.hw2.operator;

public class Exponentiation implements BinaryOperator{

	public double apply(double arg1, double arg2) {
		// TODO Auto-generated method stub
		return Math.pow(arg1, arg2);
	}

	@Override	
	public String toString(){
		return "^";		
	}
	
}
