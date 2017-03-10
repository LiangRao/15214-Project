package edu.cmu.cs.cs214.hw4.core.timer;

import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.Word;

/**
 * A triple word timer which will triple the value of a word
 * 
 * @author raoliang
 *
 */
public class TripleWordTimer implements Timer {
	private static final int TRIPLE_NUM = 3;

	@Override
	public void changeWordValue(Word word, Tile tile) {

		word.changeTimer(TRIPLE_NUM);

	}

}
