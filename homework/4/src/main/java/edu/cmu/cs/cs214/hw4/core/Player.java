package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;

/**
 * A class to represent a player
 * 
 * @author raoliang
 *
 */
public class Player {
	private final String name;
	private int score;
	private List<Tile> tileList;
	// private List<SpecialTile> specialTiles;
	private Map<String, List<SpecialTile>> specialTilesMap;
	private Boolean nextTurnFlag;
	private List<Tile> lastRandomTile;
	private List<Word> lastWords;
	private int lastScore;
	private Move lastMove;
	private int passTime;

	/**
	 * A constructor
	 * 
	 * @param name
	 *            the name of player
	 */
	public Player(String name) {
		this.name = name;
		nextTurnFlag = true;
		score = 9999;
		tileList = new ArrayList<>();
		// specialTiles = new ArrayList<>();
		lastRandomTile = new ArrayList<>();
		lastWords = new ArrayList<>();
		lastScore = 0;
		passTime = 0;
		specialTilesMap = new HashMap<>();
		specialTilesMap.put("Boom", new ArrayList<>());
		specialTilesMap.put("NegativePoint", new ArrayList<>());
		specialTilesMap.put("RetrieveOrder", new ArrayList<>());
		specialTilesMap.put("ReverseOrder", new ArrayList<>());
		specialTilesMap.put("Skip-a-Turn", new ArrayList<>());
	}

	/**
	 * Get the continue pass turn time of a player
	 * 
	 * @return the continue pass turn time of a player
	 */
	public int getPassTime() {
		return passTime;
	}

	/**
	 * Get the next turn flag
	 * 
	 * @return true if the player has next turn, or return false
	 */
	public Boolean getNextTurnFlag() {
		return nextTurnFlag;
	}

	/**
	 * Set the next turn flag
	 * 
	 * @param nextTurnFlag
	 *            set true if the player has next turn, or set false
	 */
	public void setNextTurnFlag(Boolean nextTurnFlag) {
		this.nextTurnFlag = nextTurnFlag;
	}

	/**
	 * Get the name of the player
	 * 
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the score of the player
	 * 
	 * @return the score of the player
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Get all tiles in the general tile rack
	 * 
	 * @return all tiles in the tile rack
	 */
	public List<Tile> getTileList() {
		return tileList;
	}

	/**
	 * Get all special tiles in the special tile rack
	 * 
	 * @return all special tiles in the special tile rack
	 */
	public Map<String, List<SpecialTile>> getSpecialTiles() {
		return specialTilesMap;
	}

	/**
	 * Get the last random tiles from the letter bag
	 * 
	 * @return the last random tiles from the letter bag
	 */
	public List<Tile> getLastRandomTile() {
		return lastRandomTile;
	}

	/**
	 * Set the last random tile from the letter bag
	 * 
	 * @param lastRandomTile
	 *            the last random tile from the letter bag
	 */
	public void setLastRandomTile(List<Tile> lastRandomTile) {
		this.lastRandomTile = lastRandomTile;
	}

	/**
	 * Get last words of the player
	 * 
	 * @return last words of the player
	 */
	public List<Word> getLastWords() {
		return lastWords;
	}

	/**
	 * Set the last words of a move by the player
	 * 
	 * @param lastWords
	 *            last words of a move
	 */
	public void setLastWords(List<Word> lastWords) {
		this.lastWords = lastWords;
	}

	/**
	 * Get the last move score of the player
	 * 
	 * @return the last move score of the player
	 */
	public int getLastScore() {
		return lastScore;
	}

	/**
	 * Set the last move score of the player
	 * 
	 * @param lastScore
	 *            the last move score of the player
	 */
	public void setLastScore(int lastScore) {
		this.lastScore = lastScore;
	}

	/**
	 * Get the last move of the player
	 * 
	 * @return the last move
	 */
	public Move getLastMove() {
		return lastMove;
	}

	/**
	 * Set the last move
	 * 
	 * @param lastMove
	 *            the last move
	 */
	public void setLastMove(Move lastMove) {
		this.lastMove = lastMove;
	}

	/**
	 * Add a special tile to the special tile rack
	 * 
	 * @param specialTile
	 *            a special tile needs to add
	 */
	public void addSpecialTiles(SpecialTile specialTile) {
		specialTilesMap.get(specialTile.getName()).add(specialTile);
	}

	/**
	 * Remove a special tile from the special tile rack
	 * 
	 * @param specialTile
	 *            a special tile needs to remove
	 */
	public void removeSpecialTile(SpecialTile specialTile) {
		specialTilesMap.get(specialTile.getName()).remove(specialTile);
	}

	/**
	 * Add tiles to the general tiles rack
	 * 
	 * @param tiles
	 *            the tiles needs to add
	 */
	public void addTile(List<Tile> tiles) {
		tileList.addAll(tiles);
	}

	/**
	 * Remove tiles from the general tiles rack
	 * 
	 * @param tiles
	 *            the tiles needs to remove
	 */
	public void removeTile(List<Tile> tiles) {
		tileList.removeAll(tiles);
	}

	/**
	 * Add a score to the player's score
	 * 
	 * @param scoreTmp
	 *            the score needs to add
	 */
	public void addScore(int scoreTmp) {
		score += scoreTmp;
	}

	/**
	 * Minus a score from the player's score
	 * 
	 * @param scoreTmp
	 *            the score needs to minus
	 */
	public void minusScore(int scoreTmp) {
		score -= scoreTmp;
	}

	/**
	 * Add one to the continue pass turn time of the player
	 */
	public void addPassTime() {
		passTime += 1;
	}

	/**
	 * Clear the pass turn time to zero
	 */
	public void clearPassTime() {
		passTime = 0;
	}

}
