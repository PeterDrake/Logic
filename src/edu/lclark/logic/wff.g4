grammar wff;
	   
prog: formula EOF;

formula: leftparen formula rightparen
	| not formula
	| letters
	| truth
	| falsity
	| formula connectives formula
	;

letters: 'p'
	   | 'q'
	   | 'r'
	   | 's'
	   | 't'
	   ;
	   
leftparen: '(' ;
rightparen: ')' ;
	  
connectives: and
		   | inclusive_or
		   | conditional
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