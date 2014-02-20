grammar wff;

options {
    language = Java;
}

formula: letters connectives letters
	   | formula connectives formula
	   | letters
	   ;

letters: 'p'
	   | 'q'
	   | 'r'
	   | 's'
	   | 't'
	   ;
	  
connectives: '-'
		   | '.'
		   | '⋁'
		   | '→'
		   | '↔'
		   ;
		   
truths: '⊤'
	  | '⊥'
	  ;

WS : [ \r\t\n]+ -> skip ;