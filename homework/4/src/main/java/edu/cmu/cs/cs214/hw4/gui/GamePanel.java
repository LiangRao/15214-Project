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
import java.util.Map;

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
	private List<Boolean> exchangeResultList;
	private GamePanel gamePanel;
	private SpecialTile selectedSpecialTile;
	// private MyCheckBox myCheckBox;

	private final static int BOARD_SIZE = 15;

	public GamePanel(ScrabbleSystem scrabbleSystem) {
		gamePanel = this;
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
		exchangeResultList = new ArrayList<>();
		selectedSpecialTile = null;
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
		tileRackPanel.setLayout(new GridLayout(7, 2));
		tileButton = new JButton[7];

		List<Tile> tiles = scrabbleSystem.getCurrentPlayer().getTileList();
		for (int i = 0; i < 7; i++) {
			tileButton[i] = new JButton();
			Tile tile = tiles.get(i);
			int tileNum = i + 1;
			tileButton[i]
					.setText(String.valueOf(tile.getLetter()) + "  [value =" + String.valueOf(tile.getValue()) + " ]");
			tileButton[i].addActionListener(new tileListener(tile, i));
			tileRackPanel.add(new JLabel("Tile " + tileNum + ":"));
			tileRackPanel.add(tileButton[i]);
		}
		tileRackPanel.setBorder(BorderFactory.createTitledBorder("Tile Rack"));
		repaint();

	}

	private void buildSpecialRackPanel() {
		specialRackPanel.setLayout(new GridLayout(5, 2));
		int boomNum = 0;
		int negativeNum = 0;
		int retrieveNum = 0;
		int reverseNum = 0;
		int skipNum = 0;

		specialTileButton = new JButton[5];
		specialTileButton[0] = new JButton("Boom X " + String.valueOf(boomNum));
		specialTileButton[0].setEnabled(false);
		specialTileButton[1] = new JButton("NegativePoint X " + String.valueOf(negativeNum));
		specialTileButton[1].setEnabled(false);
		specialTileButton[2] = new JButton("RetrieveOrder X " + String.valueOf(retrieveNum));
		specialTileButton[2].setEnabled(false);
		specialTileButton[3] = new JButton("ReverseOrder X " + String.valueOf(reverseNum));
		specialTileButton[3].setEnabled(false);
		specialTileButton[4] = new JButton("Skip-a-Turn X " + String.valueOf(skipNum));
		specialTileButton[4].setEnabled(false);

		specialRackPanel.add(specialTileButton[0]);
		specialRackPanel.add(specialTileButton[1]);
		specialRackPanel.add(specialTileButton[2]);
		specialRackPanel.add(specialTileButton[3]);
		specialRackPanel.add(specialTileButton[4]);
		specialRackPanel.setBorder(BorderFactory.createTitledBorder("Special Tile Rack"));
		repaint();

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
				if (selectedSpecialTile != null) {
					selectedSpecialTile = selectedSpecialTile.substring(0, selectedSpecialTile.indexOf("[")).trim();
				}

				Boolean buySpecialFlag = scrabbleSystem.buySpecialTile(selectedSpecialTile);
				if (buySpecialFlag) {
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
				new MyCheckBox(scrabbleSystem);

			}
		});
		JButton pass = new JButton("pass");
		pass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scrabbleSystem.pass(move);

			}
		});
		JButton submit = new JButton("submit");
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (move.getTileMap().size() != 0) {
					Boolean playMoveFlag = scrabbleSystem.playMove(move);
					if (!playMoveFlag) {
						updateBoardPanel();
						updateTileRackButton();
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
								if (answer == 0) {
									scrabbleSystem.challenge(player);
									updateInfoPanel();
									updateBoardPanel();
									break;
								}
							}
							scrabbleSystem.updateOrder();
						} else {
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

	private void updateTileRackButton() {
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
			nameLabel.setVisible(true);
			scoreLabel.setVisible(true);
			singlePlayerInfo[i].add(nameLabel);
			singlePlayerInfo[i].add(scoreLabel);
			singlePlayerInfo[i].repaint();
			singlePlayerInfo[i].getParent().repaint();
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

	private void rebuildSpecialRack() {
		Map<String, List<SpecialTile>> specialMap = scrabbleSystem.getCurrentPlayer().getSpecialTiles();

		int boomNum = specialMap.get("Boom").size();
		int negativeNum = specialMap.get("NegativePoint").size();
		int retrieveNum = specialMap.get("RetrieveOrder").size();
		int reverseNum = specialMap.get("ReverseOrder").size();
		int skipNum = specialMap.get("Skip-a-Turn").size();

		specialTileButton[0].setText("Boom X" + boomNum);
		specialTileButton[1].setText("NegativePoint X" + String.valueOf(negativeNum));
		specialTileButton[2].setText("RetrieveOrder X" + String.valueOf(retrieveNum));
		specialTileButton[3].setText("ReverseOrder X" + String.valueOf(reverseNum));
		specialTileButton[4].setText("Skip-a-Turn X" + String.valueOf(skipNum));

		if (selectedSpecialTile != null) {
			disableSpecialButton();
		} else {
			if (boomNum == 0) {
				specialTileButton[0].setEnabled(false);
			} else if (boomNum != 0) {
				specialTileButton[0].setEnabled(true);
				specialTileButton[0].addActionListener(new specialTileListener(specialMap.get("Boom").get(0)));
			}

			if (negativeNum == 0) {
				specialTileButton[1].setEnabled(false);
			} else if (negativeNum != 0) {
				specialTileButton[1].setEnabled(true);
				specialTileButton[1].addActionListener(new specialTileListener(specialMap.get("NegativePoint").get(0)));

			}

			if (retrieveNum == 0) {
				specialTileButton[2].setEnabled(false);
			} else if (retrieveNum != 0) {
				specialTileButton[2].setEnabled(true);
				specialTileButton[2].addActionListener(new specialTileListener(specialMap.get("RetrieveOrder").get(0)));

			}

			if (reverseNum == 0) {
				specialTileButton[3].setEnabled(false);
			} else if (reverseNum != 0) {
				specialTileButton[3].setEnabled(true);
				specialTileButton[3].addActionListener(new specialTileListener(specialMap.get("ReverseOrder").get(0)));

			}

			if (skipNum == 0) {
				specialTileButton[4].setEnabled(false);
			} else if (skipNum != 0) {
				specialTileButton[4].setEnabled(true);
				specialTileButton[4].addActionListener(new specialTileListener(specialMap.get("Skip-a-Turn").get(0)));
			}
		}

	}

	private void updateTileRack() {
		List<Tile> tiles = scrabbleSystem.getCurrentPlayer().getTileList();
		for (int i = 0; i < 7; i++) {
			Tile tile = tiles.get(i);
			tileButton[i]
					.setText(String.valueOf(tile.getLetter()) + "  [value =" + String.valueOf(tile.getValue()) + " ]");
		}
	}

	private void updateCurrentPlayerScore() {
		Player currentPlayer = scrabbleSystem.getCurrentPlayer();
		currentPlayerLabel.setText("Current player: " + currentPlayer.getName() + " ( score: "
				+ String.valueOf(currentPlayer.getScore()) + " )");
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
			} else if (selectedSpecialTile != null) {
				move.addSpecialTile(selectedSpecialTile, square);
				button.setText(selectedSpecialTile.getName());
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

	private class specialTileListener implements ActionListener {
		private SpecialTile specialTile;

		public specialTileListener(SpecialTile specialTile) {
			this.specialTile = specialTile;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			selectedSpecialTile = specialTile;
			rebuildSpecialRack();
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

	private void disableSpecialButton() {
		for (int i = 0; i < 5; i++) {
			specialTileButton[i].setEnabled(false);

		}

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
		rebuildSpecialRack();
		move = new Move();
		selectedTileList = new ArrayList<>();
		selectedSpecialTile = null;

	}

	@Override
	public void scoreChanged() {
		updateInfoPanel();

	}

	@Override
	public void specialRackChange() {
		rebuildSpecialRack();

	}

	@Override
	public void currentplayerScoreChange() {
		updateCurrentPlayerScore();

	}

	@Override
	public void tileRackChange() {
		updateTileRack();
	}

	@Override
	public void gameEnded(Player winner) {

	}
}
