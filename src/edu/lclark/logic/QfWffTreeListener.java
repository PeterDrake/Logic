package edu.lclark.logic;

import java.util.HashSet;
import edu.lclark.logic.QfWffParser.*;

public class QfWffTreeListener extends QfWffBaseListener {
	
	/** A place to leave error messages. */
	private StringBox box;
	
	/** Quantified variables seen so far. */
	private HashSet<String> quantifiedVariables;
	
	/** True when, while walking the tree, we are looking for a variable within a quantifier. */
	private boolean lookingForVariable;
	
	/** A counter that keeps track of the amount of open pairs of parentheses */
	private int parenthesesCounter = 0;
	
	public QfWffTreeListener(StringBox box) {
		this.box = box;
		quantifiedVariables = new HashSet<String>();
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
			if (quantifiedVariables.contains(variable)) {
				box.setContents("Redundant quantification: " + variable);
			} else {
				quantifiedVariables.add(variable);
			}
		}		
	}
	
//	@Override
//	public void enterLeftparen(QfWffParser.LeftparenContext ctx) {
//		parenthesesCounter++;
//		System.out.println(ctx.getText());
//		System.out.println(parenthesesCounter);
//	}
//
//	@Override
//	public void enterRightparen(QfWffParser.RightparenContext ctx) {
//		parenthesesCounter--;		
//		System.out.println(ctx.getText());
//		System.out.println(parenthesesCounter);
//	}
	
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
