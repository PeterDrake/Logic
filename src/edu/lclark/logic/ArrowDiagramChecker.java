package edu.lclark.logic;

import java.util.ArrayList;

public class ArrowDiagramChecker {

	private String formula;

	private ArrayList<Arrow> arrows;

	private ArrayList<Integer> UD;

	public ArrowDiagramChecker(String formula) {
		this.formula = formula;
		UD = new ArrayList<Integer>();
		arrows = new ArrayList<Arrow>();
	}

	/** e.g UD = "UD =    {1,2,3}" */
	public void setUD(String UD) {

		for (int i = 1; i <= 4; i++) {
			if (UD.contains("" + i)) {
				this.UD.add(i);
			}
		}
	}

	public ArrayList<Integer> getUD() {
		return UD;
	}

	/** e.g ArrowFormula = "F = {(1,1) , (2,2) , (1,3)}". */
	public void setArrows(String arrowFormula) {
		if (arrowFormula.contains("(")) {
			int start = arrowFormula.charAt(arrowFormula.indexOf('(') + 1) - 48;
			int end = arrowFormula.charAt(arrowFormula.indexOf(')') - 1) - 48;

			arrows.add(new Arrow(start, end));
			setArrows(arrowFormula.substring(arrowFormula.indexOf(')') + 1));
		}
	}

	public ArrayList<Arrow> getArrows() {
		return arrows;
	}
}
