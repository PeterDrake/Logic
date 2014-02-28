package edu.lclark.logic;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import javax.swing.JLabel;

/** A JPanel that displays a truth-table */
public class TruthTablePanel extends JPanel implements View {
    /** Width, in pixels, of each 'cell' in the truth table grid */
    private static final int CELL_WIDTH = 50;

    /** Height, in pixels, of each 'cell' in the truth table grid */
    private static final int CELL_HEIGHT = 20;

    /** The underlying model for the truth table */
    private TruthTable truthTable;

    /** The number of columns added _after_ initialization */
    private int numColumnsAdded;

    /** The panel containing the grid of JLabels */
    private JPanel panel = new JPanel();

    public TruthTablePanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setVisible(true);
        // TODO: get letters from ButtonLayout input instead of hard-coded "pqr"
        truthTable = new TruthTable("pqr");
        initTable();
        add(panel);
        // TODO: remove the following line. It's for demonstration only.
        addColumn();
    }

    /** Initializes truth table by drawing every truth-value combination for the letters in the formula */
    private void initTable() {
        int numLetters = truthTable.getNumLetters();
        int numRows = truthTable.getNumRows();
        // Makes one extra row for the top 'label' row:
        panel.setLayout(new GridLayout(numRows + 1, numLetters));
        fillInTruthTable();
    }

    /** Fills in an empty truth table with already-known values.
     *  To be used when truth table is initialized, or when adding
     *  a column (since doing that erases and refills the table).
     */
    private void fillInTruthTable() {
        int numLetters = truthTable.getNumLetters();
        int numRows = truthTable.getNumRows();
        JLabel cell;
        // Adds labels, as first row of table:
        for (int col = 0; col < numLetters + numColumnsAdded; col++) {
            if (col >= numLetters) {
                // This is an added formula on the right-hand-side of the table
                // TODO: Put a formula here instead of the hard-coded string "[formula]"
                cell = new JLabel("[formula]");
            } else {
                /* This is on the left-hand-side of the table, part of
                 * the possible truth-value combinations for each letter
                 */
                cell = new JLabel("" + truthTable.getLetter(col));
            }
            cell.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
            cell.setOpaque(false);
            panel.add(cell);
        }
        // Adds truth-value rows to table:
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numLetters + numColumnsAdded; col++) {
                // Adds a cell (a JLabel) to the table at (row, col):
                if (col >= numLetters) {
                    // This is an added formula on the right-hand-side of the table
                    // TODO: Put a real truth-value here instead of the hard-coded string "-"
                    cell = new JLabel("-");
                } else {
                    /* This is on the left-hand-side of the table, part of
                     * the possible truth-value combinations for each letter
                     */
                    // TODO: Replace "T" and "F" with actual symbols
                    cell = new JLabel(truthTable.getValue(row, col) ? "T" : "F");
                }
                cell.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
                cell.setOpaque(false);
                panel.add(cell);
            }
        }
    }

    /** Adds a column to the truth table, on the right */
    private void addColumn() {
        int numLetters = truthTable.getNumLetters();
        int numRows = truthTable.getNumRows();
        // Replaces existing panel with new panel that has 1 extra added column:
        remove(panel);
        panel = new JPanel();
        numColumnsAdded++;
        panel.setLayout(new GridLayout(numRows + 1, numLetters + numColumnsAdded));
        fillInTruthTable();
        add(panel);
    }

    /* Useful for drawing lines. We're not using this now, but we
     * might use this later on if we need to draw lines in the future.*/
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
//        g2.draw(new Line2D.Double(x1,y2, x2,y2);
    }
}