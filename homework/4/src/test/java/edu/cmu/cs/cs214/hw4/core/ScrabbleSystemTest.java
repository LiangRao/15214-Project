package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;

/**
 * Test for ScrabbleSystem class
 * 
 * @author raoliang
 *
 */
public class ScrabbleSystemTest {
	private ScrabbleSystem scrabbleSystem;

	/**
	 * Called before each test case method
	 * 
	 * @throws Exception
	 *             the exception when method is fail
	 */
	@Before
	public void setUp() throws Exception {
		scrabbleSystem = new ScrabbleSystem();
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
	 * Test for the all games flow
	 */
	@Test
	public void test() {
		// CHECKSTYPE:OFF

		// Test the addPlayer(Player player) method
		Player player1 = new Player("Iris");
		Player player2 = new Player("Emily");
		Player player3 = new Player("Jason");
		Player player4 = new Player("Rain");
		Player player5 = new Player("Mandy");
		scrabbleSystem.addPlayer(player1);
		scrabbleSystem.addPlayer(player2);
		scrabbleSystem.addPlayer(player3);
		scrabbleSystem.addPlayer(player4);
		scrabbleSystem.addPlayer(player5);
		List<Player> playersList = scrabbleSystem.getPlayers();
		assertEquals(4, playersList.size());

		// Test the startNewGame() method
		/**
		 * The first Turn
		 */
		scrabbleSystem.startNewGame();
		Player currentPlayer1 = scrabbleSystem.getCurrentPlayer();
		System.out.println(currentPlayer1.getName());
		assertEquals(7, currentPlayer1.getTileList().size());
		List<SpecialTile> specialStore = scrabbleSystem.getSpecialStore();
		assertEquals(4, specialStore.size());
		assertEquals("Boom", specialStore.get(0).getName());
		assertEquals("NegativePoint", specialStore.get(1).getName());
		assertEquals("RetrieveOrder", specialStore.get(2).getName());
		assertEquals("ReverseOrder", specialStore.get(3).getName());
		assertEquals(70, scrabbleSystem.getLetterBag().getNumber());

		// Test the playMove(Move move) method
		Square square1 = scrabbleSystem.getBoard().getSquare(7, 7);
		Square square2 = scrabbleSystem.getBoard().getSquare(8, 7);
		Square square3 = scrabbleSystem.getBoard().getSquare(9, 7);

		Tile tile1 = currentPlayer1.getTileList().get(0);
		Tile tile2 = currentPlayer1.getTileList().get(1);
		Tile tile3 = currentPlayer1.getTileList().get(2);

		int scoreSum1 = tile1.getValue() + tile2.getValue() + tile3.getValue();
		// The first Player
		Move move = scrabbleSystem.getMove();
		move.addTile(square2, tile2);
		move.addTile(square3, tile3);

		scrabbleSystem.playMove(move);
		assertEquals(7, currentPlayer1.getTileList().size());
		assertEquals(70, scrabbleSystem.getLetterBag().getNumber());

		move.addTile(square1, tile1);
		scrabbleSystem.playMove(move);
		assertEquals(7, currentPlayer1.getTileList().size());
		assertEquals(67, scrabbleSystem.getLetterBag().getNumber());

		assertTrue(scrabbleSystem.getBoard().getSquare(7, 7).isOccuppied());
		assertTrue(scrabbleSystem.getBoard().getSquare(8, 7).isOccuppied());
		assertTrue(scrabbleSystem.getBoard().getSquare(9, 7).isOccuppied());
		assertFalse(scrabbleSystem.getBoard().getSquare(10, 7).isOccuppied());
		assertFalse(scrabbleSystem.isGameOver());
		// check player's score
		assertEquals(scoreSum1, currentPlayer1.getScore());

		Player challengePlayer1 = playersList.get(0);
		for (int i = 0; i < 4; i++) {
			Player playerTmp = playersList.get(i);
			if (playerTmp != currentPlayer1) {
				challengePlayer1 = playerTmp;
				break;
			}
		}
		// if there is no player invoke a challenge
		scrabbleSystem.challenge(challengePlayer1);

		/**
		 * The secord turn
		 */
		Player currentPlayer2 = scrabbleSystem.getCurrentPlayer();
		assertNotEquals(currentPlayer1, currentPlayer2);
		System.out.println(currentPlayer1.getName());
		System.out.println(currentPlayer2.getName());

		Square square4 = scrabbleSystem.getBoard().getSquare(8, 6);
		Square square5 = scrabbleSystem.getBoard().getSquare(8, 8);
		Square square6 = scrabbleSystem.getBoard().getSquare(8, 9);
		Square square7 = scrabbleSystem.getBoard().getSquare(7, 8);

		Tile tile4 = currentPlayer2.getTileList().get(0);
		Tile tile5 = currentPlayer2.getTileList().get(1);
		Tile tile6 = currentPlayer2.getTileList().get(2);

		Move move2 = scrabbleSystem.getMove();
		assertEquals(0, move2.getTileMap().size());
		move2.addTile(square4, tile4);
		move2.addTile(square5, tile5);
		move2.addTile(square7, tile6);
		// check invalid move
		scrabbleSystem.playMove(move2);

		// check invalid move
		move2.clearMove();
		move2.addTile(square4, tile4);
		move2.addTile(square6, tile6);
		scrabbleSystem.playMove(move2);

		// make valid move
		move2.clearMove();
		move2.addTile(square4, tile4);
		move2.addTile(square5, tile5);
		move2.addTile(square6, tile6);
		scrabbleSystem.playMove(move2);

		int scoreSum2 = tile2.getValue() + tile4.getValue() * 2 + tile5.getValue() * 2 + tile6.getValue();
		assertEquals(scoreSum2, currentPlayer2.getScore());
		scrabbleSystem.setChallengeFlag(true);

		// get the challenge player
		Player challengePlayer2 = playersList.get(0);
		for (int i = 0; i < 4; i++) {
			Player playerTmp = playersList.get(i);
			if (playerTmp != currentPlayer1) {
				challengePlayer2 = playerTmp;
				break;
			}
		}

		assertEquals(64, scrabbleSystem.getLetterBag().getNumber());
		scrabbleSystem.challenge(challengePlayer2);
		assertEquals(67, scrabbleSystem.getLetterBag().getNumber());
		assertFalse(square4.isOccuppied());
		assertFalse(square5.isOccuppied());
		assertFalse(square6.isOccuppied());
		// check isChallengeFlag
		assertFalse(scrabbleSystem.isChallengeFlag());

		/**
		 * The third turn
		 */

		Player currentPlayer3 = scrabbleSystem.getCurrentPlayer();
		Move move3 = scrabbleSystem.getMove();
		currentPlayer3.addScore(100);
		scrabbleSystem.buySpecialTile("Boom");
		assertEquals(80, currentPlayer3.getScore());

		Square square8 = scrabbleSystem.getBoard().getSquare(8, 8);
		Square square9 = scrabbleSystem.getBoard().getSquare(9, 8);
		Square square10 = scrabbleSystem.getBoard().getSquare(10, 8);
		Square square11 = scrabbleSystem.getBoard().getSquare(10, 7);

		Tile tile8 = currentPlayer3.getTileList().get(0);
		Tile tile9 = currentPlayer3.getTileList().get(1);
		Tile tile10 = currentPlayer3.getTileList().get(2);
		SpecialTile specialTile = currentPlayer3.getSpecialTiles().get(0);
		move3.addTile(square8, tile8);
		move3.addTile(square9, tile9);
		move3.addTile(square10, tile10);
		move3.addSpecialTile(specialTile, square11);

		int scoreSum3 = 2 * tile8.getValue() + tile9.getValue() + tile10.getValue() + 2 * tile8.getValue()
				+ tile2.getValue() + tile3.getValue() + tile9.getValue() + 80;
		scrabbleSystem.playMove(move3);
		assertEquals(scoreSum3, currentPlayer3.getScore());
		scrabbleSystem.challenge(currentPlayer2);
		assertEquals(64, scrabbleSystem.getLetterBag().getNumber());
		assertTrue(square11.hasSpecialTile());

		/**
		 * The fourth turn
		 */
		Player currentPlayer4 = scrabbleSystem.getCurrentPlayer();
		currentPlayer4.addScore(10);
		scrabbleSystem.buySpecialTile("NegativePoint");
		assertEquals(0, currentPlayer4.getSpecialTiles().size());

		currentPlayer4.addScore(50);
		scrabbleSystem.buySpecialTile("NegativePoint");
		int scoreSum4 = currentPlayer4.getScore();

		Move move4 = scrabbleSystem.getMove();
		SpecialTile specialTile2 = currentPlayer4.getSpecialTiles().get(0);

		Square square12 = scrabbleSystem.getBoard().getSquare(11, 7);
		move4.addSpecialTile(specialTile2, square12);

		scrabbleSystem.pass(move4);
		assertEquals(scoreSum4, currentPlayer4.getScore());
		assertTrue(square12.hasSpecialTile());
		assertEquals(0, currentPlayer4.getSpecialTiles().size());

		/**
		 * The fifth turn
		 */
		Player currentPlayer5 = scrabbleSystem.getCurrentPlayer();
		Move move5 = scrabbleSystem.getMove();
		Square square13 = scrabbleSystem.getBoard().getSquare(10, 7);
		Square square14 = scrabbleSystem.getBoard().getSquare(11, 7);
		Square square15 = scrabbleSystem.getBoard().getSquare(12, 7);
		Square square16 = scrabbleSystem.getBoard().getSquare(13, 7);
		Square square17 = scrabbleSystem.getBoard().getSquare(14, 7);

		Tile tile13 = currentPlayer5.getTileList().get(0);
		Tile tile14 = currentPlayer5.getTileList().get(1);
		Tile tile15 = currentPlayer5.getTileList().get(2);
		Tile tile16 = currentPlayer5.getTileList().get(3);
		Tile tile17 = currentPlayer5.getTileList().get(4);

		move5.addTile(square13, tile13);
		move5.addTile(square14, tile14);
		move5.addTile(square15, tile15);
		move5.addTile(square16, tile16);
		move5.addTile(square17, tile17);
		// System.out.println(currentPlayer1.getName());
		// System.out.println(currentPlayer5.getName());
		// System.out.println(scoreSum1);
		// System.out.println(currentPlayer5.getScore());
		scrabbleSystem.playMove(move5);
		assertFalse(square13.isOccuppied());
		assertFalse(square14.isOccuppied());
		assertFalse(square15.isOccuppied());
		assertFalse(square2.isOccuppied());
		assertFalse(square3.isOccuppied());
		assertTrue(square1.isOccuppied());
		// assertEquals(2, move5.getTileMap().size());

		int scoreSum5 = scoreSum1 - 3 * (tile13.getValue() + tile14.getValue() + tile1.getValue());
		assertEquals(scoreSum1, currentPlayer5.getScore());

		/**
		 * The sixth turn
		 */
		// The player exchanges some tiles
		Player currentPlayer6 = scrabbleSystem.getCurrentPlayer();
		List<Tile> tiles = new ArrayList<>();
		Tile tile18 = currentPlayer6.getTileList().get(0);
		Tile tile19 = currentPlayer6.getTileList().get(1);
		Tile tile20 = currentPlayer6.getTileList().get(2);
		tiles.add(tile18);
		tiles.add(tile19);
		tiles.add(tile20);
		scrabbleSystem.exchangeTile(tiles);
		assertEquals(7, currentPlayer6.getTileList().size());

		/**
		 * Get winner
		 */

		List<Player> playersWinner = scrabbleSystem.getWinner();
		assertEquals(currentPlayer3.getName(), playersWinner.get(0).getName());
	}

}
