package edu.cmu.cs.cs214.hw4.core.timer;

import java.awt.Color;

import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.Word;

/**
 * A double word timer which will double the value of a word
 * 
 * @author raoliang
 *
 */
public class DoubleWordTimer implements Timer {
	private static final Color COLOR = new Color(255, 175, 175);
	private static final String NAME = "2WS";

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
		word.changeTimer(2);
	}

}
