package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdditionTest {
	private Addition addtion;

	@Before
	public void setUp() throws Exception {
		addtion =new Addition();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApply() {
		double sum = addtion.apply(100, 3);
		assertEquals(103.0, sum, 0);
	}
	
	@Test
	public void testToString(){
		assertTrue("+".equals(addtion.toString()));
	}

}
