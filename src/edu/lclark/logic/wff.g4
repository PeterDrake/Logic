grammar wff;

/*
 * PARSER RULES
 */
	   
//prog:  | formula (BICONDITIONAL formula)? EOF
//	   | formula (CONDITIONAL formula)? EOF
//	   | formula (operators formula)? EOF 
//	   ;
//
//formula: LEFTPAREN formula RIGHTPAREN
//	   | NOT formula
//	   | expr (BICONDITIONAL expr)?
//	   | expr (CONDITIONAL expr)?
//	   | expr (operators expr)?
//	   ;
//	   
//expr: LEFTPAREN expr RIGHTPAREN
//	| (LETTERS operators)+ expr (operators LETTERS)?
//	| NOT expr
//	| LETTERS
//	;
//
//operators: AND
//		 | INCLUSIVE_OR
//		 ;

prog : equivalence EOF ;
equivalence : implication ( BICONDITIONAL implication )* ;
implication : disjunction ( CONDITIONAL disjunction )* ;
disjunction : conjunction ( INCLUSIVE_OR conjunction )* ;
conjunction : negation ( AND negation )* ;
negation : NOT parenthesis | parenthesis ;
parenthesis : LEFTPAREN equivalence RIGHTPAREN | atom ;
atom : TRUTH | FALSITY | LETTERS ;

/*
 * LEXER RULES
 */

FORALL: 'V' | '∀' ;
EXISTS: 'E' | '∃' ;
	   
LEFTPAREN: '(' | '[' ;
RIGHTPAREN: ')' | ']' ;
	  
BICONDITIONAL: '↔' | '<->';

CONDITIONAL: '→' | '->';
		   
AND: '.' | '&' | '^' ;

INCLUSIVE_OR: 'v' | '⋁' ;

NOT: '-' | '¬' | '~' ;
		   
TRUTH: '⊤' | '1';
FALSITY: '⊥' | '0';

LETTERS: 'p' | 'q' | 'r' | 's' | 't' ;
	   
WS : (' ' | '\t' | '\r' | '\n') -> skip ;