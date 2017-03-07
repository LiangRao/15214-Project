package edu.cmu.cs.cs214.hw4.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;

public class Move {
	private Map<Square, Tile> tileMap;
	private SpecialTile specialTile;
	private Square specialTileSquare;

	public Move() {
		tileMap = new HashMap<Square, Tile>();
		specialTile = null;
		specialTileSquare = null;
	}

	public void addTile(Square square, Tile tile) {
		tileMap.put(square, tile);
	}

	public Map<Square, Tile> getTileMap() {
		return tileMap;
	}

	public SpecialTile getSpecialTile() {
		return specialTile;
	}

	public void setSpecialTile(SpecialTile specialTile, Square specialTileSquare) {
		this.specialTile = specialTile;
		this.specialTileSquare = specialTileSquare;
	}

	public Square getSpecialTileSquare() {
		return specialTileSquare;
	}

	public boolean containSquare(Square square) {
		Set<Square> set = tileMap.keySet();
		return set.contains(square);
	}
}
