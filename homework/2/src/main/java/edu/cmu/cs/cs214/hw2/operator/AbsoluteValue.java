package edu.cmu.cs.cs214.hw2.operator;

/**
 * computing a absolute value of number
 * @author raoliang
 *
 */
public class AbsoluteValue implements UnaryOperator{

	@Override
	public double apply(double arg) {
		// TODO Auto-generated method stub
		if(arg<0){
		return -arg;
		}else {
			return arg;
		}
	}
	
	@Override	
	public String toString(){
		return "| |";		
	}
}
