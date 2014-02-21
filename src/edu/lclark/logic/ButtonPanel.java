package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ButtonPanel extends JPanel {
/*
	Sentence letters: p, q, r, s, t, ′
	Connectives: -, ., ⋁, →, ↔
	Parentheses: (, ),  [, ]
	Truth-values: ⊤, ⊥
*/	
	
	public ButtonPanel() {
		//setLayout( new BorderLayout());
		setLayout( new GridLayout(4, 4));
		
		addButton("p", "sentence letter");
		addButton("q", "sentence letter");
		addButton("r", "sentence letter");
		addButton("s", "sentence letter");
		
		addButton("t", "sentence letter");
		addButton("-", "negation");
		addButton(".", "and");
		addButton("⋁", "or");
		
		addButton("→", "conditional");
		addButton("↔", "biconditional");
		addButton("(", "left parens");
		addButton(")", "right parens");
		
		addButton("[", "left bracket");
		addButton("]", "right bracket");
		addButton("⊤", "true");
		addButton("⊥", "false");
		
	}
	private void addButton(String label, String toolTip/*, ActionListener listener*/)
	   {
	      JButton button = new JButton(label);
	      button.setToolTipText(toolTip);
	     /* button.addActionListener(listener);*/
	      add(button);
	   }
}
