package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbsoluteValueTest {
	private UnaryOperator absolute;

	@Before
	public void setUp() throws Exception {
		absolute = new AbsoluteValue();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApply() {
		assertEquals(11.22, absolute.apply(-11.22), 0);
		assertEquals(21.21, absolute.apply(21.21), 0);
	}
	
	@Test
	public void testToString(){
		assertTrue("| |".equals(absolute.toString()));
	}

}
