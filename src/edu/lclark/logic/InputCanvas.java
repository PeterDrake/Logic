package edu.lclark.logic;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class InputCanvas extends JPanel implements View {
	private TruthTableBuilder truthTableBuilder;
	private JPanel panel = new JPanel();
	
	private static final int CELL_WIDTH = 50;
	private static final int CELL_HEIGHT = 20;

	public InputCanvas() {
	    setLayout(new FlowLayout(FlowLayout.LEFT));
		setVisible(true);
		truthTableBuilder = new TruthTableBuilder("pqr");
		drawTruthTable();
		add(panel);
	}

	private void drawTruthTable() {
	    int numLetters = truthTableBuilder.getNumLetters();
	    int numRows = 1 << numLetters; // 2 ^ numLetters
	    panel.setLayout(new GridLayout(numRows+1, numLetters));
	    for (int i = 0; i < numLetters; i++) {
	        panel.add(new JLabel("" + truthTableBuilder.getLetter(i)));
	    }
	    for (int row = 0; row < numRows; row++) {
	        for (int col = 0; col < numLetters; col++) {
	            JLabel label = new JLabel(truthTableBuilder.getValue(row, col) ? "⊤" : "⊥");
	            label.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
	            label.setOpaque(false);
	            panel.add(label);
	        }
	    }
	}

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int numLines = 1 + (1 << truthTableBuilder.getNumLetters());
        for (int i = 0; i < numLines; i++) {
            int y = i * CELL_HEIGHT;
            g2.draw(new Line2D.Double(0, y, InputFrame.DEFAULT_WIDTH, y));
        }
    }
}
