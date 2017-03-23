package edu.cmu.cs.cs214.hw4.core.timer;

import java.awt.Color;

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
	private static final Color COLOR = new Color(255, 0, 0);
	private static final String NAME = "3WS";

	@Override
	public Color getColor() {
		return COLOR;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void changeWordValue(Word word, Tile tile) {

		word.changeTimer(TRIPLE_NUM);

	}

}
