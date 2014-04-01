grammar QfWff;

/*
 * PARSER RULES
 */

formula	: (LEFTPAREN (FORALL | EXISTS) variable RIGHTPAREN)* (biconditional | conditional) EOF;
biconditional : disjunction ( BICONDITIONAL disjunction)* ;
conditional : disjunction CONDITIONAL disjunction ;
disjunction : conjunction (INCLUSIVE_OR conjunction)* ;
conjunction : negation (CONJUNCTION negation)* ;
negation : NEGATION? parentheses ;
parentheses : LEFTPAREN ( biconditional | conditional ) RIGHTPAREN | predicate ;
predicate : preposition predicateTuple ;
predicateTuple : term (',' term)* ;
//term : function | variable ;
term : variable* ;

//function : constant functionTuple | constant ;
//functionTuple : (constant | variable)*;

variable : VARIABLES('\'')? ;
constant : CONSTANTS('\'')? ;
preposition : PREPOSITIONS('\'')? ;

/*
 * LEXER RULES
 */

FORALL: 'V' | '∀' ;
EXISTS: '#' | '∃' | '∃' ;
	   
LEFTPAREN: '(' | '[' ;
RIGHTPAREN: ')' | ']' ;
	  
BICONDITIONAL: '↔' | '<->' | '≡';

CONDITIONAL: '→' | '->' | '⊃';
		   
CONJUNCTION: '.' | '&' | '^' ;

INCLUSIVE_OR: 'v' | '⋁' ;

NEGATION: '-' | '¬' | '~' ;
		   
TRUTH: '⊤' | '1';
FALSITY: '⊥' | '0';

PREPOSITIONS: 'F' | 'G' | 'H' | 'I' | 'J' | 'K' ;

VARIABLES: 'x' | 'y' | 'z' | 'v' | 'w' ;
	   
CONSTANTS: 'a' | 'b' | 'c' | 'd' | 'e' ;
	   
SUBSCRIPTS: '₁' | '₂' | '₃' | '₄' | '₅' ;
	   
WS : (' ' | '\t' | '\r' | '\n') -> skip ;

ErrorChar : . ;