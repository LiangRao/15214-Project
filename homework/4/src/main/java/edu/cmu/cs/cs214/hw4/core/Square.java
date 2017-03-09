package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;
import edu.cmu.cs.cs214.hw4.core.timer.Timer;

/**
 * A class to store the information of every square on the board
 * 
 * @author raoliang
 *
 */
public class Square {
	private int x;
	private int y;
	private Tile tile;
	private Timer timer;
	private SpecialTile specialTile;

	/**
	 * A constructor
	 * 
	 * @param x
	 *            the x coordinate of a square
	 * @param y
	 *            the y coordinate of a square
	 */
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
		timer = null;
		tile = null;
		specialTile = null;
	}

	/**
	 * Get the x coordinate
	 * 
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the x coordinate value
	 * 
	 * @param x
	 *            the value to set x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the y coordinate
	 * 
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the y coordinate value
	 * 
	 * @param y
	 *            the value to set y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Get the tile placing on the square
	 * 
	 * @return he tile placing on the square
	 */
	public Tile getTile() {
		return tile;
	}

	/**
	 * Place the tile to the Square
	 * 
	 * @param tile
	 *            the tile needs to place
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}

	/**
	 * Get the timer of the square
	 * 
	 * @return the timer of the square
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * Set the timer of the square
	 * 
	 * @param timer
	 *            the timer needs to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	/**
	 * Get the special tile placing on the square
	 * 
	 * @return the special tile placing on the square
	 */
	public SpecialTile getSpecialTile() {
		return specialTile;
	}

	/**
	 * Place a special tile to the square
	 * 
	 * @param specialTile
	 *            the special tile needs to place
	 */
	public void setSpecialTile(SpecialTile specialTile) {
		this.specialTile = specialTile;
	}

	/**
	 * Justify the square is occupied or not
	 * 
	 * @return return true if the square is occupied, or return false
	 */
	public boolean isOccuppied() {
		return tile != null;
	}

	/**
	 * Remove the tile in the square
	 */
	public void removeTile() {
		if (tile != null) {
			tile = null;
		}
	}

	/**
	 * Remove the special tile in the square
	 */
	public void removeSpecialTile() {
		if (specialTile != null) {
			specialTile = null;
		}
	}

	/**
	 * Justify the square has a timer or not
	 * 
	 * @return return true if the square has a timer, or return false
	 */
	public boolean hasTimer() {
		return timer != null;
	}

	/**
	 * Justify the square has a special tile or not
	 * 
	 * @return return true if the square has a special tile, or return false
	 */
	public boolean hasSpecialTile() {
		return specialTile != null;

	}

	/**
	 * Justify the square has a tile or not
	 * 
	 * @return return true if the square has a tile, or return false
	 */
	public boolean hasTile() {
		return tile != null;
	}

	/**
	 * Restore the former special tile if the challenge is successful
	 * 
	 * @param lastSpecialTile
	 *            the former special tile on the square
	 */
	public void restoreLastSpecialTile(SpecialTile lastSpecialTile) {
		this.specialTile = lastSpecialTile;
	}

	/**
	 * Change the value of the word on the square if the square has a timer
	 * 
	 * @param word
	 *            the word on the square
	 * @param tile
	 *            the specific tile on the square
	 */
	public void changeWordValue(Word word, Tile tile) {
		timer.changeWordValue(word, tile);

	}
}
