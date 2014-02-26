grammar wff;

/*
 * PARSER RULES
 */
	   
prog: formula EOF ;

formula: LEFTPAREN expr RIGHTPAREN
	   | leftpartial expr
	   | expr rightpartial
	   | expr
	   | NOT expr
	  //| expr subconnectives expr

	   ;
	   
expr: LETTERS
	| TRUTH
	| FALSITY
	;
	
rightpartial: subconnectives LETTERS;
leftpartial: LETTERS subconnectives;
	   
subconnectives: AND
		   | INCLUSIVE_OR
		   ;
		   
connectives: CONDITIONAL
		   | BICONDITIONAL
		   ;

/*
 * LEXER RULES
 */

FORALL: 'V' | '∀' ;
EXISTS: 'E' | '∃' ;
	   
LEFTPAREN: '(' ;
RIGHTPAREN: ')' ;
	  
BICONDITIONAL: '↔' | '<->';

CONDITIONAL: '→' | '->';
		   
AND: '.' | '&' | '^' ;

INCLUSIVE_OR: 'v' | '⋁' ;

NOT: '-' | '¬' ;
		   
TRUTH: '⊤' | 't' | '1';
FALSITY: '⊥' | 'f' | '0';

LETTERS: 'p'
	   | 'q'
	   | 'r'
	   | 's'
	   | 't'
	   ;
	   
WS : ' ' | '\t' | '\r' | '\n' -> skip ;