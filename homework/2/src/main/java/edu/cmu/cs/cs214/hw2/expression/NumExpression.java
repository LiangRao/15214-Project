package edu.cmu.cs.cs214.hw2.expression;

public class NumExpression implements Expression{
	
	private double num;
	
	public NumExpression(double num){
		this.num = num;
	}
	
	public double eval(){
       return num;
	} 
	
   @Override
	public String toString(){
		return String.valueOf(num);		
	}
}
