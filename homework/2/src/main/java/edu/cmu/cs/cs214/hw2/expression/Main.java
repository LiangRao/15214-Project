package edu.cmu.cs.cs214.hw2.expression;

import edu.cmu.cs.cs214.hw2.operator.Addition;
import edu.cmu.cs.cs214.hw2.operator.Multiplication;
import edu.cmu.cs.cs214.hw2.operator.Subtraction;
import edu.cmu.cs.cs214.hw2.zerofinder.ZeroFinder;

public class Main {

	public static void main(String[] args){
		VariableExpression variable = new VariableExpression("x");
		//variable.store(2.0);
		Expression exp = new BinaryExpression(variable, variable, new Multiplication());
		Expression exp2 = new BinaryExpression(variable, new NumExpression(3.0), new Multiplication());
		Expression exp3 = new BinaryExpression(exp, exp2, new Subtraction());
		Expression exp4 = new  BinaryExpression(exp3, new NumExpression(2), new Addition());
      //  variable.store(3.0);
        DerivativeExpression derExp = new DerivativeExpression(exp2, variable);
        //ZeroFinder exp3 = new ZeroFinder();
        double zero = ZeroFinder.zero(exp4, variable, 1.6, 0.000000001);
        System.out.println(zero);
		//System.out.println(exp2.toString()+":"+exp2.eval()+":"+variable.eval());
		//System.out.println(derExp.eval());
	}
}
  