package edu.cmu.cs.cs214.hw4.core.timer;

import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.Word;

/**
 * The interface for different timers
 * 
 * @author raoliang
 *
 */
public interface Timer {
	/**
	 * Change the word value with timer
	 * 
	 * @param word
	 *            a word needing to change the value
	 * @param tile
	 *            a tile in the word needing to change the value
	 */
	void changeWordValue(Word word, Tile tile);
}
