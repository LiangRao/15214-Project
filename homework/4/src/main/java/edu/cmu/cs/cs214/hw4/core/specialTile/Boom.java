package edu.cmu.cs.cs214.hw4.core.specialTile;

import edu.cmu.cs.cs214.hw4.core.Board;
import edu.cmu.cs.cs214.hw4.core.Move;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleSystem;
import edu.cmu.cs.cs214.hw4.core.Square;

public class Boom implements SpecialTile {
	private final String NAME = "Boom";
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
	public void makeSpecialEffect(ScrabbleSystem scrabbleSystem, Move move, Square square) {
		Board board = scrabbleSystem.getBoard();
		// Move move = scrabbleSystem.getMove();
		int x = square.getX();
		int y = square.getY();
		int xTmp;
		int yTmp;
		for (int i = 0; i < 5; i++) {
			if ((x + i - 2) > 0 && (x + i - 2) < 14) {
				xTmp = x + i - 2;
			} else {
				continue;
			}
			for (int j = 0; j < 5; j++) {
				if ((y + j - 2) > 0 && (y + j - 2) < 14) {
					yTmp = y + j - 2;
				} else {
					continue;
				}
				// System.out.println(xTmp);
				// System.out.println(yTmp);
				Square squareTmp = board.getSquare(xTmp, yTmp);
				if (squareTmp.hasTile()) {
					move.addBoomSquareList(squareTmp);
					squareTmp.removeTile();
				}
				if (move.containSquare(squareTmp)) {
					move.addBoomSquareList(squareTmp);
					move.removeTile(squareTmp);
				}

			}
		}
		scrabbleSystem.setBoomFlag(true);
	}

}
