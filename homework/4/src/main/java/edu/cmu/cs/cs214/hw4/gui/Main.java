package edu.cmu.cs.cs214.hw4.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * The Main class for GUI
 * 
 * @author raoliang
 *
 */
public class Main {
	/**
	 * The main method to invork the game GUI
	 * 
	 * @param args
	 *            the parament of main method
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// add frame and set its closing operation
			JFrame frame = new JFrame("Start a Scrabble Game");
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			frame.add(new StartGamePanel(frame));
			// display the JFrame
			frame.pack();
			frame.setResizable(true);
			frame.setVisible(true);
		});
	}

}