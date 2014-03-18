grammar Qf;

/*
 * PARSER RULES
 */

formula : EOF ;

//formula : ( biconditional | conditional ) EOF ;
//biconditional : disjunction ( BICONDITIONAL disjunction)* ;
//conditional : disjunction CONDITIONAL disjunction ;
//disjunction : conjunction ( INCLUSIVE_OR conjunction )* ;
//conjunction : negation ( CONJUNCTION negation )* ;
//negation : NEGATION parentheses | parentheses ;
//parentheses : LEFTPAREN ( biconditional | conditional ) RIGHTPAREN | atom ;
//atom : TRUTH | FALSITY | letters ;
//letters : LETTERS('\'')? ;

/*
 * LEXER RULES
 */

FORALL: 'V' | '∀' ;
EXISTS: '#' | '∃' ;
	   
LEFTPAREN: '(' | '[' ;
RIGHTPAREN: ')' | ']' ;
	  
BICONDITIONAL: '↔' | '<->' | '≡';

CONDITIONAL: '→' | '->' | '⊃';
		   
CONJUNCTION: '.' | '&' | '^' ;

INCLUSIVE_OR: 'v' | '⋁' ;

NEGATION: '-' | '¬' | '~' ;
		   
TRUTH: '⊤' | '1';
FALSITY: '⊥' | '0';

PREDICATELETTERS: 'F' | 'G' | 'H'
				| 'I' | 'J' | 'K' ;

LETTERS: 'p' | 'q' | 'r' | 's' | 't' 
	   | 'P' | 'Q' | 'R' | 'S' | 'T'
	   | 'a' | 'b' | 'c' | 'd' | 'e'
	   | 'A' | 'B' | 'C' | 'D' | 'E'
	   | 'x' | 'y' | 'z' | 'v' | 'w'
	   ;
	   
SUBSCRIPTS: '₁' | '₂' | '₃' | '₄' | '₅' ;
	   
WS : (' ' | '\t' | '\r' | '\n') -> skip ;

ErrorChar : . ;