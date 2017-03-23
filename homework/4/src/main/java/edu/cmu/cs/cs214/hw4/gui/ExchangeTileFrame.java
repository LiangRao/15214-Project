package edu.cmu.cs.cs214.hw4.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.cmu.cs.cs214.hw4.core.ScrabbleSystem;
import edu.cmu.cs.cs214.hw4.core.Tile;

/**
 * Invork the frame while clicks on the "exchange" button in the GameGui
 * 
 * @author raoliang
 *
 */
public class ExchangeTileFrame implements ItemListener {
	private JFrame frame = new JFrame("Exchange Tiles");
	private Container cont = frame.getContentPane();
	private JCheckBox[] tileCheckBox;
	private JButton confirm = new JButton("Confirm");
	private JButton cancel = new JButton("Cancel");
	private JPanel pan = new JPanel();
	private JPanel controlPanel = new JPanel();
	private static final int TILE_NUM = 7;

	private boolean[] selected;

	/**
	 * A constructor
	 * 
	 * @param scrabbleSystem
	 *            the game core class
	 */
	public ExchangeTileFrame(ScrabbleSystem scrabbleSystem) {
		tileCheckBox = new JCheckBox[TILE_NUM];
		pan.setBorder(BorderFactory.createTitledBorder("select all the tiles"));
		pan.setLayout(new GridLayout(1, TILE_NUM));
		for (int i = 0; i < TILE_NUM; i++) {
			int j = i + 1;
			tileCheckBox[i] = new JCheckBox("Tile " + j);
			tileCheckBox[i].addItemListener(this);
			pan.add(this.tileCheckBox[i]);
		}
		selected = new boolean[TILE_NUM];
		for (int i = 0; i < TILE_NUM; i++) {
			selected[i] = false;
		}

		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<Tile> tiles = scrabbleSystem.getCurrentPlayer().getTileList();
				List<Tile> resultList = new ArrayList<>();
				for (int i = 0; i < TILE_NUM; i++) {
					if (selected[i]) {
						resultList.add(tiles.get(i));
					}
				}
				scrabbleSystem.exchangeTile(resultList);
				frame.dispose();
				frame = null;
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame = null;

			}
		});
		controlPanel.setLayout(new GridLayout(1, 2));
		controlPanel.add(confirm);
		controlPanel.add(cancel);
		cont.setLayout(new GridLayout(2, 1));
		cont.add(pan);
		cont.add(controlPanel);
		frame.setSize(500, 100);
		frame.setVisible(true);
		frame.setResizable(true);

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		for (int i = 0; i < TILE_NUM; i++) {
			if (tileCheckBox[i].isSelected()) {
				selected[i] = true;
			}
			if (!tileCheckBox[i].isSelected()) {
				selected[i] = false;
			}
		}
	}

}
