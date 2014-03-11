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

prog : ( biconditional | conditional ) EOF ;
//biconditional : conditional ( BICONDITIONAL conditional )* ;
//conditional : disjunction ( CONDITIONAL disjunction )* ;
biconditional : disjunction ( BICONDITIONAL disjunction)* ;
conditional : disjunction CONDITIONAL disjunction ;
disjunction : conjunction ( INCLUSIVE_OR conjunction )* ;
conjunction : negation ( AND negation )* ;
negation : NOT parentheses | parentheses ;
parentheses : LEFTPAREN ( biconditional | conditional ) RIGHTPAREN | atom ;
atom : TRUTH | FALSITY | letters ;
letters : LETTERS('\'')? ;

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

ErrorChar : . ;