// Generated from src/edu/lclark/logic/Wff.g4 by ANTLR 4.2
package edu.lclark.logic;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link WffParser}.
 */
public interface WffListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link WffParser#parentheses}.
	 * @param ctx the parse tree
	 */
	void enterParentheses(@NotNull WffParser.ParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by {@link WffParser#parentheses}.
	 * @param ctx the parse tree
	 */
	void exitParentheses(@NotNull WffParser.ParenthesesContext ctx);

	/**
	 * Enter a parse tree produced by {@link WffParser#negation}.
	 * @param ctx the parse tree
	 */
	void enterNegation(@NotNull WffParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by {@link WffParser#negation}.
	 * @param ctx the parse tree
	 */
	void exitNegation(@NotNull WffParser.NegationContext ctx);

	/**
	 * Enter a parse tree produced by {@link WffParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull WffParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link WffParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull WffParser.AtomContext ctx);

	/**
	 * Enter a parse tree produced by {@link WffParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void enterDisjunction(@NotNull WffParser.DisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WffParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void exitDisjunction(@NotNull WffParser.DisjunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link WffParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(@NotNull WffParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WffParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(@NotNull WffParser.ConjunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link WffParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(@NotNull WffParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link WffParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(@NotNull WffParser.ConditionalContext ctx);

	/**
	 * Enter a parse tree produced by {@link WffParser#letters}.
	 * @param ctx the parse tree
	 */
	void enterLetters(@NotNull WffParser.LettersContext ctx);
	/**
	 * Exit a parse tree produced by {@link WffParser#letters}.
	 * @param ctx the parse tree
	 */
	void exitLetters(@NotNull WffParser.LettersContext ctx);

	/**
	 * Enter a parse tree produced by {@link WffParser#biconditional}.
	 * @param ctx the parse tree
	 */
	void enterBiconditional(@NotNull WffParser.BiconditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link WffParser#biconditional}.
	 * @param ctx the parse tree
	 */
	void exitBiconditional(@NotNull WffParser.BiconditionalContext ctx);

	/**
	 * Enter a parse tree produced by {@link WffParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull WffParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link WffParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull WffParser.FormulaContext ctx);
}