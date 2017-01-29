package edu.cmu.cs.cs214.hw2.expression;


public class DerivativeExpression implements Expression{
	
    Expression fn;
    VariableExpression indepedentVar;
    private final double DELTA_X = 1e-9;
	
	/**
	* Creates an expression representing the derivative of the specified
	* function with respect to the specified variable.
	*
	* @param fn the function whose derivative this expression represents
	* @param independentVar the variable with respect to which we're
	* differentiating
	*/
	public DerivativeExpression(Expression fn,
	                            VariableExpression indepedentVar) {
		this.fn = fn;
		this.indepedentVar = indepedentVar;	
	}
	
	@Override
	public double eval() {
		// TODO Auto-generated method stub
		double val1 = fn.eval();
		indepedentVar.store(indepedentVar.eval()+DELTA_X);
		double val2 =fn.eval();
		double result =(val2-val1)/DELTA_X;		
		return result;
	}

}
