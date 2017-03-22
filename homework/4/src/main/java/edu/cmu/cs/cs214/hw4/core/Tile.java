package edu.cmu.cs.cs214.hw4.core;

/**
 * A class to store the information of a tile
 * 
 * @author raoliang
 *
 */
public class Tile {
	private char letter;
	private int value;

	/**
	 * A tile class constructor
	 * 
	 * @param i
	 *            the letter in a tile
	 * @param value
	 *            the value of a tile
	 */
	public Tile(char i, int value) {
		this.letter = i;
		this.value = value;
	}

	/**
	 * Get the letter of a tile
	 * 
	 * @return the letter of a tile
	 */
	public char getLetter() {
		return letter;
	}

	/**
	 * Get the value of a tile
	 * 
	 * @return the value of a tile
	 */
	public int getValue() {
		return value;
	}

}
