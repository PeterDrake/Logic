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

	public void initTable() {
		int size = (int) Math.pow(2, numLetters);
		truthValues = new boolean[size][numLetters];
		for (int i = 0; i < numLetters; i++)
			popColumn(i, size, (int) Math.pow(2, i + 1));
	}

	/** Takes column and size of partitions, distributes truths */
	private void popColumn(int c, int size, int numPartitions) {
		for (int p = 0; p < numPartitions; p += 2)
			for (int i = 0; i < size; i++)
				truthValues[i][c] = true;
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

}
