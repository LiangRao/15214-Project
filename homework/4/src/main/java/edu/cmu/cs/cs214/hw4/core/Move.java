package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;

/**
 * A class to store information a move that a player submit
 * 
 * @author raoliang
 *
 */
public class Move {
	private Map<Square, Tile> tileMap; // to store general tiles and squares
	private List<Square> boomSquareList; // to store the squares which
											// experience a boom special tile in
											// this move
	private SpecialTile specialTile;
	private Square specialTileSquare;
	private Map<Square, SpecialTile> removedSpecialTile; // to store the removed
															// special tile

	/**
	 * A constructor
	 */
	public Move() {
		tileMap = new HashMap<Square, Tile>();
		removedSpecialTile = new HashMap<>();
		boomSquareList = new ArrayList<>();
		specialTile = null;
		specialTileSquare = null;
	}

	/**
	 * Add the square which experience the boom in this move
	 * 
	 * @param square
	 *            the blasted squares related to the move
	 */
	public void addBoomSquareList(Square square) {
		boomSquareList.add(square);
	}

	/**
	 * Add tile and square to the move
	 * 
	 * @param square
	 *            the square which places a certain tile
	 * @param tile
	 *            the tile places on the board related to this move
	 */
	public void addTile(Square square, Tile tile) {
		tileMap.put(square, tile);
	}

	/**
	 * Remove the couple of tile and square from the move
	 * 
	 * @param square
	 *            the key of the couple needs to remove
	 */
	public void removeTile(Square square) {
		tileMap.remove(square);
	}

	/**
	 * Get the tiles map related to the move
	 * 
	 * @return the tiles map related to the move
	 */
	public Map<Square, Tile> getTileMap() {
		return tileMap;
	}

	/**
	 * Get the special title placed by the move
	 * 
	 * @return the special title placed by the move
	 */
	public SpecialTile getSpecialTile() {
		return specialTile;
	}

	/**
	 * Add special tile to the move
	 * 
	 * @param specialTile
	 *            special tile places by the move
	 * @param specialTileSquare
	 *            the square which places the special tile
	 */
	public void addSpecialTile(SpecialTile specialTile, Square specialTileSquare) {
		this.specialTile = specialTile;
		this.specialTileSquare = specialTileSquare;
	}

	/**
	 * Get the square placing the special tile in the move
	 * 
	 * @return the square placing the special tile in the move
	 */
	public Square getSpecialTileSquare() {
		return specialTileSquare;
	}

	/**
	 * Justify if the move contain a general tile square
	 * 
	 * @param square
	 *            the square needs to check
	 * @return return true if the move contains the square, or return false
	 */
	public boolean containSquare(Square square) {
		Set<Square> set = tileMap.keySet();
		return set.contains(square);
	}

	/**
	 * Justify the move contain a blasted square or not
	 * 
	 * @param square
	 *            the square needs to check
	 * @return return true if the move contains the square, or return false
	 */
	public boolean containBoomSquare(Square square) {
		return boomSquareList.contains(square);
	}

	/**
	 * Justify if the move contain a special tile or not
	 * 
	 * @return return true if the move contains a special tile, or return false
	 */
	public boolean hasSpecialTile() {
		return specialTile != null;
	}

	/**
	 * Get the removed special tile related to the move
	 * 
	 * @return the removed special tile related to the move
	 */
	public Map<Square, SpecialTile> getRemovedSpecialTile() {
		return removedSpecialTile;
	}

	/**
	 * Set the removed special tile related to the move
	 * 
	 * @param removedSpeicialTile
	 *            the removed special tile
	 */
	public void setRemovedSpecialTile(Map<Square, SpecialTile> removedSpeicialTile) {
		this.removedSpecialTile = removedSpeicialTile;
	}

	/**
	 * Clear all information in the move
	 */
	public void clearMove() {
		tileMap = new HashMap<Square, Tile>();
		removedSpecialTile = new HashMap<>();
		boomSquareList = new ArrayList<>();
		specialTile = null;
		specialTileSquare = null;
	}
}
