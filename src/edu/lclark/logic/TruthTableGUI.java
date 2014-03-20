package edu.lclark.logic;

import java.awt.EventQueue;
import javax.swing.JFrame;

/** A GUI that displays a truth table */
public class TruthTableGUI extends JFrame {
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 693;

	public TruthTableGUI() {
		add(new TruthTablePanel("p.qvr"));
		pack();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				TruthTableGUI gui = new TruthTableGUI();
				gui.setTitle("Truth Table");
				gui.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gui.setVisible(true);
			}
		});
	}
}