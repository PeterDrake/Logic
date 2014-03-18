package edu.lclark.logic;

import java.math.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

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

	/** Takes column with user-entered values and returns which ones are correct */
	public boolean[] compareValues(TruthTableColumn column) {
		boolean[] calculatedValues = evaluateFormula(column.getLabel());
		boolean[] results = new boolean[column.getNumRows()];
		for (int i = 0; i < column.getNumRows(); i++) {
			results[i] = column.getValue(i) == calculatedValues[i];
		}
		return results;
	}

	/** Returns true if index of String is inside parentheses */
	public boolean insideParentheses(String formula, int index) {
		int parenthesesLevel = 0;
		for (int i = 0; i < formula.length(); i++) {
			if (formula.charAt(i) == '(') {
				parenthesesLevel++;
			} else if (formula.charAt(i) == ')') {
				parenthesesLevel--;
			}
			if (index == i) {
				return (parenthesesLevel != 0);
			}
		}
		return false;
	}

	/** Returns the number of operators */
	public int numOperators(String formula) {
		formula = formula.replaceAll("\\s+", "");
		int count = 0;
		String[] ops = { "↔", "<->", "→", "[^<]->", "\\.", "&", "^", "v", "⋁",
				"[^<]\\-[^>]", "¬", "~" };
		for (String op : ops) {
			String[] formula2 = formula.split(op);
			int sum = 0;
			if (formula2.length > 1) {
				for (int i = 0; i < formula2.length - 1; i++) {
					String cleanOp = op.replaceAll("\\\\", "");
					cleanOp = cleanOp.replaceAll("\\[\\^>\\]", "");
					cleanOp = cleanOp.replaceAll("\\[\\^<\\]", "");
					sum += formula2[i].length() - i + cleanOp.length() - 1;
					if (!insideParentheses(formula.replaceAll("\\\\", ""), sum)) {
						count++;
					}
				}
			}
		}
		return count;
	}

	public boolean isOperator(String c) {
		String[] ops = { "↔", "-", "→", "<->", ".", "&", "^", "v", "⋁", "->",
				"¬", "~" };
		for (String op : ops) {
			if (op.equals(c)) {
				return true;
			}
		}
		return false;
	}

	/** Returns all operators outside of parentheses */
	public ArrayList<String> getTopLevelOperators(String formula) {
		int parenthesesLevel = 0;
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < formula.length(); i++) {
			String c = formula.charAt(i) + "";
			if (c.equals("(")) {
				parenthesesLevel++;
			} else if (c.equals(")")) {
				parenthesesLevel--;
			} else if (parenthesesLevel == 0) {
				if (i < formula.length() - 2
						&& isOperator(formula.substring(i, i + 3))) {
					result.add(formula.substring(i, i + 3));
					i += 2;
				} else if (i < formula.length() - 1
						&& isOperator(formula.substring(i, i + 2))) {
					result.add(formula.substring(i, i + 2));
					i++;
				} else if (isOperator(c)) {
					result.add("" + c);

				}
			}
		}
		return result;
	}

	// TODO Recursion, accept more than one top level operator
	public boolean[] evaluateFormula(String formula) {
		boolean[] calculatedValues = new boolean[truthValues.length];
		formula = formula.replaceAll(" ", "");
		ArrayList<String> ops = getTopLevelOperators(formula);
		for (String op : ops) {
			op = Pattern.quote(op);
			String[] subFormula = formula.split(op);
			System.out.println(subFormula[0]);
			System.out.println(subFormula[1]);
		}
		if (numOperators(formula) == 0) {
			return getColumnCalculatedValues(formula.charAt(0));
		} else if (numOperators(formula) == 1) {
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
