package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.List;

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

	public void startNewGame() {
		move = new Move();
		firstFlag = true;

		for (int i = 0; i < playerNum; i++) {
			List<Tile> tiles = letterBag.getRandomTiles(PLAYER_TILE_NUM);
			players.get(i).addTile(tiles);
		}

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
	}

	/**
	 * Clear move for next player
	 */
	public void clearMove() {
		move = new Move();
	}

	public void playMove(Move move) {
		board.isValid(move, firstFlag);
	}

}
