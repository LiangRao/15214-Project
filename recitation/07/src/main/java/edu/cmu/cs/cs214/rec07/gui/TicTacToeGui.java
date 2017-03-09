package edu.cmu.cs.cs214.rec07.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.cmu.cs.cs214.rec07.core.GameChangeListener;
import edu.cmu.cs.cs214.rec07.core.TicTacToe;
import edu.cmu.cs.cs214.rec07.core.TicTacToe.Player;

public class TicTacToeGui extends JPanel implements GameChangeListener {

	private JFrame mainFrame;
	private TicTacToe game;
	private JPanel boardPane;
	private JTextArea text;
	private JButton[][] grids;

	public TicTacToeGui(TicTacToe game) {
		this.game = game;
		mainFrame = new JFrame();
		mainFrame.setLayout(new FlowLayout());
		boardPane = new JPanel();
		boardPane.setLayout(new GridLayout(3, 3));
		grids = new JButton[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				grids[i][j] = new JButton();
				int ii = i;
				int jj = j;

				grids[i][j].addActionListener(e -> {
					game.playMove(ii, jj);
				});
				boardPane.add(grids[i][j]);
				grids[i][j].setVisible(true);
			}
		}

	}

	@Override
	public void squareChanged(int row, int col) {

	}

	@Override
	public void currentPlayerChanged(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameEnded(Player winner) {
		// TODO Auto-generated method stub

	}

}
