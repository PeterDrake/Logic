package edu.lclark.logic;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

/** A JPanel that displays a truth-table */
public class TruthTablePanel extends JPanel implements View {
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

	public TruthTablePanel(String formula) {
		addColumnButton = new JButton("Add Column");
		addColumnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				addColumn(new TruthTableColumn("[new]", null));
			}
		});
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setVisible(true);
		truthTable = new TruthTable(formula);
		initTable();
		// add(addColumnButton);
		add(panel);
		addColumn(new TruthTableColumn(formula, null));
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
		for (int col = 0; col < numColumns; col++)
			addCell(new JLabel(truthTable.getColumn(col).getLabel()));
		for (int row = 0; row < numRows; row++)
			addRow(row);
	}

	/** Draws the ith row of the truth table. */
	private void addRow(int row) {
		int numColumns = truthTable.getNumColumns();
		int numLetters = truthTable.getNumLetters();
		for (int col = 0; col < numColumns; col++) {
			JComponent cell;
			if (col < numLetters) {
				// Add a JLabel:
				cell = new JLabel(truthTable.getColumn(col).getValue(row) ? "T"
						: "F");
			} else {
				// Add a JTextField:
				cell = new JTextField();
			}
			addCell(cell);
		}
	}

	/** Adds a JLabel to the end of the truth table. */
	private void addCell(JComponent cell) {
		cell.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
		cell.setOpaque(false);
		panel.add(cell);
	}

	/** Adds a column to the truth table, on the right */
	private void addColumn(TruthTableColumn column) {
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
		Graphics2D g2 = (Graphics2D) g;
		// g2.draw(new Line2D.Double(x1,y2, x2,y2);
	}
}