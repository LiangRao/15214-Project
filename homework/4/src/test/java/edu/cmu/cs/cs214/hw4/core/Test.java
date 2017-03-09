package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

public class Test {
	/**
	 * Created by tonydeng on 16/5/12.
	 */
	@RunWith(Parameterized.class)
	public class FibonacciNumbersTest {
	    private static final Logger log = LoggerFactory.getLogger(FibonacciNumbersTest.class);
	    public static Collection<Integer[]> data() {
	        return Arrays.asList(new Integer[][]{
	                {0, 0}, {1, 1}, {2, 1},
	                {3, 2}, {4, 3}, {5, 5},
	                {6, 8}});
	    }
	    private int value;
	    private int expected;
	    public FibonacciNumbersTest(int input, int expected) {
	        value = input;
	        this.expected = expected;
	    }
	    
	    @Test
	    public void fibonacciNumberCall() {
	        log.info("expected {} fib(value) {}", expected, fib(value));
	        Assert.assertEquals(expected, fib(value));
	    }
	    

}
