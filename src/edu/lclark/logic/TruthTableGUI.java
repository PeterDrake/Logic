package edu.lclark.logic;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class TruthTableGUI extends JPanel implements View {
    private TruthTable truthTableBuilder;
    private JPanel panel = new JPanel();

    private static final int CELL_WIDTH = 50;
    private static final int CELL_HEIGHT = 20;

    private static int numColumns;

    public TruthTableGUI() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setVisible(true);
        truthTableBuilder = new TruthTable("pqr");
        drawTruthTable();
        add(panel);
        addColumn();
    }

    private void drawTruthTable() {
        int numLetters = truthTableBuilder.getNumLetters();
        int numRows = truthTableBuilder.getNumRows();
        panel.setLayout(new GridLayout(numRows + 1, numLetters));
        fillInTruthTable();
    }

    private void fillInTruthTable() {
        int numLetters = truthTableBuilder.getNumLetters();
        int numRows = truthTableBuilder.getNumRows();
        for (int i = 0; i < numLetters + numColumns; i++) {
            if (i >= numLetters) panel.add(new JLabel("FEISO"));
            else panel.add(new JLabel("" + truthTableBuilder.getLetter(i)));
        }
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numLetters + numColumns; col++) {
                JLabel label;
                if (col >= numLetters) label = new JLabel("HI");
                else label = new JLabel(truthTableBuilder.getValue(row, col) ? "⊤" : "⊥");
                label.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
                label.setOpaque(false);
                panel.add(label);
            }
        }
    }

    private void addColumn() {
        numColumns++;
        int numLetters = truthTableBuilder.getNumLetters();
        int numRows = truthTableBuilder.getNumRows();
        remove(panel);
        panel = new JPanel();
        System.out.println(numLetters + numColumns);
        panel.setLayout(new GridLayout(numRows + 1, numLetters + numColumns));
        fillInTruthTable();
        add(panel);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int numLines = 1 + (1 << truthTableBuilder.getNumLetters());
        for (int i = 0; i < numLines; i++) {
            int y = i * CELL_HEIGHT;
            // g2.draw(new Line2D.Double(0, y, InputFrame.DEFAULT_WIDTH, y));
        }
    }
}
