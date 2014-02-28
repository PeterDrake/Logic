package edu.lclark.logic;

/** A truth table for a well-formed formula */
public class TruthTable implements View {
    /** The well-formed formula this truth table represents */
	final String formula;

	/** The letters found in the formula */
	final char[] letters = new char[5];

	/** The number of letters in the formula */
	int numLetters;

	/** The set of all possible combinations of truth-values for each letter in the formula */
	boolean[][] truthValues;

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

	/** Returns the number of rows in the table: 2 ^ numLetters */
	public int getNumRows() {
	    return 1 << numLetters; // same as: (int) Math.pow(2, numLetters)
	}

	/** Returns how many letters are in target formula */
	private void countLetters() {
		if (formula.indexOf('p') >= 0)
			letters[numLetters++] = 'p';
		if (formula.indexOf('q') >= 0)
			letters[numLetters++] = 'q';
		if (formula.indexOf('r') >= 0)
			letters[numLetters++] = 'r';
		if (formula.indexOf('s') >= 0)
			letters[numLetters++] = 's';
		if (formula.indexOf('t') >= 0)
			letters[numLetters++] = 't';
	}

	/** Initializes truth-table with each truth-value combination of the letters */
	public void initTable() {
	    int numRows = getNumRows();
	    truthValues = new boolean[numRows][numLetters];
	    // For each column...
	    for (int col = 0; col < numLetters; col++) {
	        int numPartitions = 1 << (col + 1); // same as: (int) Math.pow(2, col+1)
	        int partionLength = numRows / numPartitions;
	        // ...for each partition in the column...
	        for (int p = 0; p < numPartitions - 1; p += 2) {
	            int start = p * partionLength;
	            // ...for each row in that partition:
	            for (int row = 0; row < partionLength; row++) {
	                truthValues[row + start][col] = true;
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

	public String toString() {
	    String s = "";
	    // Add each letter on first line, as a label...
	    for (int i = 0; i < numLetters; i++) {
	        s += letters[i];
	    }
	    s += "\n";
	    // ...then beneath each label, add each truth-value
	    // as a 0 or 1, based on truthValues:
	    for (boolean[] row : truthValues) {
	        for (boolean val : row) {
	            s += val ? "1" : "0";
	        }
	        s += "\n";
	    }
	    return s;
	}
}