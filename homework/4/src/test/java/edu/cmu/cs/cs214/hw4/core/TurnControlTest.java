package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TurnControlTest {
	private TurnControl turnControl;

	@Before
	public void setUp() throws Exception {
		turnControl = new TurnControl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		/**
		 * test retrieveTurn()
		 */
		Player player1 = new Player("Iris");
		Player player2 = new Player("Emily");
		Player player3 = new Player("Jason");
		Player player4 = new Player("Rain");
		turnControl.addPlayer(player1);
		turnControl.addPlayer(player2);
		turnControl.addPlayer(player3);
		turnControl.addPlayer(player4);

		turnControl.retrieveTurn();
		turnControl.updateTurn();
		Player currentPlayer1 = turnControl.getCurrentPlayer();
		assertEquals(player1.getName(), currentPlayer1.getName());

		/**
		 * test retrieveTurn()
		 */
		turnControl.updateTurn();
		Player currentPlayer2 = turnControl.getCurrentPlayer();
		assertEquals(player2.getName(), currentPlayer2.getName());

		/**
		 * test reverseTurn()
		 */

		turnControl.reverseTurn();
		turnControl.updateTurn();
		Player currentPlayer3 = turnControl.getCurrentPlayer();
		assertEquals(player1.getName(), currentPlayer3.getName());

		/**
		 * test
		 */
		turnControl.skipTurn();
		turnControl.updateTurn();
		Player currentPlayer4 = turnControl.getCurrentPlayer();
		assertEquals(player3.getName(), currentPlayer4.getName());

		turnControl.retrieveTurn();
		turnControl.updateTurn();
		Player currentPlayer5 = turnControl.getCurrentPlayer();
		assertEquals(player3.getName(), currentPlayer5.getName());

	}

}
