grammar QfWff;
// (∀x)[Hx.(∃y)(Fy.Gxy).(∃y)(Iy.Gxy)->(∃y)((Fy v Iy).Gyx)]
/*
 * PARSER RULES
 */

formula	: form EOF;
form : leftbiconditional | leftconditional | (LEFTPAREN form RIGHTPAREN);
quantifier: (FORALL | EXISTS) variable | LEFTPAREN quantifier RIGHTPAREN;

leftbiconditional : leftdisjunction (BICONDITIONAL biconditional)* | (LEFTPAREN biconditional RIGHTPAREN);
biconditional : disjunction (BICONDITIONAL disjunction)* | leftbiconditional;

leftconditional : leftdisjunction (CONDITIONAL disjunction)* | (LEFTPAREN conditional RIGHTPAREN);
conditional : disjunction (CONDITIONAL disjunction)* | leftconditional;

leftdisjunction : leftconjunction (INCLUSIVE_OR disjunction)* | (LEFTPAREN disjunction RIGHTPAREN) | (quantifier leftdisjunction);
disjunction : conjunction (INCLUSIVE_OR conjunction)* | leftdisjunction;

leftconjunction : leftnegation (CONJUNCTION conjunction)* | (LEFTPAREN conjunction RIGHTPAREN) | (quantifier leftconjunction);
conjunction : negation (CONJUNCTION negation)* | leftconjunction;

leftnegation : NEGATION? (predicate | atom | (quantifier leftnegation) | (LEFTPAREN form RIGHTPAREN) ) | (LEFTPAREN negation RIGHTPAREN);
negation : NEGATION? (predicate | atom) | leftnegation;

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
	   | 'A' | 'B' | 'C' | 'D' | 'E' ;
	   
PREPOSITIONS: 'F' | 'G' | 'H' | 'I' | 'J' | 'K' ;
VARIABLES: 'x' | 'y' | 'z' | 'v' | 'w' ;
CONSTANTS: 'a' | 'b' | 'c' | 'd' | 'e' ;
SUBSCRIPTS: '₁' | '₂' | '₃' | '₄' | '₅' ;
WS : (' ' | '\t' | '\r' | '\n') -> skip ;
ErrorChar : . ;
