package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test for the board class
 * 
 * @author raoliang
 *
 */
public class BoardTest {
	private Board board;

	/**
	 * Called before each test case method
	 * 
	 * @throws Exception
	 *             the exception when method is fail
	 */
	@Before
	public void setUp() throws Exception {
		board = new Board();

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
	 * Test the isValid() method for the first move
	 */
	@Test
	public void testFirstIsValid() {
		// CHECKSTYPE:OFF
		Tile tile1 = new Tile('A', 1);
		Tile tile2 = new Tile('B', 1);
		Square square1 = board.getSquare(7, 7);
		Square square2 = board.getSquare(8, 7);
		Square square3 = board.getSquare(9, 7);

		Move move = new Move();
		move.addTile(square1, tile1);
		move.addTile(square2, tile2);
		move.addTile(square3, tile2);
		assertTrue(board.isValid(move, true));

		Move move2 = new Move();
		move2.addTile(square2, tile1);
		move2.addTile(square3, tile2);
		assertFalse(board.isValid(move2, true));

		Move move3 = new Move();
		move3.addTile(square1, tile1);
		move3.addTile(square3, tile2);
		assertFalse(board.isValid(move3, true));
	}

	/**
	 * Test the isvalid() method for general move
	 */
	@Test
	public void testIsValid() {
		// CHECKSTYPE:OFF
		Tile tile1 = new Tile('A', 1);
		Tile tile2 = new Tile('B', 1);
		Square square1 = board.getSquare(7, 7);
		Square square2 = board.getSquare(8, 7);
		Square square3 = board.getSquare(9, 7);

		square1.setTile(tile1);
		square2.setTile(tile2);
		square3.setTile(tile2);

		Tile tile3 = new Tile('H', 4);
		Tile tile4 = new Tile('Z', 10);
		Square square4 = board.getSquare(7, 6);
		Square square5 = board.getSquare(9, 6);
		Move move = new Move();
		move.addTile(square4, tile3);
		move.addTile(square5, tile3);
		assertFalse(board.isValid(move, false));

		Square square6 = board.getSquare(7, 8);
		Square square7 = board.getSquare(7, 6);
		Move move2 = new Move();
		move2.addTile(square6, tile3);
		move2.addTile(square7, tile4);
		assertTrue(board.isValid(move2, false));

		Square square8 = board.getSquare(7, 9);
		Square square9 = board.getSquare(7, 6);
		Move move3 = new Move();
		move3.addTile(square8, tile3);
		move3.addTile(square9, tile4);
		assertFalse(board.isValid(move3, false));

		Square square10 = board.getSquare(8, 9);
		Square square11 = board.getSquare(7, 6);
		Move move4 = new Move();
		move4.addTile(square10, tile3);
		move4.addTile(square11, tile4);
		assertFalse(board.isValid(move4, false));

	}

	/**
	 * Test the makeWord() method
	 */
	@Test
	public void testMakeWord() {
		Tile tile1 = new Tile('A', 1);
		Tile tile2 = new Tile('B', 1);
		Tile tile3 = new Tile('W', 12);
		Tile tile4 = new Tile('M', 6);
		Square square1 = board.getSquare(7, 7);
		Square square2 = board.getSquare(8, 7);
		Square square3 = board.getSquare(9, 7);
		Square square4 = board.getSquare(8, 6);

		square1.setTile(tile1);
		square2.setTile(tile2);
		square3.setTile(tile2);
		square4.setTile(tile3);

		Square square5 = board.getSquare(10, 7);
		Square square6 = board.getSquare(11, 7);
		Square square7 = board.getSquare(12, 7);
		Move move = new Move();
		move.addTile(square5, tile1);
		move.addTile(square6, tile2);
		move.addTile(square7, tile4);
		// test make key word
		String identify = board.moveOnSameLine(move);
		Word word = board.makeKeyWord(move, identify);
		int startX = word.getStartX();
		int startY = word.getStartY();
		int endX = word.getEndX();
		int endY = word.getEndY();
		List<Tile> tileList = word.getTileList();
		assertEquals(7, startX);
		assertEquals(7, startY);
		assertEquals(12, endX);
		assertEquals(7, endY);
		assertEquals(6, tileList.size());
		// check tiles
		assertEquals(tile1, tileList.get(0));
		assertEquals(tile2, tileList.get(1));
		assertEquals(tile2, tileList.get(2));
		assertEquals(tile1, tileList.get(3));
		assertEquals(tile2, tileList.get(4));
		assertEquals(tile4, tileList.get(5));

		Square square8 = board.getSquare(8, 8);
		Square square9 = board.getSquare(7, 8);
		Move move2 = new Move();
		move2.addTile(square8, tile4);
		move2.addTile(square9, tile3);

		String identify2 = board.moveOnSameLine(move);
		Word word2 = board.makeKeyWord(move2, identify2);
		int startX2 = word2.getStartX();
		int startY2 = word2.getStartY();
		int endX2 = word2.getEndX();
		int endY2 = word2.getEndY();
		List<Tile> tileList2 = word2.getTileList();
		assertEquals(7, startX2);
		assertEquals(8, startY2);
		assertEquals(8, endX2);
		assertEquals(8, endY2);
		assertEquals(2, tileList2.size());

		// test make adjacent word
		List<Word> words = board.makeAdjacentWord(move, identify);
		assertEquals(0, words.size());

		List<Word> words2 = board.makeAdjacentWord(move2, identify2);
		assertEquals(2, words2.size());

		Word wordTmp1 = words2.get(0);
		Word wordTmp2 = words2.get(1);

		int startX3 = wordTmp1.getStartX();
		int startY3 = wordTmp1.getStartY();
		int endX3 = wordTmp1.getEndX();
		int endY3 = wordTmp1.getEndY();
		List<Tile> tileList4 = wordTmp2.getTileList();

		int startX4 = wordTmp2.getStartX();
		int startY4 = wordTmp2.getStartY();
		int endX4 = wordTmp2.getEndX();
		int endY4 = wordTmp2.getEndY();

		System.out.println(startX3);
		System.out.println(startY3);
		System.out.println(endX3);
		System.out.println(endY3);
		System.out.println(startX4);
		System.out.println(startX4);
		System.out.println(endX4);
		System.out.println(endY4);
	}

}
