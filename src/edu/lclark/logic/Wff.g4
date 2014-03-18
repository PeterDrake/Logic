grammar Wff;

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

formula : ( biconditional | conditional ) EOF ;
biconditional : disjunction ( BICONDITIONAL disjunction)* ;
conditional : disjunction CONDITIONAL disjunction ;
disjunction : conjunction ( INCLUSIVE_OR conjunction )* ;
conjunction : negation ( CONJUNCTION negation )* ;
negation : NEGATION parentheses | parentheses ;
parentheses : LEFTPAREN ( biconditional | conditional ) RIGHTPAREN | atom ;
atom : TRUTH | FALSITY | letters ;
letters : LETTERS('\'')? ;

/*
 * LEXER RULES
 */
	   
LEFTPAREN: '(' | '[' ;
RIGHTPAREN: ')' | ']' ;
	  
BICONDITIONAL: '↔' | '<->' | '≡';

CONDITIONAL: '→' | '->' | '⊃';
		   
CONJUNCTION: '.' | '&' | '^' ;

INCLUSIVE_OR: 'v' | '⋁' ;

NEGATION: '-' | '¬' | '~' ;
		   
TRUTH: '⊤' | '1';
FALSITY: '⊥' | '0';

LETTERS: 'p' | 'q' | 'r' | 's' | 't' 
	   | 'P' | 'Q' | 'R' | 'S' | 'T'
	   | 'a' | 'b' | 'c' | 'd' | 'e'
	   | 'A' | 'B' | 'C' | 'D' | 'E'
	   ;
	   
WS : (' ' | '\t' | '\r' | '\n') -> skip ;

ErrorChar : . ;