package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;

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

	public Player(String name) {
		this.name = name;
		nextTurnFlag = true;
		score = 0;
		tileList = new ArrayList<>();
		specialTiles = new ArrayList<>();
		lastRandomTile = new ArrayList<>();
		lastWords = new ArrayList<>();
		lastScore = 0;
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
		tiles.removeAll(tiles);
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

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();

		list1.add(1);
		list1.add(2);
		list2.add(1);
		list2.add(2);
		list2.add(3);
		list = list1;
		System.out.println(list.toString());
		list = list2;
		System.out.println(list.toString());
	}

}
