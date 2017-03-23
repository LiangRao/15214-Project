package edu.cmu.cs.cs214.hw4.core.specialTile;

import edu.cmu.cs.cs214.hw4.core.Move;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleSystem;
import edu.cmu.cs.cs214.hw4.core.Square;

/**
 * The reverse order special tile which will reverse the current turn order
 * 
 * @author raoliang
 *
 */
public class ReverseOrder implements SpecialTile {
	private final String name = "ReverseOrder";
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
	public void makeSpecialEffect(ScrabbleSystem scrabbleSystem, Square square, Move move) {
		scrabbleSystem.reverseOrder();

	}

}
