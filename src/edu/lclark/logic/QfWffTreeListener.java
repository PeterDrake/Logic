package edu.lclark.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import edu.lclark.logic.QfWffParser.*;

public class QfWffTreeListener extends QfWffBaseListener {
	
	/** A place to leave error messages. */
	private StringBox box;
	
	/** Quantified variables seen so far. */
	private HashSet<String> currentQuantifiedVariables;
	
	private ArrayList<Character> currentLeftNegations;
		
	public ArrayList<Character> getCurrentLeftNegations() {
		return currentLeftNegations;
	}

	/** True when, while walking the tree, we are looking for a variable within a quantifier. */
	private boolean lookingForVariable;
	
	/** A counter that keeps track of the amount of open pairs of parentheses */
	private int parenthesesCounter = 0;
	
	public QfWffTreeListener(StringBox box) {
		this.box = box;
		currentQuantifiedVariables = new HashSet<String>();
		currentLeftNegations = new ArrayList<Character>();
				
//		System.out.println("Creating a QfTreeListener");
	}

	@Override
	public void enterQuantifier(QuantifierContext ctx) {
//		System.out.println("Entering quantifier: " + ctx.getText());
		lookingForVariable = true;
	}
	
	@Override
	public void exitQuantifier(QuantifierContext ctx) {
//		System.out.println("Exiting quantifier: " + ctx.getText());
		lookingForVariable = false;
	}

	@Override
	public void enterVariable(VariableContext ctx) {
		if (lookingForVariable) {
			String variable = ctx.getText();
//			System.out.println("Variable being quantified: " + variable);
			currentQuantifiedVariables.add(variable);
			currentLeftNegations.add(variable.toCharArray()[0]);
		}	
//		System.out.println("Current Left Negations: " + currentLeftNegations);
	}
	
	@Override
	public void enterLeftnegation(LeftnegationContext ctx) {
//		System.out.println("Entering leftnegation: " + ctx.getText());
		currentLeftNegations.add('(');
//		System.out.println(currentLeftNegations);
	}
	
	@Override
	public void exitLeftnegation(LeftnegationContext ctx) {
//		System.out.println("Exiting leftnegation: " + ctx.getText());
		currentLeftNegations.add(')');
//		System.out.println(currentLeftNegations);
	}
	
	public boolean parenthesesNotMatched() {
//		System.out.println(parenthesesCounter);
		if (parenthesesCounter != 0) {
			box.setContents("Parentheses mismatched.");
			return true;
		} else {
			return false;
		}
	}

}
