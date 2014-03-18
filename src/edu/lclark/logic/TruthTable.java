package edu.lclark.logic;

import java.util.ArrayList;
import java.util.List;

/** A truth table model for a well-formed formula */
public class TruthTable implements Model {
	/** The well-formed formula this truth table represents */
	private final String formula;

	/** The letters found in the formula */
	private final char[] letters = new char[5];

	/** The number of letters in the formula */
	private int numLetters;

	/**
	 * The set of all possible combinations of truth-values for each letter in
	 * the formula
	 */
	private boolean[][] truthValues;
	private List<TruthTableColumn> columns;

	/** Constructor; takes target formula */
	public TruthTable(String formula) {
		this.formula = formula;
		countLetters();
		initTable();
	}

	/** Returns the number of letters in target formula */
	public int getNumLetters() {
		return numLetters;
	}

	/** Returns the ith letter used in target formula */
	public char getLetter(int i) {
		return letters[i];
	}

	/**
	 * Returns the number of rows (not counting the "label" row in the GUI) in
	 * the table: 2 ^ numLetters
	 */
	public int getNumRows() {
		return 1 << numLetters; // same as: (int) Math.pow(2, numLetters)
	}

	/** Returns the ith column in the truth table, as a TruthTableColumn. */
	public TruthTableColumn getColumn(int col) {
		return columns.get(col);
	}

	/** Returns the number of columns in the truth table. */
	public int getNumColumns() {
		return columns.size();
	}

	/** Adds a column to the right side of the truth table. */
	public void addColumn(TruthTableColumn column) {
		columns.add(column);
	}

	/**
	 * Updates letter and numLetters to reflect how many letters are in target
	 * formula
	 */
	private void countLetters() {
		for (char letter : "pqrst".toCharArray()) {
			if (formula.indexOf(letter) >= 0) {
				letters[numLetters++] = letter;
			}
		}
	}

	/** Initializes truth-table with each truth-value combination of the letters */
	private void initTable() {
		int numRows = getNumRows();
		truthValues = new boolean[numRows][numLetters];
		columns = new ArrayList<TruthTableColumn>(numLetters);
		// For each column...
		for (int col = 0; col < numLetters; col++) {
			int numPartitions = 1 << (col + 1); // same as: (int) Math.pow(2,
												// col+1)
			int partionLength = numRows / numPartitions;
			columns.add(new TruthTableColumn("" + letters[col],
					new boolean[numRows]));
			// ...for each partition in the column...
			for (int p = 0; p < numPartitions - 1; p += 2) {
				int start = p * partionLength;
				// ...for each row in that partition:
				for (int row = 0; row < partionLength; row++) {
					truthValues[row + start][col] = true;
					columns.get(col).setValue(row + start + 1, true);
				}
			}
		}
	}

	public boolean getValue(int row, int column) {
		return truthValues[row][column];
	}

	public void addTargetFormula() {
		// TODO
	}

	public void checkFragment() {
		// TODO
	}
}