package edu.lclark.logic;

import java.util.HashSet;
import java.util.Stack;

import edu.lclark.logic.QfWffParser.*;

public class QfWffTreeListener extends QfWffBaseListener {
	
	/** A place to leave error messages. */
	private StringBox box;
	
	/** Quantified variables seen so far. */
	private HashSet<String> currentQuantifiedVariables;
	
	private Stack<HashSet> formStack;
	
	/** True when, while walking the tree, we are looking for a variable within a quantifier. */
	private boolean lookingForVariable;
	
	/** A counter that keeps track of the amount of open pairs of parentheses */
	private int parenthesesCounter = 0;
	
	public QfWffTreeListener(StringBox box) {
		this.box = box;
		currentQuantifiedVariables = new HashSet<String>();
		
		formStack = new Stack<HashSet>();
		
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
			if (currentQuantifiedVariables.contains(variable)) {
				box.setContents("Redundant quantification: " + variable);
			} else {
				currentQuantifiedVariables.add(variable);
			}
		}		
	}
	
	@Override
	public void enterForm(FormContext ctx) {
		formStack.push(currentQuantifiedVariables);
	}
	
	@Override
	public void exitForm(FormContext ctx) {
		formStack.pop();
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
