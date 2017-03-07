package edu.cmu.cs.cs214.hw4.core;

import java.util.List;

public class Word {
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private String direction;
	private List<Tile> tileList;
	private int value;
	private int timer;

	public Word(int startX, int startY, int endX, int endY, String direction, List<Tile> tileList) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.direction = direction;
		this.tileList = tileList;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getEndX() {
		return endX;
	}

	public int getEndY() {
		return endY;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<Tile> getTileList() {
		return tileList;
	}

	public void changeTimer(int tmp) {
		timer = timer * tmp;
	}

	public void addValue(int val) {
		value += val;
	}

	public void calValue(Board board) {
		for (Tile tile : tileList) {
			addValue(tile.getValue());
		}
		if (direction == "row") {
			for (int i = startX; i < endX + 1; i++) {
				Square squareTmp = board.getSquare(i, startY);
				if (!squareTmp.isOccuppied()) {
					Tile tile = tileList.get(i - startX);
					squareTmp.changeWordValue(this, tile);
				}
			}
		}
		if (direction == "col") {
			for (int i = startY; i > endY; i--) {
				Square squareTmp = board.getSquare(startX, i);
				if (!squareTmp.isOccuppied()) {
					Tile tile = tileList.get(i - startY);
					squareTmp.changeWordValue(this, tile);
				}

			}
		}
		setValue(getValue() * timer);
	}

}
