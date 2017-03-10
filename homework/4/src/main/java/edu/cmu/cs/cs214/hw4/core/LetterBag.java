package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * A class to represent a letter bag
 * 
 * @author raoliang
 *
 */
public class LetterBag {
	private List<Tile> tiles;
	private int number;
	private static final int[] TILENUM = { 9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2,
			1 };
	private static final int[] TILESCORE = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4,
			10 };

	/**
	 * A constructor
	 */
	public LetterBag() {
		tiles = new ArrayList<Tile>();
		number = 0;
		initial();
	}

	/**
	 * Initial the letter bag
	 */
	public void initial() {
		for (char i = 65; i < 91; i++) {
			for (int j = 0; j < TILENUM[i - 65]; j++) {
				Tile tileTmp = new Tile(i, TILESCORE[i - 65]);
				tiles.add(tileTmp);
				number++;
			}
		}
	}

	/**
	 * Get the number of tiles in the letter bag
	 * 
	 * @return the number of tiles in the letter bag
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Get all tiles in the letter bag
	 * 
	 * @return all tiles in the letter bag
	 */
	public List<Tile> getTiles() {
		return tiles;
	}

	/**
	 * Add tiles to the letter bag
	 * 
	 * @param tile
	 *            tiles needs to add
	 */
	public void addTile(List<Tile> tile) {
		tiles.addAll(tile);
		number += tile.size();
	}

	/**
	 * Justify if the letter bag is empty or not
	 * 
	 * @return return true if the letter bag is empty, or return false
	 */
	public boolean isEmpty() {
		return number == 0;
	}

	/**
	 * Get random tiles from the letter bag
	 * 
	 * @param tileNum
	 *            the number of random tiles needs to get
	 * @return random tiles
	 */
	public List<Tile> getRandomTiles(int tileNum) {
		if (tileNum > number) {
			return tiles;
		}
		List<Tile> tilesTmp = new ArrayList<Tile>();
		List<Integer> randomIndex = getRandomIndex(tileNum);
		int size = randomIndex.size();
		for (int i = 0; i < size; i++) {
			tilesTmp.add(tiles.get(randomIndex.get(i)));
		}
		for (int i = 0; i < size; i++) {
			tiles.remove(tilesTmp.get(i));
			number--;
		}

		return tilesTmp;
	}

	private List<Integer> getRandomIndex(int tileNum) {
		Set<Integer> tmpSet = new HashSet<Integer>();
		List<Integer> tmpList = new ArrayList<Integer>();
		int[] temp = new int[tileNum];

		while (tmpSet.size() < tileNum) {
			Random rand = new Random();
			int s = rand.nextInt(number);
			tmpSet.add(s);
		}
		tmpList.addAll(tmpSet);
		return tmpList;

	}

}
