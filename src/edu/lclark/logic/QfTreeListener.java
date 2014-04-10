package edu.lclark.logic;

import java.util.HashSet;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import edu.lclark.logic.QfWffParser.AtomContext;
import edu.lclark.logic.QfWffParser.BiconditionalContext;
import edu.lclark.logic.QfWffParser.ConditionalContext;
import edu.lclark.logic.QfWffParser.ConjunctionContext;
import edu.lclark.logic.QfWffParser.ConstantContext;
import edu.lclark.logic.QfWffParser.DisjunctionContext;
import edu.lclark.logic.QfWffParser.FormContext;
import edu.lclark.logic.QfWffParser.FormparenContext;
import edu.lclark.logic.QfWffParser.LettersContext;
import edu.lclark.logic.QfWffParser.NegationContext;
import edu.lclark.logic.QfWffParser.ParenthesesContext;
import edu.lclark.logic.QfWffParser.PredicateContext;
import edu.lclark.logic.QfWffParser.PrepositionContext;
import edu.lclark.logic.QfWffParser.QuantifierContext;
import edu.lclark.logic.QfWffParser.QuantparenContext;
import edu.lclark.logic.QfWffParser.VariableContext;

public class QfTreeListener implements QfWffListener {
	
	/** A place to leave error messages. */
	private StringBox box;
	
	/** Quantified variables seen so far. */
	private HashSet<String> quantifiedVariables;
	
	/** True when, while walking the tree, we are looking for a variable within a quantifier. */
	private boolean lookingForVariable;
	
	public QfTreeListener(StringBox box) {
		this.box = box;
		quantifiedVariables = new HashSet<String>();
		System.out.println("Creating a QfTreeListener");
	}
	
	@Override
	public void enterFormula(@NotNull QfWffParser.FormulaContext ctx) {
	}
	@Override
	public void exitFormula(@NotNull QfWffParser.FormulaContext ctx) {
	}
	@Override
	public void enterEveryRule(@NotNull ParserRuleContext ctx) {
	}
	@Override public void exitEveryRule(@NotNull ParserRuleContext ctx) { }
	@Override public void visitTerminal(@NotNull TerminalNode node) {
	}
	@Override public void visitErrorNode(@NotNull ErrorNode node) { }
	@Override
	public void enterParentheses(ParenthesesContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitParentheses(ParenthesesContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterFormparen(FormparenContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitFormparen(FormparenContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterAtom(AtomContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitAtom(AtomContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterConstant(ConstantContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitConstant(ConstantContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterForm(FormContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitForm(FormContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterDisjunction(DisjunctionContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitDisjunction(DisjunctionContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterPredicate(PredicateContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitPredicate(PredicateContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterConjunction(ConjunctionContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitConjunction(ConjunctionContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterBiconditional(BiconditionalContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitBiconditional(BiconditionalContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterPreposition(PrepositionContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitPreposition(PrepositionContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterQuantifier(QuantifierContext ctx) {
		System.out.println("Entering quantifier: " + ctx.getText());
		lookingForVariable = true;
	}
	@Override
	public void exitQuantifier(QuantifierContext ctx) {
//		System.out.println("Exiting quantifier: " + ctx.getText());
		lookingForVariable = false;
	}

	@Override
	public void enterNegation(NegationContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitNegation(NegationContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterQuantparen(QuantparenContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitQuantparen(QuantparenContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterConditional(ConditionalContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitConditional(ConditionalContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterVariable(VariableContext ctx) {
		if (lookingForVariable) {
			String variable = ctx.getText();
			System.out.println("Variable being quantified: " + variable);
			if (quantifiedVariables.contains(variable)) {
				box.setContents("Redundant quantification: " + variable);
			} else {
				quantifiedVariables.add(variable);
			}
		}		
	}
	@Override
	public void exitVariable(VariableContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterLetters(LettersContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitLetters(LettersContext ctx) {
		// TODO Auto-generated method stub
		
	}
}
