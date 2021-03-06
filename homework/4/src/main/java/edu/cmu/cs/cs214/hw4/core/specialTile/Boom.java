package edu.cmu.cs.cs214.hw4.core.specialTile;

import edu.cmu.cs.cs214.hw4.core.Board;
import edu.cmu.cs.cs214.hw4.core.Move;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleSystem;
import edu.cmu.cs.cs214.hw4.core.Square;

/**
 * A boom special tile, which will remove all tiles in a 3-tile radius on the
 * board from the board.
 * 
 * @author raoliang
 *
 */
public class Boom implements SpecialTile {
	private final String name = "Boom";
	private final int price = 20;
	private Player owner;
	private static final int BOARD_SIZE = 15;
	private static final int BOOM_SIZE = 5;

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
		// CHECKSTYPE:OFF
		Board board = scrabbleSystem.getBoard();
		// Move move = scrabbleSystem.getMove();
		int x = square.getX();
		int y = square.getY();
		int xTmp;
		int yTmp;

		for (int i = 0; i < BOOM_SIZE; i++) {
			if ((x + i - 2) > 0 && (x + i - 2) < BOARD_SIZE - 1) {
				xTmp = x + i - 2;
			} else {
				continue;
			}
			for (int j = 0; j < BOOM_SIZE; j++) {
				if ((y + j - 2) > 0 && (y + j - 2) < BOARD_SIZE - 1) {
					yTmp = y + j - 2;
				} else {
					continue;
				}
				Square squareTmp = board.getSquare(xTmp, yTmp);
				if (squareTmp.hasTile()) {
					move.addBoomSquareList(squareTmp);
					// squareTmp.removeTile();
				}
				if (move.containSquare(squareTmp)) {
					move.addBoomSquareList(squareTmp);
					// move.removeTile(squareTmp);
				}

			}
		}
		scrabbleSystem.setBoomFlag(true);
	}

}
