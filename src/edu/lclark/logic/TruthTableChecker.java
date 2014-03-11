package edu.lclark.logic;

import java.math.*;

public class TruthTableChecker {

	private String formula;

	private boolean[][] truthValues;
	private char[] letters;

	public TruthTableChecker(String formula, boolean[][] truthValues,
			char[] letters) {
		this.formula = formula;
		this.truthValues = truthValues;
		this.letters = letters;
	}

	public boolean[] getColumnCalculatedValues(char letter) {
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == letter) {
				return truthValues[i];
			}
		}
		return null;
	}

	/** Returns index of first found mistake. Returns -1 if there are none. */
	public int compare(boolean[] set1, boolean[] set2) {
		for (int i = 0; i < set1.length; i++) {
			if (set1[i] != set2[i])
				return i;
		}
		return -1;
	}

	public boolean[] conjunction(boolean[] letter1Values,
			boolean[] letter2Values) {
		boolean[] values = new boolean[letter1Values.length];
		for (int i = 0; i < values.length; i++) {
			if (letter1Values[i] == true && letter2Values[i] == true) {
				values[i] = true;
			}
		}

		return values;
	}

	public boolean[] disjunction(boolean[] letter1Values,
			boolean[] letter2Values) {
		boolean[] values = new boolean[letter1Values.length];
		for (int i = 0; i < values.length; i++) {
			if (letter1Values[i] == true || letter2Values[i] == true) {
				values[i] = true;
			}
		}

		return values;
	}

	public boolean[] conditional(boolean[] letter1Values,
			boolean[] letter2Values) {
		boolean[] values = new boolean[letter1Values.length];
		for (int i = 0; i < values.length; i++) {
			if (letter1Values[i] == true && letter2Values[i] == false) {
				values[i] = false;
			} else {
				values[i] = true;
			}
		}
		return values;

	}

	public boolean[] biconditional(boolean[] letter1Values,
			boolean[] letter2Values) {
		boolean[] values = new boolean[letter1Values.length];
		for (int i = 0; i < values.length; i++) {
			if (letter1Values[i] == letter2Values[i]) {
				values[i] = true;
			} else {
				values[i] = false;
			}
		}
		return values;
	}

	public boolean[] compareValues(TruthTableColumn column) {
		boolean[] enteredValues = new boolean[column.getNumRows()];
		boolean[] calculatedValues = evaluateFormula(column.getLabel());
		for (int i = 0; i < column.getNumRows(); i++) {
			enteredValues[i] = column.getValue(i);
		}

		return conjunction(enteredValues, calculatedValues);
	}

	public int numOperators(String string) {
		int count = 0;
		for (int i = 0; i < string.length(); i++){
			char c = string.charAt(i);
			if (c == '.' || c == 'v' || c == '-' || c == '↔' || c == '→' || c == '⋁' || c == '&' || c == '^'){
				count++;
			}
		}
		return count;

	}

	public boolean[] evaluateFormula(String formula) {
		boolean[] calculatedValues = new boolean[truthValues.length];
		formula.replaceAll(" ", "");
		if (numOperators(formula) == 0) {
			return getColumnCalculatedValues(formula.charAt(0));
		}
		if (numOperators(formula) == 1) {
//			if ()
			if (formula.contains("→") || (formula.contains("->"))) {
				return conditional(
						getColumnCalculatedValues(formula.charAt(0)),
						getColumnCalculatedValues(formula.charAt(formula
								.length() - 1)));
			} else if (formula.charAt(1) == '↔' || formula.charAt(1) == '<') {
				return biconditional(
						getColumnCalculatedValues(formula.charAt(0)),
						getColumnCalculatedValues(formula.charAt(formula
								.length() - 1)));
			} else if (formula.charAt(1) == '⋁' || formula.charAt(1) == 'v') {
				return disjunction(
						getColumnCalculatedValues(formula.charAt(0)),
						getColumnCalculatedValues(formula.charAt(formula
								.length() - 1)));
			} else {
				return conjunction(
						getColumnCalculatedValues(formula.charAt(0)),
						getColumnCalculatedValues(formula.charAt(formula
								.length() - 1)));
			}

		}
		return null;
	}
}
