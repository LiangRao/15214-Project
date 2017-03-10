package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for LetterBag class
 * 
 * @author raoliang
 *
 */
public class LetterBagTest {
	private LetterBag letterBag;

	/**
	 * Called before each test case method
	 * 
	 * @throws Exception
	 *             the exception when method is fail
	 */
	@Before
	public void setUp() throws Exception {
		letterBag = new LetterBag();
	}

	/**
	 * Called after each test case method.
	 * 
	 * @throws Exception
	 *             throw Exception when the method fail
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test for getRandomTiles() method in the LetterBag
	 */
	@Test
	public void test() {
		// CHECKSTYPE:OFF
		assertEquals(98, letterBag.getTiles().size());
		List<Tile> tileList = new ArrayList<Tile>();
		tileList = letterBag.getRandomTiles(7);
		assertEquals(7, tileList.size());
		assertEquals(91, letterBag.getTiles().size());
		assertEquals(91, letterBag.getNumber());

	}

}
