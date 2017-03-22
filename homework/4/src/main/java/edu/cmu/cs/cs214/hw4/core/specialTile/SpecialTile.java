package edu.cmu.cs.cs214.hw4.core.specialTile;

import edu.cmu.cs.cs214.hw4.core.Move;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleSystem;
import edu.cmu.cs.cs214.hw4.core.Square;

/**
 * The interface for special tiles
 * 
 * @author raoliang
 *
 */
public interface SpecialTile {
	/**
	 * The special tile make effect on the game
	 * 
	 * @param scrabbleSystem
	 *            the game system control
	 * @param square
	 *            the square which contains a certain special tile
	 */
	void makeSpecialEffect(ScrabbleSystem scrabbleSystem, Square square, Move move);

	/**
	 * Get the special tile owner
	 * 
	 * @return the special tile owner
	 */
	Player getOwner();

	/**
	 * Set the special tile owner
	 * 
	 * @param owner
	 *            the owner of a certain special tile
	 */
	void setOwner(Player owner);

	/**
	 * Get the name of a specific special tile
	 * 
	 * @return the name of a specify special tile
	 */
	String getName();

	/**
	 * Get the price of a specific special tile
	 * 
	 * @return the price of a specific special tile
	 */
	int getPrice();
}
