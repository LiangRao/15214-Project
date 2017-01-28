package edu.cmu.cs.cs214.hw2.operator;

public class Negation implements UnaryOperator{


	public double apply(double arg) {
		// TODO Auto-generated method stub
		return -arg;
	}
	@Override	
	public String toString(){
		return "-(Neg)";		
	}
}
