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

/**
 * The panel to enroll all players to the game
 * 
 * @author raoliang
 *
 */
public class StartGamePanel extends JPanel {
	private JFrame mainFrame;
	private List<Player> playersList;
	private static final int MAX_PLAYER = 4;
	private static final int TEXT_LENGTH = 20;
	private static final String CURRENT_PLAYER = "Player number:";
	private static final String NAME_SPACE = "Input your name here:";
	private static final int PANEL_SIZE = 30;

	private final JLabel welcomeLabel = new JLabel("Welcome to scrabble!");

	/**
	 * A constructor
	 * 
	 * @param frame
	 *            the game GUI main fram
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
		setBorder(BorderFactory.createEmptyBorder(PANEL_SIZE, PANEL_SIZE, PANEL_SIZE, PANEL_SIZE));
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
