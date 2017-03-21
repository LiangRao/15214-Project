package edu.cmu.cs.cs214.hw4.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ButtonTest extends JFrame {
	public static int k = 8;

	public ButtonTest() {
		Container co = getContentPane();
		JPanel p1 = new JPanel(new GridLayout(k, k, 1, 1));
		JButton jb[] = new JButton[k * k];
		int r, g, b;
		for (int i = 0; i < (k * k); i++) {
			r = (int) (Math.random() * 100);
			g = (int) (Math.random() * 100);
			b = (int) (Math.random() * 100);
			jb[i] = new JButton();
			jb[i].setBackground(new Color(r, g, b));
			p1.add(jb[i]);
		}
		co.add(p1);
		setSize(300, 300);
		setResizable(false);
		setVisible(true);
		setTitle("颜色设置");
		setBackground(Color.red);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ButtonTest();

	}
}