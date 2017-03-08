package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TurnControl {
	private List<Player> players;
	private int orderNum;
	private int playerNum;

	public TurnControl() {
		players = new ArrayList<Player>();
		orderNum = 0;
		playerNum = 0;
	}

	public void addPlayer(Player player) {
		players.add(player);
		playerNum++;
	}

	public Player getCurrentPlayer() {
		return players.get(orderNum);
	}

	public Player getLastPlayer() {
		int n = 0;
		if (orderNum == 0) {
			n = playerNum - 1;
		} else {
			n = (orderNum - 1) % playerNum;
		}

		return players.get(n);
	}

	public void updateTurn() {
		orderNum++;
		orderNum %= playerNum;
	}

	public void retrieveTurn() {
		if (orderNum == 0) {
			orderNum = playerNum - 1;
		} else {
			orderNum--;
			orderNum %= playerNum;
		}

	}

	public void reverseTurn() {
		List<Player> playersTmp = new ArrayList<Player>();
		for (int i = playerNum - 1; i > 0; i--) {
			playersTmp.add(players.get(i));
		}
		players = playersTmp;
		orderNum = (playerNum - orderNum - 1) % playerNum;
	}

	public void randomTurn() {
		Random rand = new Random();
		int s = rand.nextInt(playerNum);
		orderNum = s;
	}

	public void skipTurn() {
		updateTurn();
	}
}
