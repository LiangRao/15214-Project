package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.List;

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

	public void updateTurn() {
		orderNum++;
		orderNum %= playerNum;
	}
}
