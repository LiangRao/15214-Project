package edu.cmu.cs.cs214.hw2.operator;

/**
 * computing a number's negation
 * @author raoliang
 *
 */
public class Negation implements UnaryOperator{

    @Override
	public double apply(double arg) {
		// TODO Auto-generated method stub
		return -arg;
	}
	@Override	
	public String toString(){
		return "-(Neg)";		
	}
}
