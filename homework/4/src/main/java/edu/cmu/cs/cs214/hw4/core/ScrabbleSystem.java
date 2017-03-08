package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;

public class ScrabbleSystem {
	private List<Player> players;
	private final String path;
	private int playerNum;
	private static final int MAX_PLAYER_NUM = 4;
	private static final int PLAYER_TILE_NUM = 7;
	private LetterBag letterBag;
	private TurnControl turnControl;
	private Dictionary dictionary;
	private Board board;
	private Move move;
	private boolean firstFlag;
	private boolean isGameOver;
	private boolean negativeFlag;
	private boolean boomFlag;

	/**
	 * Constructor for Scrabble System
	 */
	public ScrabbleSystem() {
		this.players = new ArrayList<>();
		this.playerNum = 0;
		this.isGameOver = false;
		this.path = "words.txt";
		this.letterBag = new LetterBag();
		this.turnControl = new TurnControl();
		this.dictionary = new Dictionary(path);
		this.board = new Board();

	}

	public Board getBoard() {
		return board;
	}

	public Move getMove() {
		return move;
	}

	public void setNegativeFlag(boolean negativeFlag) {
		this.negativeFlag = negativeFlag;
	}

	public void startNewGame() {
		move = new Move();
		firstFlag = true;
		negativeFlag = false;
		boomFlag = false;
		for (int i = 0; i < playerNum; i++) {
			List<Tile> tiles = letterBag.getRandomTiles(PLAYER_TILE_NUM);
			players.get(i).addTile(tiles);
		}
		turnControl.randomTurn();

	}

	/**
	 * Add player to the game
	 * 
	 * @param player
	 *            the player need to add
	 */
	public void addPlayer(Player player) {
		if (playerNum == MAX_PLAYER_NUM) {
			System.out.println("The number of players is out of range!");
			return;
		}
		players.add(player);
		playerNum++;
		turnControl.addPlayer(player);
	}

	/**
	 * Clear move for next player
	 */
	public void clearMove() {
		move = new Move();
	}

	public void resetBoomFlag() {
		boomFlag = false;
	}

	public void resetNegFlag() {
		negativeFlag = false;
	}

	public void resetFirstFlag() {
		firstFlag = false;
	}

	/**
	 * A player plays a set of tiles and submits
	 * 
	 * @param move
	 *            a set of tiles
	 */
	public void playMove(Move move) {
		// check valid
		if (!board.isValid(move, firstFlag)) {
			System.out.println("The move is not valid! Please play again!");
			return;
		}
		// add special tile
		board.addSpecialTile(move);
		// active special tile
		board.activeSpecialTile(move, this);

		Player currentPlayer = turnControl.getCurrentPlayer();
		board.calMoveScore(move, currentPlayer, boomFlag, negativeFlag);

		board.addTileToBoard(move);

		List<Tile> tiles = new ArrayList<Tile>();
		Iterator<Map.Entry<Square, Tile>> it = move.getTileMap().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Square, Tile> entry = it.next();
			tiles.add(entry.getValue());
		}
		currentPlayer.removeTile(tiles);
		int tileNum = tiles.size();
		List<Tile> randomTiles = new ArrayList<>();
		randomTiles.addAll(letterBag.getRandomTiles(tileNum));
		currentPlayer.addTile(randomTiles);
		currentPlayer.setLastRandomTile(randomTiles);
		currentPlayer.setLastMove(move);

		updateOrder();
		resetBoomFlag();
		resetNegFlag();
		resetFirstFlag();
	}

	public void updateOrder() {
		Player currentPlayer = turnControl.getCurrentPlayer();
		if (!currentPlayer.getNextTurnFlag()) {
			turnControl.skipTurn();
			currentPlayer.setNextTurnFlag(true);
		}
		turnControl.updateTurn();
		clearMove();
	}

	public void reverseOrder() {
		turnControl.reverseTurn();
	}

	public void retrieveOrder() {
		turnControl.retrieveTurn();
	}

	public void exchangeTile(List<Tile> tiles) {
		int size = tiles.size();
		List<Tile> randomTiles = letterBag.getRandomTiles(size);
		Player currentPlayer = turnControl.getCurrentPlayer();
		currentPlayer.removeTile(tiles);
		currentPlayer.addTile(randomTiles);
		updateOrder();

	}

	public List<Player> getWinner() {
		List<Player> winners = new ArrayList<>();
		int maxScore = 0;
		for (Player player : players) {
			int scoreTmp = player.getScore();
			if (scoreTmp > maxScore) {
				maxScore = scoreTmp;
			}
		}

		for (Player player : players) {
			int scoreTmp = player.getScore();
			if (scoreTmp == maxScore) {
				winners.add(player);
			}
		}

		return winners;
	}

	public void challenge(Player player) {
		Player lastPlayer = turnControl.getLastPlayer();
		List<Word> lastWords = lastPlayer.getLastWords();
		Boolean challengeFlag = false;
		for (Word word : lastWords) {
			if (!dictionary.isIn(word)) {
				challengeFlag = true;
				break;
			}
		}

		if (challengeFlag) {
			List<Tile> moveTile = new ArrayList<>();
			int lastScore = lastPlayer.getLastScore();
			lastPlayer.minusScore(lastScore);
			Move lastMove = lastPlayer.getLastMove();
			List<Tile> randomTiles = lastPlayer.getLastRandomTile();
			lastPlayer.removeTile(randomTiles);
			letterBag.addTile(randomTiles);
			Iterator<Map.Entry<Square, Tile>> it = lastMove.getTileMap().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Square, Tile> entry = it.next();
				Square square = entry.getKey();
				square.removeTile();
				Tile tile = entry.getValue();
				moveTile.add(tile);
			}
			lastPlayer.addTile(moveTile);

			// restore special Tile
			Map<Square, SpecialTile> removedSpecial = move.getRemovedSpecialTile();
			Iterator<Map.Entry<Square, SpecialTile>> it2 = removedSpecial.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry<Square, SpecialTile> entry = it2.next();
				entry.getKey().restoreLastSpecialTile(entry.getValue());
			}

		} else {
			player.setNextTurnFlag(false);
		}
	}
}
