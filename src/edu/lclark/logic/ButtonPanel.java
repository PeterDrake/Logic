package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ButtonPanel extends JPanel {
	/*
	 * Sentence letters: p, q, r, s, t 
	 * Connectives: -, ., ⋁, →, ↔ 
	 * Parentheses: (, ), [, ] 
	 * Truth-values: ⊤, ⊥
	 * Other: ', ⨆
	 */
	
	/**
	 * The text field that buttons output to 
	 */
	private JTextField textField;

	public ButtonPanel(ActionListener submitAction) {
		setLayout(new BorderLayout());
		textField = new JTextField();
		textField.setEditable(true);
		add(textField, BorderLayout.NORTH);

		// A panel that all the buttons, but the submit button are added to
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());

		// Makes a panel with all the sentence letters
		JPanel sentenceLetters = new JPanel();
		sentenceLetters.setLayout(new GridLayout(5, 1));
		addButton("p", "sentence letter", sentenceLetters, new InsertAction("p"));
		addButton("q", "sentence letter", sentenceLetters, new InsertAction("q"));
		addButton("r", "sentence letter", sentenceLetters, new InsertAction("r"));
		addButton("s", "sentence letter", sentenceLetters, new InsertAction("s"));
		addButton("t", "sentence letter", sentenceLetters, new InsertAction("t"));
		buttons.add(sentenceLetters);

		// Makes a panel with all the connectives
		JPanel connectives = new JPanel();
		connectives.setLayout(new GridLayout(5, 1));
		addButton("-", "negation", connectives, new InsertAction("-"));
		addButton(".", "and", connectives, new InsertAction("."));
		addButton("⋁", "or", connectives, new InsertAction("⋁"));
		addButton("→", "conditional", connectives, new InsertAction("→"));
		addButton("↔", "biconditional", connectives, new InsertAction("↔"));
		buttons.add(connectives);

		// Creates a new panel with the remaining buttons
		JPanel misc = new JPanel();
		misc.setLayout(new GridLayout(5, 1));

		// Makes a panel with the prime and space buttons
		JPanel primenspace = new JPanel();
		primenspace.setLayout(new GridLayout(1, 2));
		addButton("'", "prime", primenspace, new InsertAction("'"));
		addButton("⨆", "space", primenspace, new InsertAction(" "));
		misc.add(primenspace);
		
		// Makes a panel with left and right parens
		JPanel parentheses = new JPanel();
		parentheses.setLayout(new GridLayout(1, 2));
		addButton("(", "left parens", parentheses, new InsertAction("("));
		addButton(")", "right parens", parentheses, new InsertAction(")"));
		misc.add(parentheses);

		// Makes a panel with different right and left parens
		parentheses = new JPanel();
		parentheses.setLayout(new GridLayout(1, 2));
		addButton("[", "left bracket", parentheses, new InsertAction("["));
		addButton("]", "right bracket", parentheses, new InsertAction("]"));
		misc.add(parentheses);

		// Adds the clear and submit button
		addButton("clear", "clears the text field", misc, new ClearAction());
		addButton("delete", "deletes the last character in the text field", misc, new DeleteAction());
		buttons.add(misc);
		
		add(buttons, BorderLayout.CENTER);
		
		JButton submitButton = new JButton("submit");
		submitButton.setToolTipText("submits what is in the text field");
		submitButton.addActionListener(submitAction);
		add(submitButton, BorderLayout.SOUTH);
	}
	
	public String getText() {
		return textField.getText();
	}

	private void addButton(String label, String toolTip, JPanel panel,
			ActionListener listener) {
		JButton button = new JButton(label);
		button.setToolTipText(toolTip);
		button.addActionListener(listener);
		panel.add(button);
	}

	private class ClearAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			textField.setText("");
		}
	}

	private class InsertAction implements ActionListener {

		private String symbol;

		public InsertAction(String symbol) {
			this.symbol = symbol;
		}

		public void actionPerformed(ActionEvent event) {
			String result = textField.getText();
			textField.setText(result + symbol);
		}
	}

	private class DeleteAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String text = textField.getText();
			if (!text.isEmpty()){
				String result = text.substring(0, text.length() - 1);
				textField.setText(result);
			}
		}
	}

}
