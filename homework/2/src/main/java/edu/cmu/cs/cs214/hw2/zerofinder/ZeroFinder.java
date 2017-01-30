package edu.cmu.cs.cs214.hw2.zerofinder;

import edu.cmu.cs.cs214.hw2.expression.DerivativeExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.UnaryExpression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;
import edu.cmu.cs.cs214.hw2.operator.AbsoluteValue;

/**
 * Finds zeros of arbitrary functions using Newton's method.
 */
public class ZeroFinder {
    // TODO: Write the zero method
	
	/**
	* Returns a zero of the specified function using Newton's method with
	* approxZero as the initial estimate.
	*
	* @param fn the function whose zero is to be found
	* @param x the independent variable of the function
	* @param approxZero initial approximation for the zero of the function
	* @param tolerance how close to zero f(the returned value) has to be
	* @return a value x for which f(x) is "close to zero" (<= tolerance)
	*/
	public static double zero(Expression fn, VariableExpression x,
	double approxZero, double tolerance) { 
		x.store(approxZero);
		Expression absoluteValue =new UnaryExpression(fn, new AbsoluteValue());
		double val = absoluteValue.eval();
		while (absoluteValue.eval()>=tolerance){
			val = absoluteValue.eval();

		DerivativeExpression derFn = new DerivativeExpression(fn, x);
		approxZero = approxZero - fn.eval()/derFn.eval();
		x.store(approxZero);
		}
		return approxZero;
		
}
}
