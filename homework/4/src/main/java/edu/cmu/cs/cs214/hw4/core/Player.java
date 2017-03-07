package edu.cmu.cs.cs214.hw4.core;

import java.util.List;

import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;

public class Player {
	private final String name;
	private int score;
	private List<Tile> tileList;
	private List<SpecialTile> specialTiles;
	private Boolean nextTurnFlag;
	private List<Tile> LastRandomTile;
	private List<Word> LastWords;
	private int lastScore;

	public Player(String name) {
		this.name = name;
		nextTurnFlag = true;
		score = 0;
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

	public List<Tile> getLastRandomTile() {
		return LastRandomTile;
	}

	public List<Word> getLastWords() {
		return LastWords;
	}

	public int getLastScore() {
		return lastScore;
	}

	public void addTile(List<Tile> tiles) {
		tileList.addAll(tiles);
	}

	public void addLastRandomTile(List<Tile> tiles) {
		tileList.addAll(tiles);
	}

	public void clearLastRandomTile() {
		LastRandomTile.clear();
	}

	public void addScore(int scoreTmp) {
		score += scoreTmp;
	}

	public void minusScore(int scoreTmp) {
		score -= scoreTmp;
	}

	public static void main(String[] args) {
		System.out.println(1);
	}
}
