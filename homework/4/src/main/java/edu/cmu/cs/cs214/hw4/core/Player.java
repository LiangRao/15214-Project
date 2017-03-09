package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.List;

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
	private List<SpecialTile> specialTiles;
	private Boolean nextTurnFlag;
	private List<Tile> lastRandomTile;
	private List<Word> lastWords;
	private int lastScore;
	private Move lastMove;
	private int passTime;

	public Player(String name) {
		this.name = name;
		nextTurnFlag = true;
		score = 0;
		tileList = new ArrayList<>();
		specialTiles = new ArrayList<>();
		lastRandomTile = new ArrayList<>();
		lastWords = new ArrayList<>();
		lastScore = 0;
		passTime = 0;
	}

	public int getPassTime() {
		return passTime;
	}

	public Boolean getNextTurnFlag() {
		return nextTurnFlag;
	}

	public void setNextTurnFlag(Boolean nextTurnFlag) {
		this.nextTurnFlag = nextTurnFlag;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public List<Tile> getTileList() {
		return tileList;
	}

	public List<SpecialTile> getSpecialTiles() {
		return specialTiles;
	}

	public void addSpecialTiles(SpecialTile specialTile) {
		specialTiles.add(specialTile);
	}

	public void removeSpecialTile(SpecialTile specialTile) {
		specialTiles.remove(specialTile);
	}

	public List<Tile> getLastRandomTile() {
		return lastRandomTile;
	}

	public void setLastRandomTile(List<Tile> lastRandomTile) {
		this.lastRandomTile = lastRandomTile;
	}

	public List<Word> getLastWords() {
		return lastWords;
	}

	public int getLastScore() {
		return lastScore;
	}

	public void setLastScore(int lastScore) {
		this.lastScore = lastScore;
	}

	public void addTile(List<Tile> tiles) {
		tileList.addAll(tiles);
	}

	public void removeTile(List<Tile> tiles) {
		tileList.removeAll(tiles);
	}

	public void addScore(int scoreTmp) {
		score += scoreTmp;
	}

	public void minusScore(int scoreTmp) {
		score -= scoreTmp;
	}

	public void setLastWords(List<Word> lastWords) {
		this.lastWords = lastWords;
	}

	public Move getLastMove() {
		return lastMove;
	}

	public void setLastMove(Move lastMove) {
		this.lastMove = lastMove;
	}

	public void addPassTime() {
		passTime += 1;
	}

	public void clearPassTime() {
		passTime = 0;
	}

}
