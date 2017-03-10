package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.cmu.cs.cs214.hw4.core.specialTile.Boom;
import edu.cmu.cs.cs214.hw4.core.specialTile.NegativePoint;
import edu.cmu.cs.cs214.hw4.core.specialTile.RetrieveOrder;
import edu.cmu.cs.cs214.hw4.core.specialTile.ReverseOrder;
import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;

/**
 * The system class from the entire scrabble game, which actors as a controller
 * between different class.
 * 
 * @author raoliang
 *
 */
public class ScrabbleSystem {
	private List<Player> players;
	private final String path;
	private int playerNum;
	private static final int MAX_PLAYER_NUM = 4;
	private static final int PLAYER_TILE_NUM = 7;
	private static final int MAX_PASS_TIME = 3;
	private LetterBag letterBag;
	private TurnControl turnControl;
	private Dictionary dictionary;
	private Board board;
	private Move gameMove;
	private boolean firstFlag;
	private boolean gameOverFlag;
	private boolean isChallengeFlag;
	private boolean negativeFlag;
	private boolean boomFlag;
	private List<SpecialTile> specialStore;

	/**
	 * Constructor for Scrabble System
	 */
	public ScrabbleSystem() {
		this.players = new ArrayList<>();
		this.playerNum = 0;
		this.gameOverFlag = false;
		this.isChallengeFlag = false;
		this.path = "words.txt";
		this.letterBag = new LetterBag();
		this.turnControl = new TurnControl();
		this.dictionary = new Dictionary(path);
		this.board = new Board();
		this.specialStore = new ArrayList<>();

	}

	/**
	 * Get the game board
	 * 
	 * @return the game board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Get the current game move
	 * 
	 * @return the current game move
	 */
	public Move getMove() {
		return gameMove;
	}

	/**
	 * Get all players involving in the game
	 * 
	 * @return all players involving in the game
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Get the letter bag
	 * 
	 * @return the letter bag
	 */
	public LetterBag getLetterBag() {
		return letterBag;
	}

	/**
	 * Get all kinds of the special tiles
	 * 
	 * @return all kinds of the special tiles
	 */
	public List<SpecialTile> getSpecialStore() {
		return specialStore;
	}

	/**
	 * Get the GameOverFlag
	 * 
	 * @return the GameOverFlag
	 */
	public boolean isGameOverFlag() {
		return gameOverFlag;
	}

	/**
	 * Set the negtiveFlag
	 * 
	 * @param negativeFlag
	 *            the value to set the negativeFlag
	 */
	public void setNegativeFlag(boolean negativeFlag) {
		this.negativeFlag = negativeFlag;
	}

	/**
	 * Set the challengeFlag
	 * 
	 * @param isChallengeFlag
	 *            the value to set the challengeFlag
	 */
	public void setChallengeFlag(boolean isChallengeFlag) {
		this.isChallengeFlag = isChallengeFlag;
	}

	/**
	 * Get the isChallengeFlag
	 * 
	 * @return the isChallengeFlag
	 */
	public boolean isChallengeFlag() {
		return isChallengeFlag;
	}

	/**
	 * Add player to the game
	 * 
	 * @param player
	 *            the player need to add
	 */
	public void addPlayer(Player player) {
		if (playerNum == MAX_PLAYER_NUM) {
			System.out.println("The number of players is out of range! Fail to add Player! ");
			return;
		}
		players.add(player);
		playerNum++;
		turnControl.addPlayer(player);
		System.out.println("Add " + player.getName() + " to the game!");
	}

	/**
	 * Initial fields when the game is start
	 */
	public void startNewGame() {
		gameMove = new Move();
		firstFlag = true;
		negativeFlag = false;
		boomFlag = false;
		for (int i = 0; i < playerNum; i++) {
			List<Tile> tiles = letterBag.getRandomTiles(PLAYER_TILE_NUM);
			players.get(i).addTile(tiles);
		}
		turnControl.randomTurn();

		specialStore.add(new Boom());
		specialStore.add(new NegativePoint());
		specialStore.add(new RetrieveOrder());
		specialStore.add(new ReverseOrder());
	}

	/**
	 * Get the current player
	 * 
	 * @return the current player
	 */
	public Player getCurrentPlayer() {
		return turnControl.getCurrentPlayer();
	}

	/**
	 * Clear move for next player
	 */
	public void clearMove() {
		gameMove = new Move();
	}

	/**
	 * Reset the boomFlag
	 */
	public void resetBoomFlag() {
		boomFlag = false;
	}

	/**
	 * Set the boomFlag
	 * 
	 * @param boomFlag
	 *            the value to set the boomFlag
	 */
	public void setBoomFlag(boolean boomFlag) {
		this.boomFlag = boomFlag;
	}

	/**
	 * Reset the negativeFlag
	 */
	public void resetNegFlag() {
		negativeFlag = false;
	}

	/**
	 * Reset the firstFlag
	 */
	public void resetFirstFlag() {
		firstFlag = false;
	}

