package edu.cmu.cs.cs214.hw4.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.cmu.cs.cs214.hw4.core.Move;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleSystem;
import edu.cmu.cs.cs214.hw4.core.Square;
import edu.cmu.cs.cs214.hw4.core.Tile;
import edu.cmu.cs.cs214.hw4.core.specialTile.SpecialTile;

public class GamePanel extends JPanel implements GameListener {
	private ScrabbleSystem scrabbleSystem;
	private JPanel boardPanel;
	private JButton[] specialTileButton;
	private JButton[] tileButton;
	private JButton[][] squareButton;
	private JPanel playerInfoPanel;
	private JPanel[] singlePlayerInfo;
	private JPanel tileRackPanel;
	private JPanel specialRackPanel;
	private JPanel controlPanel;
	private JLabel currentPlayerLabel;
	private Tile selectedTile;
	private List<Tile> selectedTileList;
	private Move move;
	private List<Integer> disabledTileButton;

	private final static int BOARD_SIZE = 15;

	public GamePanel(ScrabbleSystem scrabbleSystem) {
		this.scrabbleSystem = scrabbleSystem;
		scrabbleSystem.addGameListner(this);
		selectedTile = null;
		selectedTileList = new ArrayList<Tile>();
		move = new Move();
		disabledTileButton = new ArrayList<>();
		currentPlayerLabel = new JLabel();
		boardPanel = new JPanel();
		tileRackPanel = new JPanel();
		specialRackPanel = new JPanel();
		playerInfoPanel = new JPanel();
		controlPanel = new JPanel();
		buildBoardPanel();
		buildRackPanel();
		buildSpecialRackPanel();
		buildPlayerInfoPanel();
		buildControlPanel();
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(4, 1));
		infoPanel.add(playerInfoPanel);
		infoPanel.add(tileRackPanel);
		infoPanel.add(specialRackPanel);
		infoPanel.add(controlPanel);

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout());
		add(boardPanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.EAST);
		setVisible(true);
	}

	private void buildBoardPanel() {
		squareButton = new JButton[BOARD_SIZE][BOARD_SIZE];
		boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				Square square = scrabbleSystem.getBoard().getSquare(j, BOARD_SIZE - 1 - i);
				squareButton[i][j] = new JButton();
				squareButton[i][j].addActionListener(new squareListener(square, j, BOARD_SIZE - 1 - i));
				squareButton[i][j].setPreferredSize(new Dimension(70, 35));
				if (square.hasTimer()) {
					Color color = square.getTimer().getColor();
					if (color.equals(Color.blue) || color.equals(Color.red)) {
						squareButton[i][j].setForeground(Color.white);
					}
					squareButton[i][j].setBackground(color);
					squareButton[i][j].setOpaque(true);
					squareButton[i][j].setBorderPainted(false);
					String name = square.getTimer().getName();
					squareButton[i][j].setText(name);
					squareButton[i][j].setBorder(BorderFactory.createEtchedBorder());

				}
				boardPanel.add(squareButton[i][j]);
			}
		}
		squareButton[7][7].setBackground(Color.pink);
		squareButton[7][7].setOpaque(true);
		squareButton[7][7].setBorderPainted(false);
		squareButton[7][7].setText("★");
		boardPanel.setPreferredSize(new Dimension(900, 350));

		repaint();

	}

	private void buildRackPanel() {
		tileRackPanel.setLayout(new GridLayout(7, 1));
		tileButton = new JButton[7];

		List<Tile> tiles = scrabbleSystem.getCurrentPlayer().getTileList();
		for (int i = 0; i < 7; i++) {
			tileButton[i] = new JButton();
			Tile tile = tiles.get(i);
			tileButton[i]
					.setText(String.valueOf(tile.getLetter()) + "  [value =" + String.valueOf(tile.getValue()) + " ]");
			tileButton[i].addActionListener(new tileListener(tile, i));
			tileRackPanel.add(tileButton[i]);
		}
		tileRackPanel.setBorder(BorderFactory.createTitledBorder("Tile Rack"));
		repaint();

	}

	private void buildSpecialRackPanel() {
		specialRackPanel.setLayout(new GridLayout(5, 2));
		List<SpecialTile> specialTiles = scrabbleSystem.getCurrentPlayer().getSpecialTiles();
		int size = specialTiles.size();
		int boomNum = 0;
		int negativeNum = 0;
		int retrieveNum = 0;
		int reverseNum = 0;
		int skipNum = 0;
		for (int i = 0; i < size; i++) {
			String name = specialTiles.get(i).getName();
			if (name.equals("Boom")) {
				boomNum++;
			} else if (name.equals("NegativePoint")) {
				negativeNum++;
			} else if (name.equals("RetrieveOrder")) {
				retrieveNum++;
			} else if (name.equals("ReverseOrder")) {
				reverseNum++;
			} else {
				skipNum++;
			}
		}
		specialTileButton = new JButton[5];
		specialTileButton[0] = new JButton("Boom X" + String.valueOf(boomNum));

		specialTileButton[1] = new JButton("NegativePoint X" + String.valueOf(negativeNum));

		specialTileButton[2] = new JButton("RetrieveOrder X" + String.valueOf(retrieveNum));

		specialTileButton[3] = new JButton("ReverseOrder X" + String.valueOf(reverseNum));

		specialTileButton[4] = new JButton("Skip-a-Turn X" + String.valueOf(skipNum));

		specialRackPanel.add(specialTileButton[0]);
		// panel.add(new JLabel("[price: 20]"));
		specialRackPanel.add(specialTileButton[1]);
		// panel.add(new JLabel("[price: 30]"));
		specialRackPanel.add(specialTileButton[2]);
		// panel.add(new JLabel("[price: 20]"));
		specialRackPanel.add(specialTileButton[3]);
		// panel.add(new JLabel("[price: 30]"));
		specialRackPanel.add(specialTileButton[4]);
		// panel.add(new JLabel("[price: 20]"));

		specialRackPanel.setBorder(BorderFactory.createTitledBorder("Special Tile Rack"));

	}

	private void buildPlayerInfoPanel() {
		List<Player> players = scrabbleSystem.getPlayers();
		int size = players.size();
		singlePlayerInfo = new JPanel[size];
		playerInfoPanel.setLayout(new GridLayout(size, 1));
		for (int i = 0; i < size; i++) {
			singlePlayerInfo[i] = new JPanel();
			singlePlayerInfo[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			singlePlayerInfo[i].setLayout(new GridLayout(2, 1));
			singlePlayerInfo[i].setPreferredSize(new Dimension(20, 20));
			Player player = players.get(i);
			String name = player.getName();
			int score = player.getScore();
			JLabel nameLabel = new JLabel("player: " + name);
			JLabel scoreLabel = new JLabel("score:" + String.valueOf(score));
			singlePlayerInfo[i].add(nameLabel);
			singlePlayerInfo[i].add(scoreLabel);
			playerInfoPanel.add(singlePlayerInfo[i]);
		}
		playerInfoPanel.setBorder(BorderFactory.createTitledBorder("ScoreBoard"));

	}

	private void buildControlPanel() {
		controlPanel.setLayout(new GridLayout(2, 1));
		Player currentPlayer = scrabbleSystem.getCurrentPlayer();
		currentPlayerLabel.setText("Current player: " + currentPlayer.getName() + " ( score: "
				+ String.valueOf(currentPlayer.getScore()) + " )");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		JButton buySpecialTile = new JButton("buy-a-SpecialTile");
		buySpecialTile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] specialTiles = { "Boom [price: 20]", "NegativePoint [price: 30]", "RetrieveOrder [price: 20]",
						"ReverseOrder [price: 30]", "Skip-a-Turn [price: 20]" };
				String selectedSpecialTile = (String) JOptionPane.showInputDialog(null,
						"Select the kind of special tile you want to buy:\n", "Special Tile Store",
						JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), specialTiles, "Boom");
				System.out.println(selectedSpecialTile);
				if (selectedSpecialTile != null) {
					selectedSpecialTile = selectedSpecialTile.substring(0, selectedSpecialTile.indexOf("[")).trim();
				}

				Boolean buySpecialFlag = scrabbleSystem.buySpecialTile(selectedSpecialTile);
				if (buySpecialFlag) {
					updateInfoPanel();
					updateSpecialPanel(selectedSpecialTile);
					JOptionPane.showMessageDialog(null,
							"You successful buy a " + selectedSpecialTile + " special tile!");
				} else {
					JOptionPane.showMessageDialog(null, "Fail to buy the " + selectedSpecialTile
							+ " special tile, because you don't have enough points!");
				}

			}
		});

		JButton exchange = new JButton("exchange");
		exchange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		JButton pass = new JButton("pass");
		pass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scrabbleSystem.updateOrder();

			}
		});
		JButton submit = new JButton("submit");
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (move.getTileMap().size() != 0) {
					Boolean playMoveFlag = scrabbleSystem.playMove(move);
					System.out.println(move.getTileMap().size());
					if (!playMoveFlag) {
						updateBoardPanel();
						updateTileRack();
						move = new Move();
						JOptionPane.showMessageDialog(null, "The move is not valid! Please try again!");

					} else {
						if (move.getBoomSquareList().size() == 0) {
							int playerNum = scrabbleSystem.getPlayerNum() - 1;
							List<Player> challengePlayers = scrabbleSystem.getChallengePlayer();
							for (int i = 0; i < playerNum; i++) {
								Player player = challengePlayers.get(i);
								String name = player.getName();
								Object[] options = { "YES", "NO" };
								String challenge = "Hi " + name + ", do you want to challenge?";
								int answer = JOptionPane.showOptionDialog(null, challenge, "Challenge or Not",
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
										options[0]);
								System.out.println(answer);
								if (answer == 0) {
									scrabbleSystem.challenge(player);
									updateInfoPanel();
									updateBoardPanel();
									break;
								}
							}
							scrabbleSystem.updateOrder();
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "Please add at least one tile to the board!");
				}

			}
		});
		panel.add(buySpecialTile);
		panel.add(exchange);
		panel.add(pass);
		panel.add(submit);
		controlPanel.add(currentPlayerLabel);
		controlPanel.add(panel);
		controlPanel.setBorder(BorderFactory.createTitledBorder("Please choose your action:"));

	}

	private void updateBoardPanel() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				Square square = scrabbleSystem.getBoard().getSquare(j, BOARD_SIZE - 1 - i);
				if (square.hasTile()) {
					String text = String.valueOf(square.getTile().getLetter());
					squareButton[i][j].setText(text);
					squareButton[i][j].setEnabled(false);
				} else if (square.hasSpecialTile()) {
					SpecialTile specialTile = square.getSpecialTile();
					Player owner = specialTile.getOwner();
					Player currentPlayer = scrabbleSystem.getCurrentPlayer();
					if (owner.equals(currentPlayer)) {
						String text = specialTile.getName();
						squareButton[i][j].setText(text);
						squareButton[i][j].setEnabled(true);
					}
				} else if (square.hasTimer()) {
					String name = square.getTimer().getName();
					squareButton[i][j].setText(name);
					squareButton[i][j].setEnabled(true);
				} else {
					if (i == 7 && j == 7) {
						squareButton[7][7].setText("★");
					} else {
						squareButton[i][j].setText("");
					}
					squareButton[i][j].setEnabled(true);

				}
			}
		}

	}

	private void updateTileRack() {
		int size = disabledTileButton.size();
		for (int i = 0; i < size; i++) {
			Integer tmp = disabledTileButton.get(i);

			tileButton[tmp].setEnabled(true);
		}
	}

	private void updateInfoPanel() {
		List<Player> players = scrabbleSystem.getPlayers();
		int size = players.size();
		for (int i = 0; i < size; i++) {
			Player player = players.get(i);
			singlePlayerInfo[i].removeAll();
			JLabel nameLabel = new JLabel("player: " + player.getName());
			JLabel scoreLabel = new JLabel("score:" + String.valueOf(player.getScore()));
			singlePlayerInfo[i].add(nameLabel);
			singlePlayerInfo[i].add(scoreLabel);
			singlePlayerInfo[i].setVisible(true);

		}
		repaint();
	}

	private void updateControlPanel() {
		Player currentPlayer = scrabbleSystem.getCurrentPlayer();
		currentPlayerLabel.setText("Current player: " + currentPlayer.getName() + " ( score: "
				+ String.valueOf(currentPlayer.getScore()) + " )");
		updateInfoPanel();
	}

	private void updateSpecialPanel(String selectedSpecialTile) {
		if (selectedSpecialTile.equals("Boom")) {
			int boomNum = Integer.parseInt(specialTileButton[0].getText().replaceAll(".*[^\\d](?=(\\d+))", "")) + 1;
			specialTileButton[0] = new JButton("Boom X" + boomNum);
		} else if (selectedSpecialTile.equals("NegativePoint")) {
			int negativeNum = Integer.parseInt(specialTileButton[1].getText().replaceAll(".*[^\\d](?=(\\d+))", "")) + 1;
			specialTileButton[1] = new JButton("NegativePoint X" + String.valueOf(negativeNum));
		} else if (selectedSpecialTile.equals("RetrieveOrder")) {
			int retrieveNum = Integer.parseInt(specialTileButton[2].getText().replaceAll(".*[^\\d](?=(\\d+))", "")) + 1;

			specialTileButton[2] = new JButton("RetrieveOrder X" + String.valueOf(retrieveNum));
		} else if (selectedSpecialTile.equals("ReverseOrder")) {
			int reverseNum = Integer.parseInt(specialTileButton[3].getText().replaceAll(".*[^\\d](?=(\\d+))", "")) + 1;
			specialTileButton[3] = new JButton("ReverseOrder X" + String.valueOf(reverseNum));
		} else {
			int skipNum = Integer.parseInt(specialTileButton[4].getText().replaceAll(".*[^\\d](?=(\\d+))", "")) + 1;
			specialTileButton[4] = new JButton("Skip-a-Turn X" + String.valueOf(skipNum));
		}
	}

	private class squareListener implements ActionListener {
		private Square square;
		private int x;
		private int y;

		public squareListener(Square square, int x, int y) {
			this.square = square;
			this.x = x;
			this.y = y;

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			button.addMouseListener(new mouseDoubleClick(x, y));
			if (selectedTile != null) {
				button.setText(String.valueOf(selectedTile.getLetter()));
				button.setEnabled(false);
				move.addTile(square, selectedTile);
				selectedTileList.add(selectedTile);
				clearSelectedTile();
			}
		}

	}

	private class tileListener implements ActionListener {
		private Tile tile;
		private int index;

		public tileListener(Tile tile, int index) {
			this.tile = tile;
			this.index = index;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton jButton = (JButton) e.getSource();
			List<Tile> tiles = scrabbleSystem.getCurrentPlayer().getTileList();
			if (selectedTile != null) {
				int size = disabledTileButton.size();
				for (int i = 0; i < size; i++) {
					Integer tmp = disabledTileButton.get(i);
					if (tiles.get(tmp).equals(selectedTile)) {
						tileButton[tmp].setEnabled(true);
						disabledTileButton.remove(tmp);
						break;
					}
				}

			}
			jButton.setEnabled(false);
			disabledTileButton.add(index);
			selectedTile = tile;

		}

	}

	private class mouseDoubleClick extends MouseAdapter {

		private int x;
		private int y;

		public mouseDoubleClick(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				JButton tmpButton = (JButton) e.getSource();
				tmpButton.setEnabled(true);
				String buttonText = tmpButton.getText();
				removeSelectedTile(buttonText);
				String text = restoreSquareText(x, y);
				if (text != null) {
					tmpButton.setText(text);
				} else {
					tmpButton.setText("");
				}
				int size = disabledTileButton.size();
				for (int i = 0; i < size; i++) {
					Integer tmp = disabledTileButton.get(i);
					if (tileButton[tmp].getText().substring(0, 1).equals(buttonText)) {
						tileButton[tmp].setEnabled(true);
						disabledTileButton.remove(tmp);
						move.removeTile(scrabbleSystem.getBoard().getSquare(x, y));
						return;
					}
				}

			}
		}

	}

	private void clearSelectedTile() {
		selectedTile = null;
	}

	private void removeSelectedTile(String letter) {
		int size = selectedTileList.size();
		for (int i = 0; i < size; i++) {
			String tmp = String.valueOf(selectedTileList.get(i).getLetter());
			if (tmp.equals(letter)) {
				selectedTileList.remove(i);
				break;
			}
		}
	}

	private String restoreSquareText(int x, int y) {
		Square square = scrabbleSystem.getBoard().getSquare(x, y);
		String text;
		if (square.hasTimer()) {
			text = square.getTimer().getName();
		} else if (x == 7 && y == 7) {
			text = "★";
		} else {
			text = null;
		}

		return text;
	}

	@Override
	public void squareChanged() {
		updateBoardPanel();
	}

	@Override
	public void currentPlayerChange() {
		updateInfoPanel();
		updateControlPanel();
		tileRackPanel.removeAll();
		buildRackPanel();
		updateBoardPanel();
		move = new Move();
		selectedTileList = new ArrayList<>();

	}

	@Override
	public void gameEnded(Player winner) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scoreChanged() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tileRackChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void specialRackChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void specialSquareChange(int row, int col, SpecialTile specialTile) {
		// TODO Auto-generated method stub

	}

}
