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
	private final static Color COLOR = new Color(0, 0, 255);
	private final static String NAME = "3LS";

	public Color getColor() {
		return COLOR;
	}

	public String getName() {
		return NAME;
	}

	@Override
	public void changeWordValue(Word word, Tile tile) {
		word.addValue(2 * tile.getValue());
	}
}