	/**
	 * Reset the isChallengeFlag
	 */
	public void resetIsChallengeFlag() {
		isChallengeFlag = false;
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

		Player currentPlayer = getCurrentPlayer();
		board.calMoveScore(move, currentPlayer, boomFlag, negativeFlag);

		board.addTileToBoard(move);

		List<Tile> tiles = new ArrayList<Tile>();
		Map<Square, Tile> moveMap = move.getTileMap();
		Iterator<Map.Entry<Square, Tile>> it = moveMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Square, Tile> entry = it.next();
			tiles.add(entry.getValue());
		}
		SpecialTile specialTile = move.getSpecialTile();
		// remove the player's tiles and the special tile
		currentPlayer.removeTile(tiles);
		currentPlayer.removeSpecialTile(specialTile);

		int tileNum = moveMap.size();
		if (letterBag.isEmpty()) {
			gameOverFlag = true;
		} else {
			List<Tile> randomTiles = letterBag.getRandomTiles(tileNum);
			currentPlayer.addTile(randomTiles);
			currentPlayer.setLastRandomTile(randomTiles);
		}
		currentPlayer.setLastMove(move);

		resetBoomFlag();
		resetNegFlag();
		resetFirstFlag();

	}

	/**
	 * Update current order to the next player
	 */
	public void updateOrder() {
		if (isGameOver()) {
			List<Player> players = getWinner();
			System.out.println("The game is over!");

			for (Player player : players) {
				System.out.println("The winner is" + player.getName());
			}
			return;
		}
		Player currentPlayer = getCurrentPlayer();
		if (!currentPlayer.getNextTurnFlag()) {
			turnControl.skipTurn();
			currentPlayer.setNextTurnFlag(true);
		}
		turnControl.updateTurn();
		clearMove();
	}

	/**
	 * Reverse current order
	 */
	public void reverseOrder() {
		turnControl.reverseTurn();
	}

	/**
	 * Retrieve the current order
	 */
	public void retrieveOrder() {
		turnControl.retrieveTurn();
	}

	/**
	 * Current player exchanges some tiles
	 * 
	 * @param tiles
	 *            the tiles needs to exchange
	 */
	public void exchangeTile(List<Tile> tiles) {
		int size = tiles.size();
		List<Tile> randomTiles = letterBag.getRandomTiles(size);
		Player currentPlayer = getCurrentPlayer();
		currentPlayer.removeTile(tiles);
		currentPlayer.addTile(randomTiles);
		updateOrder();

	}

	/**
	 * Get the winner of the game
	 * 
	 * @return the winner of the game
	 */
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

	/**
	 * Invork a challenge event
	 * 
	 * @param player
	 *            the player who invorks a challenge event
	 */
	public void challenge(Player player) {
		if (!isChallengeFlag) {
			updateOrder();
			return;
		}
		Player lastPlayer = turnControl.getCurrentPlayer();
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
			Map<Square, SpecialTile> removedSpecial = lastMove.getRemovedSpecialTile();
			Iterator<Map.Entry<Square, SpecialTile>> it2 = removedSpecial.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry<Square, SpecialTile> entry = it2.next();
				entry.getKey().restoreLastSpecialTile(entry.getValue());
			}

		} else {
			player.setNextTurnFlag(false);
		}

		resetIsChallengeFlag();
		updateOrder();
	}

	/**
	 * 
	 * @param specialTileName
	 *            the name of special Tile which the player want to buy
	 */
	public void buySpecialTile(String specialTileName) {
		Player currentPlayer = getCurrentPlayer();

		for (SpecialTile specialTile : specialStore) {
			String name = specialTile.getName();
			if (name.equals(specialTileName)) {
				int price = specialTile.getPrice();
				if (currentPlayer.getScore() > price) {
					currentPlayer.addSpecialTiles(specialTile);
					currentPlayer.minusScore(price);
					break;
				} else {
					System.out.println("Your total scores is not enough to buy the special Tile! ");
				}
			}

		}

	}

	/**
	 * The player submit a pass require
	 * 
	 * @param move
	 *            the move that the current player submit
	 */
	public void pass(Move move) {
		Player currentplayer = getCurrentPlayer();
		board.addSpecialTile(move);
		if (move.hasSpecialTile()) {
			SpecialTile specialTile = move.getSpecialTile();
			currentplayer.removeSpecialTile(specialTile);
		}
		currentplayer.addPassTime();
		updateOrder();
	}

	/**
	 * Justify the game is over or not
	 * 
	 * @return return true if the game is over
	 */
	public boolean isGameOver() {
		Player currentPlayer = getCurrentPlayer();
		if (currentPlayer.getTileList().size() == 0) {
			return true;
		} else if (currentPlayer.getPassTime() == MAX_PASS_TIME) {
			return true;
		} else if (gameOverFlag == true) {
			return true;
		}
		return false;
	}

}
