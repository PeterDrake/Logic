grammar wff;

/*
 * PARSER RULES
 */
	   
prog:  | formula (BICONDITIONAL formula)? EOF
	   | formula (CONDITIONAL formula)? EOF
	   | formula (operators formula)? EOF 
	   ;

formula: NOT formula
	   | LEFTPAREN formula RIGHTPAREN
	   | expr (BICONDITIONAL expr)?
	   | expr (CONDITIONAL expr)?
	   | expr (operators expr)?
	   ;
	   
expr: leftpartial expr
	| expr rightpartial
	| LEFTPAREN expr RIGHTPAREN
	| NOT expr
	| LETTERS
	;
	
leftpartial: (LETTERS operators)+ ;

rightpartial: (operators LETTERS)+ ;

operators: AND
		 | INCLUSIVE_OR
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

NOT: '-' | '¬' | '~' ;
		   
TRUTH: '⊤' | '1';
FALSITY: '⊥' | '0';

LETTERS: 'p' | 'q' | 'r' | 's' | 't' ;
	   
WS : (' ' | '\t' | '\r' | '\n') -> skip ;