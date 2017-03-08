package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;
import edu.cmu.cs.cs214.hw4.core.timer.DoubleLetterTimer;
import edu.cmu.cs.cs214.hw4.core.timer.DoubleWordTimer;
import edu.cmu.cs.cs214.hw4.core.timer.TrippleLetterTimer;
import edu.cmu.cs.cs214.hw4.core.timer.TrippleWordTimer;

public class Board {

	private final int boardSize = 15;
	private Square[][] squareArray;
	private Square starSquare;

	public Board() {
		squareArray = new Square[boardSize][boardSize];
		intial();
	}

	public void intial() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				squareArray[i][j] = new Square(i, j);

			}
		}
		starSquare = squareArray[7][7];

		// set Tripple Word Timer
		squareArray[0][0].setTimer(new TrippleWordTimer());
		squareArray[0][7].setTimer(new TrippleWordTimer());
		squareArray[0][14].setTimer(new TrippleWordTimer());
		squareArray[7][0].setTimer(new TrippleWordTimer());
		squareArray[14][0].setTimer(new TrippleWordTimer());
		squareArray[7][14].setTimer(new TrippleWordTimer());
		squareArray[14][7].setTimer(new TrippleWordTimer());
		squareArray[14][14].setTimer(new TrippleWordTimer());

		// set Double Word Timer
		squareArray[1][1].setTimer(new DoubleWordTimer());
		squareArray[2][2].setTimer(new DoubleWordTimer());
		squareArray[3][3].setTimer(new DoubleWordTimer());
		squareArray[4][4].setTimer(new DoubleWordTimer());
		squareArray[1][13].setTimer(new DoubleWordTimer());
		squareArray[2][12].setTimer(new DoubleWordTimer());
		squareArray[3][11].setTimer(new DoubleWordTimer());
		squareArray[4][10].setTimer(new DoubleWordTimer());
		squareArray[13][1].setTimer(new DoubleWordTimer());
		squareArray[12][2].setTimer(new DoubleWordTimer());
		squareArray[11][3].setTimer(new DoubleWordTimer());
		squareArray[10][4].setTimer(new DoubleWordTimer());
		squareArray[10][10].setTimer(new DoubleWordTimer());
		squareArray[11][11].setTimer(new DoubleWordTimer());
		squareArray[12][12].setTimer(new DoubleWordTimer());
		squareArray[13][13].setTimer(new DoubleWordTimer());

		// set Tripple Letter Timer
		squareArray[1][5].setTimer(new TrippleLetterTimer());
		squareArray[1][9].setTimer(new TrippleLetterTimer());
		squareArray[5][1].setTimer(new TrippleLetterTimer());
		squareArray[5][5].setTimer(new TrippleLetterTimer());
		squareArray[5][9].setTimer(new TrippleLetterTimer());
		squareArray[5][13].setTimer(new TrippleLetterTimer());
		squareArray[9][1].setTimer(new TrippleLetterTimer());
		squareArray[9][5].setTimer(new TrippleLetterTimer());
		squareArray[9][9].setTimer(new TrippleLetterTimer());
		squareArray[9][13].setTimer(new TrippleLetterTimer());
		squareArray[13][5].setTimer(new TrippleLetterTimer());
		squareArray[13][9].setTimer(new TrippleLetterTimer());

		// set Double Letter Timer
		squareArray[0][3].setTimer(new DoubleLetterTimer());
		squareArray[0][11].setTimer(new DoubleLetterTimer());
		squareArray[2][6].setTimer(new DoubleLetterTimer());
		squareArray[2][8].setTimer(new DoubleLetterTimer());
		squareArray[3][0].setTimer(new DoubleLetterTimer());
		squareArray[3][7].setTimer(new DoubleLetterTimer());
		squareArray[3][14].setTimer(new DoubleLetterTimer());
		squareArray[6][2].setTimer(new DoubleLetterTimer());
		squareArray[6][6].setTimer(new DoubleLetterTimer());
		squareArray[6][8].setTimer(new DoubleLetterTimer());
		squareArray[6][12].setTimer(new DoubleLetterTimer());
		squareArray[7][3].setTimer(new DoubleLetterTimer());
		squareArray[7][11].setTimer(new DoubleLetterTimer());
		squareArray[8][2].setTimer(new DoubleLetterTimer());
		squareArray[8][6].setTimer(new DoubleLetterTimer());
		squareArray[8][8].setTimer(new DoubleLetterTimer());
		squareArray[8][12].setTimer(new DoubleLetterTimer());
		squareArray[11][0].setTimer(new DoubleLetterTimer());
		squareArray[11][7].setTimer(new DoubleLetterTimer());
		squareArray[11][14].setTimer(new DoubleLetterTimer());
		squareArray[12][6].setTimer(new DoubleLetterTimer());
		squareArray[12][8].setTimer(new DoubleLetterTimer());
		squareArray[14][3].setTimer(new DoubleLetterTimer());
		squareArray[14][11].setTimer(new DoubleLetterTimer());
	}

	public boolean onBoard(int x, int y) {
		if ((x < boardSize) && (y < boardSize) && (y >= 0) && (x >= 0)) {
			return true;
		} else {
			return false;
		}
	}

	public Square getSquare(int x, int y) {
		if (!onBoard(x, y)) {
			return null;
		}

		return squareArray[x][y];

	}

	/**
	 * Check the move is valid or not
	 * 
	 * @param move
	 * @param firstFlag
	 * @return
	 */
	public boolean isValid(Move move, boolean firstFlag) {
		// check the first move
		if (firstFlag) {
			if (!move.containSquare(starSquare)) {
				return false;
			}

			if (moveOnSameLine(move) == "false") {
				return false;
			}
			if (moveOnSameLine(move) == "row") {
				if (makeKeyWord(move, "row") == null) {
					return false;
				}
			}
			if (moveOnSameLine(move) == "col") {
				if (makeKeyWord(move, "col") == null) {
					return false;
				}
			}
			return true;
		}

		// check the general move
		Iterator<Map.Entry<Square, Tile>> it = move.getTileMap().entrySet().iterator();
		int count = 0;
		if (moveOnSameLine(move) == "false") {
			return false;
		}

		if (moveOnSameLine(move) == "row") {
			if (makeKeyWord(move, "row") == null) {
				return false;
			} else {
				while (it.hasNext()) {
					Map.Entry<Square, Tile> entryTmp = it.next();
					int x = entryTmp.getKey().getX();
					int y = entryTmp.getKey().getY();
					Square square1 = getSquare(x - 1, y);
					Square square2 = getSquare(x + 1, y);
					Square square3 = getSquare(x, y - 1);
					Square square4 = getSquare(x, y + 1);

					if (square1 != null) {
						if (getSquare(x - 1, y).isOccuppied()) {
							count++;
						}
					}
					if (square2 != null) {
						if (square2.isOccuppied()) {
							count++;
						}
					}
					if (square3 != null) {
						if (square3.isOccuppied()) {
							count++;
						}
					}
					if (square4 != null) {
						if (square4.isOccuppied()) {
							count++;
						}
					}
				}

				if (count > 0) {
					return true;
				}
			}
		}

		if (moveOnSameLine(move) == "col") {
			if (makeKeyWord(move, "col") == null) {
				return false;
			} else {
				while (it.hasNext()) {
					Map.Entry<Square, Tile> entryTmp = it.next();
					int x = entryTmp.getKey().getX();
					int y = entryTmp.getKey().getY();
					Square square1 = getSquare(x - 1, y);
					Square square2 = getSquare(x + 1, y);
					Square square3 = getSquare(x, y - 1);
					Square square4 = getSquare(x, y + 1);

					if (square1 != null) {
						if (getSquare(x - 1, y).isOccuppied()) {
							count++;
						}
					}
					if (square2 != null) {
						if (square2.isOccuppied()) {
							count++;
						}
					}
					if (square3 != null) {
						if (square3.isOccuppied()) {
							count++;
						}
					}
					if (square4 != null) {
						if (square4.isOccuppied()) {
							count++;
						}
					}
				}

				if (count > 0) {
					return true;
				}
			}
		}
		return false;
	}

	public String moveOnSameLine(Move move) {
		Boolean rowCheck = checkRow(move);
		Boolean colCheck = checkCol(move);
		if (rowCheck) {
			return "row";
		}
		if (colCheck) {
			return "col";
		}
		return "false";
	}

	public boolean checkRow(Move move) {
		Boolean rowTemp = false;
		Iterator<Map.Entry<Square, Tile>> it = move.getTileMap().entrySet().iterator();
		if (it.hasNext()) {
			int y = it.next().getKey().getY();
			rowTemp = true;

			while (it.hasNext()) {
				if (y != it.next().getKey().getY()) {
					rowTemp = false;
					break;
				}
			}
		}

		return rowTemp;
	}

	public boolean checkCol(Move move) {
		Boolean colTemp = false;
		Iterator<Map.Entry<Square, Tile>> it = move.getTileMap().entrySet().iterator();
		if (it.hasNext()) {
			int x = it.next().getKey().getX();
			colTemp = true;

			while (it.hasNext()) {
				if (x != it.next().getKey().getX()) {
					colTemp = false;
					break;
				}
			}
		}
		return colTemp;
	}

	/**
	 * Construct the first key word
	 * 
	 * @param move
	 * @param rowColIndentify
	 * @return
	 */
	public Word makeKeyWord(Move move, String rowColIndentify) {
		Iterator<Map.Entry<Square, Tile>> it = move.getTileMap().entrySet().iterator();
		if (rowColIndentify == "row") {
			Map.Entry<Square, Tile> entry = it.next();
			int startX = entry.getKey().getX();
			int startY = entry.getKey().getY();
			int endX = startX;
			int endY = startY;

			while (it.hasNext()) {
				Map.Entry<Square, Tile> entryTmp = it.next();
				Square tmp = entryTmp.getKey();
				int tmpX = tmp.getX();
				if (tmpX < startX) {
					startX = tmpX;
				}
				if (tmpX > endX) {
					endX = tmpX;
				}
			}

			// check the tiles in the move are adjacent
			for (int i = startX + 1; i < endX + 1; i++) {
				boolean flag = false;

				if (getSquare(i, startY).isOccuppied() == false) {
					if (move.containSquare(getSquare(i, startY))) {
						flag = true;
					}

					if (flag == false) {
						return null;
					}
				}
			}

			while (startX > 0 && (getSquare(startX - 1, startY).isOccuppied() == true)) {
				startX--;
			}
			while (endX > 14 && (getSquare(endX + 1, endY).isOccuppied() == true)) {
				endX++;
			}
			List<Tile> tileList = getTile(startX, startY, endX, endY, move, rowColIndentify);
			Word word = new Word(startX, startY, endX, endY, rowColIndentify, tileList);
			return word;

		}
		if (rowColIndentify == "col") {
			Map.Entry<Square, Tile> entry = it.next();
			int startX = entry.getKey().getX();
			int endX = startX;
			int startY = entry.getKey().getY();
			int endY = startY;
			while (it.hasNext()) {
				Map.Entry<Square, Tile> entryTmp = it.next();
				Square tmp = entryTmp.getKey();
				int tmpY = tmp.getY();
				if (tmpY > startY) {
					startY = tmpY;
				}
				if (tmpY < endY) {
					endY = tmpY;
				}
			}

			// check the tiles in the move are adjacent
			for (int i = startY - 1; i > endY - 1; i--) {
				boolean flag = false;

				if (getSquare(startX, i).isOccuppied() == false) {
					if (move.containSquare(getSquare(startX, i))) {
						flag = true;
					}

					if (flag == false) {
						return null;
					}
				}
			}

			while (startY < 14 && (getSquare(startX, startY + 1).isOccuppied() == true)) {
				startY++;
			}
			while (endX > 0 && (getSquare(endX, endY - 1).isOccuppied() == true)) {
				endY--;
			}
			List<Tile> tileList = getTile(startX, startY, endX, endY, move, rowColIndentify);
			Word word = new Word(startX, startY, endX, endY, rowColIndentify, tileList);
			return word;

		}
		return null;

	}

	/**
	 * Check adjacent word
	 * 
	 * @param move
	 * @param rowColIndentify
	 * @return
	 */
	public List<Word> makeAdjacentWord(Move move, String rowColIndentify) {
		Iterator<Map.Entry<Square, Tile>> it = move.getTileMap().entrySet().iterator();
		List<Word> words = new ArrayList<Word>();
		if (rowColIndentify == "row") {
			while (it.hasNext()) {
				Map.Entry<Square, Tile> entry = it.next();
				int startX = entry.getKey().getX();
				int startY = entry.getKey().getY();
				int endX = startX;
				int endY = startY;

				while (startY < 14 && getSquare(startX, startY + 1).isOccuppied() == true) {
					startY++;
				}

				while (endY > 0 && getSquare(endX, endY - 1).isOccuppied() == true) {
					endY--;
				}
				if (startY != endY) {
					List<Tile> tileList = getTile(startX, startY, endX, endY, move, "col");
					Word wordTmp = new Word(startX, startY, endX, endY, "col", tileList);
					words.add(wordTmp);
				}
			}
		}

		if (rowColIndentify == "col") {
			while (it.hasNext()) {
				Map.Entry<Square, Tile> entry = it.next();
				int startX = entry.getKey().getX();
				int startY = entry.getKey().getY();
				int endX = startX;
				int endY = startY;

				while (startX > 0 && getSquare(startX - 1, startY).isOccuppied() == true) {
					startX--;
				}

				while (endX > 0 && getSquare(endX + 1, endY).isOccuppied() == true) {
					endX++;
				}
				if (startX != endX) {
					List<Tile> tileList = getTile(startX, startY, endX, endY, move, "row");
					Word wordTmp = new Word(startX, startY, endX, endY, "row", tileList);
					words.add(wordTmp);
				}
			}
		}
		return words;
	}

	public List<Tile> getTile(int startX, int startY, int endX, int endY, Move move, String direction) {
		Map<Square, Tile> moveMap = move.getTileMap();
		List<Tile> tileList = new ArrayList<>();
		if (direction == "row") {
			for (int i = startX; i < endX + 1; i++) {
				Square squareTmp = getSquare(i, startY);
				if (squareTmp.isOccuppied()) {
					tileList.add(squareTmp.getTile());
				} else {
					tileList.add(moveMap.get(squareTmp));
				}
			}

		}
		if (direction == "col") {
			for (int i = startY; i > endY - 1; i--) {
				Square squareTmp = getSquare(startX, i);
				if (squareTmp.isOccuppied()) {
					tileList.add(squareTmp.getTile());
				} else {
					tileList.add(moveMap.get(squareTmp));
				}
			}

		}
		return tileList;
	}

	/**
	 * Add special tile to board
	 * 
	 * @param move
	 */
	public void addSpecialTile(Move move) {
		if (!move.hasSpecialTile()) {
			return;
		}
		SpecialTile specialTile = move.getSpecialTile();
		move.getSpecialTileSquare().setSpecialTile(specialTile);
	}

	public void calMoveScore(Move move, Player player, Boolean boomFlag, Boolean negativeFlag) {
		int score = 0;
		String rowColIndentify = moveOnSameLine(move);
		List<Word> wordsList = new ArrayList<Word>();
		if (boomFlag) {
			Word word = makeBoomKeyWord(move, rowColIndentify);
			List<Word> words = makeAdjacentWord(move, rowColIndentify);
			wordsList.add(word);
			wordsList.addAll(words);
			for (Word tmp : wordsList) {
				tmp.calBoomVal(this);
				score += tmp.getValue();
			}
		} else {
			Word word = makeKeyWord(move, rowColIndentify);
			List<Word> words = makeAdjacentWord(move, rowColIndentify);
			wordsList.add(word);
			wordsList.addAll(words);
			for (Word tmp : words) {
				tmp.calValue(this);
				score += tmp.getValue();
			}
			if (negativeFlag) {
				score = -score;
			}
			player.setLastWords(wordsList);
			player.setLastScore(score);
			player.addScore(score);
		}

	}

	public Word makeBoomKeyWord(Move move, String rowColIndentify) {
		Map<Square, Tile> moveMap = move.getTileMap();
		List<Square> boomTileList = move.getBoomSquareList();
		Iterator<Map.Entry<Square, Tile>> it = moveMap.entrySet().iterator();
		List<Tile> tiles = new ArrayList<Tile>();
		Map<Square, Tile> squareMap = new HashMap<>();
		Iterator<Map.Entry<Square, Tile>> it2 = moveMap.entrySet().iterator();

		while (it2.hasNext()) {
			Map.Entry<Square, Tile> entry = it2.next();
			squareMap.put(entry.getKey(), entry.getValue());
		}

		if (rowColIndentify == "row") {
			Map.Entry<Square, Tile> entry = it.next();
			int startX = entry.getKey().getX();
			int startY = entry.getKey().getY();
			int endX = startX;
			int endY = startY;
			int startXsave = startX;
			while (it.hasNext()) {
				Map.Entry<Square, Tile> entryTmp = it.next();
				Square tmp = entryTmp.getKey();
				int tmpX = tmp.getX();
				if (tmpX < startX) {
					startX = tmpX;
				}
				if (tmpX > endX) {
					endX = tmpX;
				}
			}
			Square startSquare = getSquare(startX, startY);
			Tile startTile = moveMap.get(startSquare);
			tiles.add(startTile);

			while (startX > 0 && ((getSquare(startX - 1, startY).isOccuppied() == true)
					|| (move.containBoomSquare(getSquare(startX - 1, startY)) == true))) {
				Square squareTmp = getSquare(startX - 1, startY);
				if (squareTmp.isOccuppied() == true) {
					tiles.add(squareTmp.getTile());
				}
				startX--;
			}

			if (endX != startXsave) {
				Square endSquare = getSquare(endX, endY);
				Tile endTile = moveMap.get(endSquare);
				tiles.add(endTile);
			}

			while (endX < 14 && ((getSquare(endX + 1, endY).isOccuppied() == true)
					|| (move.containBoomSquare(getSquare(endX + 1, endY)) == true))) {
				Square squareTmp = getSquare(endX - 1, endY);
				if (squareTmp.isOccuppied() == true) {
					tiles.add(squareTmp.getTile());
				}
				endX++;
			}
			Word word = new Word(tiles, squareMap);
			return word;

		}

		if (rowColIndentify == "col") {
			Map.Entry<Square, Tile> entry = it.next();
			int startX = entry.getKey().getX();
			int startY = entry.getKey().getY();
			int endX = startX;
			int endY = startY;
			int startYsave = startY;
			while (it.hasNext()) {
				Map.Entry<Square, Tile> entryTmp = it.next();
				Square tmp = entryTmp.getKey();
				int tmpY = tmp.getY();
				if (tmpY > startY) {
					startY = tmpY;
				}
				if (tmpY < endY) {
					endY = tmpY;
				}
			}

			Square startSquare = getSquare(startX, startY);
			Tile startTile = moveMap.get(startSquare);
			tiles.add(startTile);

			while (startY < 14 && ((getSquare(startX, startY + 1).isOccuppied() == true)
					|| (move.containBoomSquare(getSquare(startX, startY + 1)) == true))) {
				Square squareTmp = getSquare(startX, startY + 1);
				if (squareTmp.isOccuppied() == true) {
					tiles.add(squareTmp.getTile());
				}
				startY++;
			}

			if (endY != startYsave) {
				Square endSquare = getSquare(endX, endY);
				Tile endTile = moveMap.get(endSquare);
				tiles.add(endTile);
			}

			while (endX > 0 && ((getSquare(endX, endY - 1).isOccuppied() == true)
					|| (move.containBoomSquare(getSquare(endX, endY - 1)) == true))) {
				Square squareTmp = getSquare(endX, endY - 1);
				if (squareTmp.isOccuppied() == true) {
					tiles.add(squareTmp.getTile());
				}
				endX--;
			}
			Word word = new Word(tiles, squareMap);
			return word;
		}
		return null;

	}

	/**
	 * Active special tile
	 * 
	 * @param move
	 */
	public void activeSpecialTile(Move move, ScrabbleSystem scrabbleSystem) {
		Map<Square, Tile> moveMap = move.getTileMap();
		Map<Square, SpecialTile> removedSpecial = new HashMap<>();
		Iterator<Map.Entry<Square, Tile>> it = moveMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Square, Tile> entryTmp = it.next();
			Square square = entryTmp.getKey();
			if (square.hasSpecialTile()) {
				SpecialTile specialTile = square.getSpecialTile();
				removedSpecial.put(square, specialTile);
				square.getSpecialTile().makeSpecialEffect(scrabbleSystem, square);
				square.removeSpecialTile();

			}
			move.setRemovedSpecialTile(removedSpecial);
		}
	}

	public void addTileToBoard(Move move) {
		Iterator<Map.Entry<Square, Tile>> it = move.getTileMap().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Square, Tile> entry = it.next();
			Square square = entry.getKey();
			square.setTile(entry.getValue());
		}
	}

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("1", 1);
		map.put("2", 2);
		map.put("3", 3);
		map.put("4", 4);
		map.put("5", 5);
		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		Map.Entry<String, Integer> entry = it.next();
		String startX = entry.getKey();
		while (it.hasNext()) {
			Map.Entry<String, Integer> entry2 = it.next();
			System.out.println(entry2.getKey());
		}

		while (it.hasNext()) {
			Map.Entry<String, Integer> entry2 = it.next();
			System.out.println(entry2.getKey());
		}

	}
}