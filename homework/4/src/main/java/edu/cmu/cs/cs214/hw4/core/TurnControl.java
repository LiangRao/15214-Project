package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class to control the order of players involving in the game
 * 
 * @author raoliang
 *
 */
public class TurnControl {
	private List<Player> players;
	private int orderNum;
	private int playerNum;

	/**
	 * A constructor
	 */
	public TurnControl() {
		players = new ArrayList<Player>();
		orderNum = 0;
		playerNum = 0;
	}

	/**
	 * Add a player to the game
	 * 
	 * @param player
	 *            the players added to the game
	 */
	public void addPlayer(Player player) {
		players.add(player);
		playerNum++;
	}

	/**
	 * Get the player in current turn
	 * 
	 * @return the player in current turn
	 */
	public Player getCurrentPlayer() {
		return players.get(orderNum);
	}

	/**
	 * Go to the next turn
	 */
	public void updateTurn() {
		orderNum++;
		orderNum %= playerNum;
	}

	/**
	 * Retrieve a turn
	 */
	public void retrieveTurn() {
		if (orderNum == 0) {
			orderNum = playerNum - 1;
		} else {
			orderNum--;
			orderNum %= playerNum;
		}

	}

	/**
	 * Reverse the current turn
	 */
	public void reverseTurn() {
		List<Player> playersTmp = new ArrayList<Player>();
		for (int i = playerNum - 1; i >= 0; i--) {
			playersTmp.add(players.get(i));
		}
		players = playersTmp;
		orderNum = (playerNum - orderNum - 1) % playerNum;
	}

	/**
	 * Get the first random turn of the game
	 */
	public void randomTurn() {
		Random rand = new Random();
		int s = rand.nextInt(playerNum);
		orderNum = s;
	}

	/**
	 * Skip a player's turn
	 */
	public void skipTurn() {
		updateTurn();
	}
}
