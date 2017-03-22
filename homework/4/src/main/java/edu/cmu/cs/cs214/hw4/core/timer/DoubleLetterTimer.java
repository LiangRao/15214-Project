package edu.cmu.cs.cs214.hw4.core.timer;

import java.awt.Color;

import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.Word;

/**
 * A double letter timer who will double a tile value in the word
 * 
 * @author raoliang
 *
 */
public class DoubleLetterTimer implements Timer {
	private final static Color COLOR = new Color(151, 255, 255);
	private final static String NAME = "2LS";

	public Color getColor() {
		return COLOR;
	}

	public String getName() {
		return NAME;
	}

	@Override
	public void changeWordValue(Word word, Tile tile) {
		word.addValue(tile.getValue());
	}

	public static void main(String[] args) {
		System.out.println(1);
	}
}
