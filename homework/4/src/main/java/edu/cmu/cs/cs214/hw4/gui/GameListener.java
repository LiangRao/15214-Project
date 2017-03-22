package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Player;

public interface GameListener {
	/**
	 * Called when any square on the board change
	 * 
	 * @param row
	 * @param col
	 */
	void squareChanged();

	void currentPlayerChange();

	void gameEnded(Player winner);

	void scoreChanged();

	void currentplayerScoreChange();

	void specialRackChange();

	void tileRackChange();

}
