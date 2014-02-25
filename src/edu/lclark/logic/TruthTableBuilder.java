package edu.lclark.logic;

public class TruthTableBuilder implements View {

	String formula;
	char[] letters = new char[5];
	int numLetters;
	boolean[][] truthValues;

	/** Constructor for TurthTableBuilder, takes target formula */
	public TruthTableBuilder(String formula) {
		this.formula = formula;
		countLetters();
		initTable();
	}

	/** returns number of letters in target formula */
	public int getNumLetters() {
		return numLetters;
	}
	
	public char getLetter(int i) {
	    return letters[i];
	}

	/** Scans formula for letters, counts them */
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

	/** Initializes the truth table */
	public void initTable() {
	    int numRows = 1 << numLetters; // 2 ^ numLetters
	    truthValues = new boolean[numRows][numLetters];
	    for (int col = 0; col < numLetters; col++) {
	        int numPartitions = 1 << (col + 1); // 2 ^ (col+1)
	        int partionLength = numRows / numPartitions;
	        for (int p = 0; p < numPartitions - 1; p += 2) {
	            int start = p * partionLength;
	            for (int row = 0; row < partionLength; row++) {
	                truthValues[row + start][col] = true;
	            }
	        }
	    }
	}

	public boolean getValue(int row, int column) {
		return truthValues[row][column];
	}

	public void addColumn() {

	}

	public void addTargetFormula() {

	}

	public void checkFragment() {

	}

	public String toString() {
	    String s = "";
	    for (int i = 0; i < numLetters; i++) {
	        s += letters[i];
	    }
	    s += "\n";
	    for (boolean[] row : truthValues) {
	        for (boolean val : row) {
	            s += val ? "1" : "0";
	        }
	        s += "\n";
	    }
	    return s;
	}
}
