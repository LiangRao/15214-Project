package edu.cmu.cs.cs214.hw4.core.specialTile;

import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleSystem;
import edu.cmu.cs.cs214.hw4.core.Square;

public class RetrieveOrder implements SpecialTile {
	private final String NAME = "RetrieveOrder";
	private final int PRICE = 20;
	private Player Owner;

	public Player getOwner() {
		return Owner;
	}

	public void setOwner(Player owner) {
		Owner = owner;
	}

	public String getName() {
		return NAME;
	}

	public int getPrice() {
		return PRICE;
	}

	@Override
	public void makeSpecialEffect(ScrabbleSystem scrabbleSystem, Square square) {
		scrabbleSystem.retrieveOrder();

	}

}
