grammar TfWff;

/*
 * PARSER RULES
 */

formula : ( biconditional | conditional ) EOF ;
biconditional : disjunction ( BICONDITIONAL disjunction)* ;
conditional : disjunction CONDITIONAL disjunction ;
disjunction : conjunction ( INCLUSIVE_OR conjunction )* ;
conjunction : negation ( CONJUNCTION negation )* ;
negation : NEGATION? parentheses ;
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
	   | 'A' | 'B' | 'C' | 'D' | 'E'
	   ;
	   
WS : (' ' | '\t' | '\r' | '\n') -> skip ;

ErrorChar : . ;