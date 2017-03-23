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
public class TrippleLetterTimer implements Timer {
	private static final Color COLOR = new Color(0, 0, 255);
	private static final String NAME = "3LS";

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
		word.addValue(2 * tile.getValue());
	}
}
