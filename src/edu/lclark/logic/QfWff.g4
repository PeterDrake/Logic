grammar QfWff;

/*
 * PARSER RULES
 */

//formula : formparen EOF;
formula	: (formparen | (biconditional | conditional)) (connective (biconditional | conditional | formparen))* EOF;
formparen : NEGATION* LEFTPAREN formparen RIGHTPAREN | form ;
form : quantparen* ((biconditional | conditional) | (LEFTPAREN form+ RIGHTPAREN));
quantparen : NEGATION* LEFTPAREN quantparen RIGHTPAREN | quantifier ;
quantifier: (FORALL | EXISTS) variable ;

biconditional : disjunction (BICONDITIONAL disjunction)* ;
conditional : disjunction CONDITIONAL disjunction ;
disjunction : conjunction (INCLUSIVE_OR conjunction)* ;
conjunction : negation (CONJUNCTION negation)* ;
negation : NEGATION? parentheses ;
//parentheses : LEFTPAREN (biconditional | conditional) RIGHTPAREN | predicate;
parentheses : LEFTPAREN (biconditional | conditional) RIGHTPAREN | (predicate | atom);
predicate: preposition variable* ;
atom : TRUTH | FALSITY | letters ;
letters : LETTERS('\'')? ;
variable : VARIABLES('\'')? ;
constant : CONSTANTS('\'')? ;
preposition : PREPOSITIONS('\'')? ;
connective : BICONDITIONAL | CONDITIONAL | CONJUNCTION | INCLUSIVE_OR;

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
