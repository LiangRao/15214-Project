package edu.cmu.cs.cs214.hw4.core;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Word {
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private String direction;
	private List<Tile> tileList;
	private Map<Square, Tile> squareMap;
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

	public Word(List<Tile> tileList, Map<Square, Tile> squareMap) {
		this.tileList = tileList;
		this.squareMap = squareMap;
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

	public void calBoomVal(Board board) {
		for (Tile tile : tileList) {
			addValue(tile.getValue());
		}

		Iterator<Map.Entry<Square, Tile>> it = squareMap.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<Square, Tile> entry = it.next();
			Square square = entry.getKey();
			if (square.hasTimer()) {
				Tile tile = entry.getValue();
				square.changeWordValue(this, tile);
			}
		}
		setValue(getValue() * timer);

	}
}
