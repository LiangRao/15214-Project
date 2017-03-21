package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;

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

	void tileRackChange();

	void specialRackChange();

	void specialSquareChange(int row, int col, SpecialTile specialTile);

}
