package edu.lclark.logic;

import java.math.*;

public class TruthTableChecker {

	private String formula;

	private boolean[] truthValues;
	private boolean[] matches;

	public TruthTableChecker(String formula, boolean[] truthValues) {
		this.formula = formula;
		this.truthValues = truthValues;
		matches = new boolean[truthValues.length];
	}

	public boolean[] compare(boolean[] enteredValues) {
		for (int i = 0; i < truthValues.length; i++) {
			if (enteredValues[i] == truthValues[i]) {
				matches[i] = true;
			} else {
				matches[i] = false;
			}
		}
		return matches;
	}

	public boolean[] conjunction(int numLetters, boolean[][] allPossibleValues,
			int letter1, int letter2) {
		boolean[] values = new boolean[(int) Math.pow(2, numLetters)];
		for (int i = 0; i < values.length; i++) {
			if (allPossibleValues[letter1][i] == true
					&& allPossibleValues[letter2][i] == true) {
				values[i] = true;
			}
		}

		return values;
	}

	public boolean[] disjunction(int numLetters, boolean[][] allPossibleValues,
			int letter1, int letter2) {
		boolean[] values = new boolean[(int) Math.pow(2, numLetters)];
		for (int i = 0; i < values.length; i++) {
			if (allPossibleValues[letter1][i] == true
					|| allPossibleValues[letter2][i] == true) {
				values[i] = true;
			}
		}

		return values;
	}

	public boolean[] conditional(int numLetters, boolean[][] allPossibleValues,
			int letter1, int letter2) {
		boolean[] values = new boolean[(int) Math.pow(2, numLetters)];
		for (int i = 0; i < values.length; i++) {
			if (allPossibleValues[letter1][i] == true
					&& allPossibleValues[letter2][i] == false) {
				values[i] = false;
			} else {
				values[i] = true;
			}
		}
		return values;

	}

	public boolean[] biconditional(int numLetters,
			boolean[][] allPossibleValues, int letter1, int letter2) {
		boolean[] values = new boolean[(int) Math.pow(2, numLetters)];
		for (int i = 0; i < values.length; i++) {
			if (allPossibleValues[letter1][i] == allPossibleValues[letter2][i]) {
				values[i] = true;
			} else {
				values[i] = false;
			}
		}
		return values;
	}

	public boolean[] evaluateFormula(String formula) {
		// TODO method stub
		return null;
	}
}
