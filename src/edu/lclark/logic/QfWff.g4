grammar QfWff;

/*
 * PARSER RULES
 */

formula	: form EOF;
form : (quantifier form) | leftbiconditional | leftconditional | (LEFTPAREN form RIGHTPAREN);
quantifier: (FORALL | EXISTS) variable | LEFTPAREN quantifier RIGHTPAREN;

leftbiconditional : leftdisjunction (BICONDITIONAL disjunction)* | (LEFTPAREN biconditional RIGHTPAREN);
biconditional : disjunction (BICONDITIONAL disjunction)* ;

leftconditional : leftdisjunction (CONDITIONAL disjunction)* | (LEFTPAREN conditional RIGHTPAREN);
conditional : disjunction (CONDITIONAL disjunction)* ;

leftdisjunction : leftconjunction (INCLUSIVE_OR conjunction)* | (LEFTPAREN disjunction RIGHTPAREN);
disjunction : conjunction (INCLUSIVE_OR conjunction)* ;

leftconjunction : leftnegation (CONJUNCTION negation)* | (LEFTPAREN conjunction RIGHTPAREN);
conjunction : negation (CONJUNCTION negation)* ;

leftnegation : NEGATION? (predicate | atom | (quantifier form) | (LEFTPAREN form RIGHTPAREN) ) | (LEFTPAREN negation RIGHTPAREN);
negation : NEGATION? (form | predicate | atom);

predicate: preposition (variable | constant) + ;
atom : TRUTH | FALSITY | letters ;
letters : LETTERS('\'')? ;
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