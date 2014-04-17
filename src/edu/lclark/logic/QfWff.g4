grammar QfWff;

/*
 * PARSER RULES
 */

formula	: (formparen | (biconditional | conditional)) EOF;
formparen : NEGATION* leftparen formparen rightparen | form ;
form : quantparen* (biconditional | conditional) ;
quantparen : NEGATION* leftparen quantparen rightparen | quantifier ;
quantifier: (FORALL | EXISTS) variable ;

biconditional : disjunction (BICONDITIONAL disjunction)* ;
conditional : disjunction CONDITIONAL disjunction ;
disjunction : conjunction (INCLUSIVE_OR conjunction)* ;
conjunction : negation (CONJUNCTION negation)* ;
negation : NEGATION? parentheses ;
//parentheses : leftparen (biconditional | conditional) rightparen | predicate;
parentheses : leftparen (biconditional | conditional) rightparen | (predicate | atom);
predicate: preposition variable* ;
atom : TRUTH | FALSITY | letters ;
letters : LETTERS('\'')? ;
variable : VARIABLES('\'')? ;
constant : CONSTANTS('\'')? ;
preposition : PREPOSITIONS('\'')? ;

leftparen : LEFTPAREN ;
rightparen : RIGHTPAREN ;

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

LETTERS: 'p' | 'q' | 'r' | 's' | 't' 
	   | 'P' | 'Q' | 'R' | 'S' | 'T'
	   | 'A' | 'B' | 'C' | 'D' | 'E'
	   ;

PREPOSITIONS: 'F' | 'G' | 'H' | 'I' | 'J' | 'K' ;

VARIABLES: 'x' | 'y' | 'z' | 'v' | 'w' ;
	   
CONSTANTS: 'a' | 'b' | 'c' | 'd' | 'e' ;
	   
SUBSCRIPTS: '₁' | '₂' | '₃' | '₄' | '₅' ;
	   
WS : (' ' | '\t' | '\r' | '\n') -> skip ;

ErrorChar : . ;