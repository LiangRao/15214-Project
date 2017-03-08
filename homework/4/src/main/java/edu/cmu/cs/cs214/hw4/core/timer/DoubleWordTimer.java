package edu.cmu.cs.cs214.hw4.core.timer;

import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.Word;

public class DoubleWordTimer implements Timer {

	@Override
	public void changeWordValue(Word word, Tile tile) {
		word.changeTimer(2);
	}

}