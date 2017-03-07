package edu.cmu.cs.cs214.hw4.core;

public class Tile {
	private char letter;
	private int value;

	// constructor
	public Tile(char i, int value) {
		this.letter = i;
		this.value = value;
	}

	public char getLetter() {
		return letter;
	}

	public int getValue() {
		return value;
	}
}
