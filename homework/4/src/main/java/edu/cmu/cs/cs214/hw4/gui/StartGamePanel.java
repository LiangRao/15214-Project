package edu.cmu.cs.cs214.hw4.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.ScrabbleSystem;

public class StartGamePanel extends JPanel {
	private JFrame mainFrame;
	private List<Player> playersList;
	private final static int MAX_PLAYER = 4;
	private final static int TEXT_LENGTH = 20;
	private final static String CURRENT_PLAYER = "Player number:";
	private final static String NAME_SPACE = "Input your name here:";

	private final JLabel welcomeLabel = new JLabel("Welcome to scrabble!");

	/**
	 * A constructor
	 */
	public StartGamePanel(JFrame frame) {
		mainFrame = frame;
		playersList = new ArrayList<Player>();
		JPanel addPlayerPanel = new JPanel();
		JLabel nameLabel = new JLabel("name:");
		JTextField playerNameText = new JTextField(TEXT_LENGTH);
		playerNameText.setText(NAME_SPACE);
		JButton addPlayerButton = new JButton("ADD");
		JButton startGameButton = new JButton("START GAME");
		JLabel currentRecord = new JLabel(CURRENT_PLAYER);

		addPlayerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = playerNameText.getText().trim();
				if (name.equals("")) {
					welcomeLabel.setText("The input name is empty!");
					playerNameText.setText(NAME_SPACE);
					return;
				}
				if (playersList.size() == MAX_PLAYER) {
					welcomeLabel.setText("The player number should not be larger than 4!");
					playerNameText.setText(NAME_SPACE);
					return;
				}
				Player player = new Player(name);
				playersList.add(player);
				welcomeLabel.setText("The player " + name + " is successfully added to the game!");
				currentRecord.setText(CURRENT_PLAYER + playersList.size());
				playerNameText.setText(NAME_SPACE);

			}
		});

		startGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (playersList.size() == 0) {
					welcomeLabel.setText("There is no player in the game!");
					return;
				}
				if (playersList.size() < 2) {
					welcomeLabel.setText("The number of players is not enough!");
					return;
				}
				runGame();
			}
		});
		addPlayerPanel.setLayout(new BorderLayout());
		addPlayerPanel.add(nameLabel, BorderLayout.BEFORE_FIRST_LINE);
		addPlayerPanel.add(playerNameText, BorderLayout.CENTER);
		addPlayerPanel.add(addPlayerButton, BorderLayout.EAST);
		addPlayerPanel.add(currentRecord, BorderLayout.SOUTH);

		setLayout(new BorderLayout());
		add(welcomeLabel, BorderLayout.NORTH);
		add(addPlayerPanel, BorderLayout.CENTER);
		add(startGameButton, BorderLayout.SOUTH);
		setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		setVisible(true);

	}

	private void runGame() {
		mainFrame.remove(this);
		ScrabbleSystem scrabbleSystem = new ScrabbleSystem();
		for (Player player : playersList) {
			scrabbleSystem.addPlayer(player);
			scrabbleSystem.startNewGame();
		}

		mainFrame.add(new GamePanel(scrabbleSystem));
		mainFrame.setTitle("Scrabble with Stuff");
		mainFrame.setVisible(true);
		mainFrame.setResizable(true);
		mainFrame.pack();

	}
}
