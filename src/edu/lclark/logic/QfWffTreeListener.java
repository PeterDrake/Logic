package edu.lclark.logic;

import java.util.ArrayList;
import edu.lclark.logic.QfWffParser.*;

public class QfWffTreeListener extends QfWffBaseListener {
	
	private ArrayList<Character> currentLeftNegations;
		
	public ArrayList<Character> getCurrentLeftNegations() {
		return currentLeftNegations;
	}

	/** True when, while walking the tree, we are looking for a variable within a quantifier. */
	private boolean lookingForVariable;
	
	public QfWffTreeListener() {
		currentLeftNegations = new ArrayList<Character>();
	}

	@Override
	public void enterQuantifier(QuantifierContext ctx) {
		lookingForVariable = true;
	}
	
	@Override
	public void exitQuantifier(QuantifierContext ctx) {
		lookingForVariable = false;
	}

	@Override
	public void enterVariable(VariableContext ctx) {
		if (lookingForVariable) {
			String variable = ctx.getText();
			currentLeftNegations.add(variable.toCharArray()[0]);
		}
	}
	
	@Override
	public void enterLeftnegation(LeftnegationContext ctx) {
		currentLeftNegations.add('(');
	}
	
	@Override
	public void exitLeftnegation(LeftnegationContext ctx) {
		currentLeftNegations.add(')');
	}

}
