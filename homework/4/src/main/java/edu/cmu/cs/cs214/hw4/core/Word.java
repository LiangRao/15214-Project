package edu.cmu.cs.cs214.hw4.core;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A word class to store the information of a word and caculate the value of
 * itself
 * 
 * @author raoliang
 *
 */
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

	/**
	 * The first constructor for general word
	 * 
	 * @param startX
	 *            the start x coordinate of a word
	 * @param startY
	 *            the start y coordinate of a word
	 * @param endX
	 *            the end x coordinate of a word
	 * @param endY
	 *            the end y coordinate of a word
	 * @param direction
	 *            the direction of a word, to be "col" or "row"
	 * @param tileList
	 *            all tiles in a word
	 * @param squareMap
	 *            all pairs of a square and a tile of the word
	 */
	public Word(int startX, int startY, int endX, int endY, String direction, List<Tile> tileList,
			Map<Square, Tile> squareMap) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.direction = direction;
		this.tileList = tileList;
		this.squareMap = squareMap;
		this.timer = 1;
		this.value = 0;
	}

	/**
	 * Get all pairs of a square and a tile of the word
	 * 
	 * @return All pairs of a square and a tile of the word
	 */
	public Map<Square, Tile> getSquareMap() {
		return squareMap;
	}

	/**
	 * Get the start x coordinate
	 * 
	 * @return the start x coordinate
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * Get the start y coordinate
	 * 
	 * @return the start y coordinate
	 */
	public int getStartY() {
		return startY;
	}

	/**
	 * Get the end x coordinate
	 * 
	 * @return the end x coordinate
	 */
	public int getEndX() {
		return endX;
	}

	/**
	 * Get the end y coordinate
	 * 
	 * @return the end y coordinate
	 */
	public int getEndY() {
		return endY;
	}

	/**
	 * Get the value of the word
	 * 
	 * @return the value of the word
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Set the value of a word
	 * 
	 * @param value
	 *            the value of a word
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Get all tiles in the word
	 * 
	 * @return all tiles in the word
	 */
	public List<Tile> getTileList() {
		return tileList;
	}

	/**
	 * Change the timer of the word
	 * 
	 * @param tmp
	 *            the number to change the timer
	 */
	public void changeTimer(int tmp) {
		timer = timer * tmp;
	}

	/**
	 * Add value to the word value
	 * 
	 * @param val
	 *            the value needed to add to the word value
	 */
	public void addValue(int val) {
		value += val;
	}

	/**
	 * Caculate value of the word
	 * 
	 * @param board
	 *            the current board class
	 * @param move
	 *            the move related to the word
	 */
	public void calValue(Board board, Move move) {
		for (Tile tile : tileList) {
			addValue(tile.getValue());
		}
		if (direction == "row") {
			for (int i = startX; i < endX + 1; i++) {
				Square squareTmp = board.getSquare(i, startY);
				if (squareTmp.hasTimer() && move.containSquare(squareTmp)) {
					Tile tile = tileList.get(i - startX);
					squareTmp.changeWordValue(this, tile);
				}
			}
		}
		if (direction == "col") {
			for (int i = startY; i > endY - 1; i--) {
				Square squareTmp = board.getSquare(startX, i);
				if (squareTmp.hasTimer() && move.containSquare(squareTmp)) {
					Tile tile = tileList.get(startY - i);
					squareTmp.changeWordValue(this, tile);
				}

			}
		}
		setValue(getValue() * timer);
	}

	/**
	 * Caculate the boom word value
	 * 
	 * @param board
	 *            the current board class
	 */
	public void calBoomVal(Board board) {
		if (tileList.size() != 0) {
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
		} else {
			setValue(0);
		}

	}

	@Override
	public String toString() {
		StringBuffer wordString = new StringBuffer();
		if (direction == "row") {
			for (int i = startX; i < endX + 1; i++) {
				Tile tile = tileList.get(i - startX);
				wordString.append(tile.getLetter());
			}
		}

		if (direction == "col") {
			for (int i = startY; i > endY - 1; i--) {
				Tile tile = tileList.get(startY - i);
				wordString.append(tile.getLetter());
			}

		}
		return wordString.toString();
	}

}
