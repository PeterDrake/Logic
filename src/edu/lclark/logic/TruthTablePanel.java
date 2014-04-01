package edu.lclark.logic;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//import java.awt.Graphics2D;

/** A JPanel that displays a truth-table */

public class TruthTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/** Width, in pixels, of each 'cell' in the truth table grid */
	private static final int CELL_WIDTH = 50;

	/** Height, in pixels, of each 'cell' in the truth table grid */
	private static final int CELL_HEIGHT = 20;

	/** The underlying model for the truth table */
	private TruthTable truthTable;

	/** The panel containing the grid of truth-table columns */
	private JPanel panel = new JPanel();

	/** The button that adds a new column */
	private final JButton addColumnButton;
	
	/** The truth-table checker that checks column */
	private TruthTableChecker checker;
	
	/** The label for the target formula */
	private JLabel targetFormulaLabel;

    public TruthTablePanel(final ButtonPanel buttons) {
    	String formula = buttons.getText();
    	targetFormulaLabel = new JLabel("Target formula: " + formula);
        addColumnButton = new JButton("Add Column");
        addColumnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
               buttons.setVisible(true);
            }
        });
        setLayout(new FlowLayout(FlowLayout.LEFT));   
        truthTable = new TruthTable(formula);
        initTable();
        JPanel temp = new JPanel(new BorderLayout());
        temp.add(targetFormulaLabel, BorderLayout.NORTH);
        temp.add(addColumnButton, BorderLayout.SOUTH);
        add(temp);
        add(panel);
    }

	/**
	 * Initializes truth table by drawing every truth-value combination for the
	 * letters in the formula
	 */
	private void initTable() {
		int numLetters = truthTable.getNumLetters();
		int numRows = truthTable.getNumRows();
		// Makes one extra row for the top 'label' row:
		panel.setLayout(new GridLayout(numRows + 1, numLetters));
		fillInTruthTable();
	}

	/**
	 * Fills in an empty truth table with already-known values. To be used when
	 * truth table is initialized, or when adding a column (since doing that
	 * erases and refills the table).
	 */
	private void fillInTruthTable() {
		int numRows = truthTable.getNumRows();
		int numColumns = truthTable.getNumColumns();
		for (int col = 0; col < numColumns; col++) {
//			if (col == truthTable.getNumLetters()) continue;
			addCell(new JLabel(truthTable.getColumn(col).getLabel()));
		}
		for (int row = 0; row < numRows; row++)
			addRow(row);
	}

	/** Draws the ith row of the truth table. */
	private void addRow(int row) {
		int numColumns = truthTable.getNumColumns();
		int numLetters = truthTable.getNumLetters();
		for (int col = 0; col < numColumns; col++) {
//			final int column = col;
//			if (col == numLetters) continue;
			if (col < numLetters) {
				// Add a JLabel:
				addCell(new JLabel(truthTable.getColumn(col).getValue(row) ? "T" : "F"));
			} else {
				// Add a JButton:
				final JButton cell = new JButton("");
				TruthTableColumn column = truthTable.getColumn(col);
				boolean value = false;
				if (column != null) {
					value = column.getValue(row);
				}
				cell.addActionListener(new TruthTableButton(value, cell, row, col));
				addCell(cell);
			}
		}
	}

	/** Adds a JLabel to the end of the truth table. */
	private void addCell(JComponent cell) {
		cell.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
		cell.setOpaque(false);
		panel.add(cell);
	}

	/** Adds a column to the truth table, on the right */
	public void addColumn(TruthTableColumn column) {
		int numRows = truthTable.getNumRows();
		// Replaces existing panel with new panel that has 1 extra added column:
		remove(panel);
		panel = new JPanel();
		truthTable.addColumn(column);
		panel.setLayout(new GridLayout(numRows + 1, truthTable.getNumColumns()));
		fillInTruthTable();
		add(panel);
	}

	/*
	 * Useful for drawing lines. We're not using this now, but we might use this
	 * later on if we need to draw lines in the future.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Graphics2D g2 = (Graphics2D) g;
		// g2.draw(new Line2D.Double(x1,y2, x2,y2);
	}
	
	private class TruthTableButton extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		private int row;
		private int column;
		
		private boolean value;
		
		private JButton button;

		TruthTableButton(boolean value, JButton button, int row, int column) {
			this.value = value;
			this.button = button;
			this.row = row;
			this.column = column;
			updateText();
		}
		
		private void updateText() {
			button.setText(value ? "T" : "F");
		}
		
        @Override
        public void actionPerformed(ActionEvent event) {
        	value = !value;
        	updateText();
            truthTable.getColumn(column).setValue(row, value);
            TruthTableColumn col = truthTable.getColumn(column);
            String formula = col.getLabel();
            int numRows = truthTable.getNumRows();
            int numCols = truthTable.getNumColumns();
            boolean[][] values = new boolean[numRows][numCols];
            for (int r = 0; r < numRows; r++) {
            	for (int c = 0; c < numCols; c++) {
            		values[r][c] = col.getValue(r);
            	}
            }
            int numLetters = truthTable.getNumLetters();
            char[] letters = new char[numLetters];
            for (int i = 0; i < numLetters; i++) {
            	letters[i] = truthTable.getLetter(i);
            }
            boolean[] correct = new TruthTableChecker(truthTable.getTargetFormula(), values, letters).evaluateFormula(formula);
            if (!correct[row]) {
            	System.out.println(row + " WRONG");
            }
        }
	}
	
	public TruthTable getTruthTable() {
		return truthTable;
	}
}