package edu.cmu.cs.cs214.hw4.gui;

import java.util.List;

import edu.cmu.cs.cs214.hw4.core.Player;

/**
 * The ScrabbleSystem game listener
 * 
 * @author raoliang
 *
 */
public interface GameListener {
	/**
	 * Called when any tiles change in the game board
	 */
	void squareChanged();

	/**
	 * Called when the game updates order and the current player changes
	 */
	void currentPlayerChange();

	/**
	 * Called when the game ends
	 * 
	 * @param winner
	 *            all winners of the game
	 */
	void gameEnded(List<Player> winner);

	/**
	 * Called when any player's score changes
	 */
	void scoreChanged();

	/**
	 * Called when the current player's score changes
	 */
	void currentplayerScoreChange();

	/**
	 * Called when the special rack panel changes
	 */
	void specialRackChange();

	/**
	 * Called when the special rack panel changes
	 */
	void tileRackChange();

}
