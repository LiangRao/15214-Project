package edu.cmu.cs.cs214.hw2.guicalc;
import edu.cmu.cs.cs214.hw2.operator.AbsoluteValue;
import edu.cmu.cs.cs214.hw2.operator.Addition;
import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;
import edu.cmu.cs.cs214.hw2.operator.Division;
import edu.cmu.cs.cs214.hw2.operator.Exponentiation;
import edu.cmu.cs.cs214.hw2.operator.Multiplication;
import edu.cmu.cs.cs214.hw2.operator.Negation;
import edu.cmu.cs.cs214.hw2.operator.Subtraction;
import edu.cmu.cs.cs214.hw2.operator.UnaryOperator;
import sun.security.provider.NativePRNG;

import java.util.HashSet;
import java.util.Set;

/**
 * Main program that runs the GUI Calculator
 */
public class Main {
	/**
	 * The main method to run the GUI Calculator
	 * @param args : do not take in any arguments from the command line
	 */
    public static void main(String[] args) {
        // TODO: Replace null with your own unary operators. Use a Linked HashSet so operators are displayed in order.
        Set<UnaryOperator> unaryOperators = new HashSet<UnaryOperator>();
        unaryOperators.add(new Negation());
        unaryOperators.add(new AbsoluteValue());

        // TODO: Replace null with your own binary operators. Use a Linked HashSet so operators are displayed in order
        Set<BinaryOperator> binaryOperators = new HashSet<BinaryOperator>();
        binaryOperators.add(new Addition());
        binaryOperators.add(new Division());
        binaryOperators.add(new Exponentiation());
        binaryOperators.add(new Multiplication());
        binaryOperators.add(new Subtraction());

        // Run the calculator!
        new GuiCalculator(unaryOperators, binaryOperators);
    }
}
