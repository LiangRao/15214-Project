package edu.cmu.cs.cs214.hw4.core.specialTile;

import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleSystem;
import edu.cmu.cs.cs214.hw4.core.Square;

/**
 * The negative special tile who will minus the move score from the player
 * 
 * @author raoliang
 *
 */
public class NegativePoint implements SpecialTile {
	private final String name = "NegativePoint";
	private final int price = 30;
	private Player owner;

	@Override
	public Player getOwner() {
		return owner;
	}

	@Override
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void makeSpecialEffect(ScrabbleSystem scrabbleSystem, Square square) {
		scrabbleSystem.setNegativeFlag(true);

	}

}
