package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LetterBagTest {
	private LetterBag letterBag;

	@Before
	public void setUp() throws Exception {
		letterBag = new LetterBag();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals(98, letterBag.getTiles().size());
		List<Tile> tileList = new ArrayList<Tile>();
		tileList = letterBag.getRandomTiles(7);
		assertEquals(7, tileList.size());
		assertEquals(91, letterBag.getTiles().size());
		assertEquals(91, letterBag.getNumber());

	}

}
