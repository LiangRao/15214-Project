package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;

public class Move {
	private Map<Square, Tile> tileMap;
	private List<Square> boomSquareList;
	private SpecialTile specialTile;
	private Square specialTileSquare;
	private Map<Square, SpecialTile> removedSpecialTile;

	public Move() {
		tileMap = new HashMap<Square, Tile>();
		removedSpecialTile = new HashMap<>();
		boomSquareList = new ArrayList<>();
		specialTile = null;
		specialTileSquare = null;
	}

	public List<Square> getBoomSquareList() {
		return boomSquareList;
	}

	public void addBoomSquareList(Square square) {
		boomSquareList.add(square);
	}

	public void addTile(Square square, Tile tile) {
		tileMap.put(square, tile);
	}

	public void removeTile(Square square) {
		tileMap.remove(square);
	}

	public Map<Square, Tile> getTileMap() {
		return tileMap;
	}

	public SpecialTile getSpecialTile() {
		return specialTile;
	}

	public void addSpecialTile(SpecialTile specialTile, Square specialTileSquare) {
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

	public boolean containBoomSquare(Square square) {
		return boomSquareList.contains(square);
	}

	public boolean hasSpecialTile() {
		return specialTile != null;
	}

	public Map<Square, SpecialTile> getRemovedSpecialTile() {
		return removedSpecialTile;
	}

	public void setRemovedSpecialTile(Map<Square, SpecialTile> removedSpeicialTile) {
		this.removedSpecialTile = removedSpeicialTile;
	}

	public void clearMove() {
		tileMap = new HashMap<Square, Tile>();
		removedSpecialTile = new HashMap<>();
		boomSquareList = new ArrayList<>();
		specialTile = null;
		specialTileSquare = null;
	}
}
