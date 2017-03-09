package edu.cmu.cs.cs214.hw4.core.timer;

import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.Word;

/**
 * A double letter timer who will double a tile value in the word
 * 
 * @author raoliang
 *
 */
public class DoubleLetterTimer implements Timer {

	@Override
	public void changeWordValue(Word word, Tile tile) {
		word.addValue(tile.getValue());
	}

}
