grammar wff;
	   
prog: formula EOF ;

//formula: leftparen formula rightparen
//	| not formula
//	| letters
//	| truth
//	| falsity
//	| formula subconnectives formula
//	| formula connectives formula
//	;

formula: leftparen expr rightparen
	   | leftpartial expr
	   | expr rightpartial
	   | expr
	   | not expr
	  //| expr subconnectives expr

	   ;
	   
expr: letters
	| truth
	| falsity
	;
	
rightpartial: subconnectives letters;
leftpartial: letters subconnectives;


letters: 'p'
	   | 'q'
	   | 'r'
	   | 's'
	   | 't'
	   ;
	   
leftparen: '(' ;
rightparen: ')' ;
	  
subconnectives: and
		   | inclusive_or
		   ;
		   
connectives: conditional
		   | biconditional
		   ;
		   
biconditional: ('↔' | '<->');

conditional: ('→' | '->');
		   
and: ('.' | '&' | '^') ;

inclusive_or: ('v' | '⋁') ;

not: ('-' | '¬') ;
		   
truth: ('⊤' | 't' | '1');
falsity: ('⊥' | 'f' | '0');

WS : (' ' | '\t' | '\r' | '\n') -> skip ;