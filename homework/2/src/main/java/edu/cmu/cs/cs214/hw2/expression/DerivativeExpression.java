package edu.cmu.cs.cs214.hw2.expression;

/**
 * computing the derivative of a variable Expression
 * @author raoliang
 *
 */
public class DerivativeExpression implements Expression{
	
    private Expression fn;
    private VariableExpression indepedentVar;
    private static final double DELTA_X = 1e-9;
	
	/**
	* Creates an expression representing the derivative of the specified
	* function with respect to the specified variable.
	*
	* @param fn the function whose derivative this expression represents
	* @param indepedentVar the variable with respect to which we're differentiating
	*/
	public DerivativeExpression(Expression fn, VariableExpression indepedentVar) {
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
		indepedentVar.store(indepedentVar.eval()-DELTA_X);
		return result;
	}
	
	@Override
	public String toString(){
		String result = "("+fn.toString()+")'"+"("+indepedentVar.name()+"="+indepedentVar.eval()+")"
	                     +"="+eval();		
		return result;
		
	}


}
