package edu.cmu.cs.cs214.hw2.expression;

/**
 * A class to represent number
 * @author raoliang
 *
 */
public class NumExpression implements Expression{
	
	private double num;
	
	/**
	 * A constructor
	 * @param num the parameter of the constructor
	 */
	public NumExpression(double num){
		this.num = num;
	}
	
	@Override
	public double eval(){
       return num;
	} 
	
   @Override
	public String toString(){
		return String.valueOf(num);		
	}
}
