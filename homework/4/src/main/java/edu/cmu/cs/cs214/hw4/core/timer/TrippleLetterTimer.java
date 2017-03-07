package edu.cmu.cs.cs214.hw4.core.timer;

import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.Word;

public class TrippleLetterTimer implements Timer {

	@Override
	public void changeWordValue(Word word, Tile tile) {
		word.addValue(2 * tile.getValue());
	}
}
