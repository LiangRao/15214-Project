package edu.cmu.cs.cs214.hw4.core.specialTile;

import edu.cmu.cs.cs214.hw4.core.Move;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleSystem;
import edu.cmu.cs.cs214.hw4.core.Square;

public interface SpecialTile {
	void makeSpecialEffect(ScrabbleSystem scrabbleSystem, Move move, Square square);

	public Player getOwner();

	public void setOwner(Player owner);

	public String getName();

	public int getPrice();
}
