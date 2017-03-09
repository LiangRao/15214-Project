package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;
import edu.cmu.cs.cs214.hw4.core.timer.Timer;

public class Square {
	private int x;
	private int y;
	private Tile tile;
	private Timer timer;
	private SpecialTile specialTile;

	public Square(int x, int y) {
		this.x = x;
		this.y = y;
		timer = null;
		tile = null;
		specialTile = null;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public SpecialTile getSpecialTile() {
		return specialTile;
	}

	public void setSpecialTile(SpecialTile specialTile) {
		this.specialTile = specialTile;
	}

	public boolean isOccuppied() {
		return tile != null;
	}

	public void removeTile() {
		if (tile != null) {
			tile = null;
		}
	}

	public void removeSpecialTile() {
		if (specialTile != null) {
			specialTile = null;
		}
	}

	public boolean hasTimer() {
		return timer != null;
	}

	public boolean hasSpecialTile() {
		return specialTile != null;

	}

	public boolean hasTile() {
		return tile != null;
	}

	public void restoreLastSpecialTile(SpecialTile lastSpecialTile) {
		this.specialTile = lastSpecialTile;
	}

	public void changeWordValue(Word word, Tile tile) {
		timer.changeWordValue(word, tile);

	}
}
