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
	 */

	private JTextField textField;
	private String text;

	public ButtonPanel(ActionListener submitAction) {
		setLayout(new BorderLayout());
		textField = new JTextField();
		textField.setEditable(false);
		//textField.setText("you can change text fields");
		add(textField, BorderLayout.NORTH);

		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());

		JPanel sentenceLetters = new JPanel();
		sentenceLetters.setLayout(new GridLayout(5, 1));
		addButton("p", "sentence letter", sentenceLetters,
				new InsertAction("p"));
		addButton("q", "sentence letter", sentenceLetters,
				new InsertAction("q"));
		addButton("r", "sentence letter", sentenceLetters,
				new InsertAction("r"));
		addButton("s", "sentence letter", sentenceLetters,
				new InsertAction("s"));
		addButton("t", "sentence letter", sentenceLetters,
				new InsertAction("t"));
		buttons.add(sentenceLetters);

		JPanel connectives = new JPanel();
		connectives.setLayout(new GridLayout(5, 1));
		addButton("-", "negation", connectives, new InsertAction("-"));
		addButton(".", "and", connectives, new InsertAction("."));
		addButton("⋁", "or", connectives, new InsertAction("V"));
		addButton("→", "conditional", connectives, new InsertAction("→"));
		addButton("↔", "biconditional", connectives, new InsertAction("↔"));
		buttons.add(connectives);

		JPanel misc = new JPanel();
		misc.setLayout(new GridLayout(5, 1));

		JPanel parentheses = new JPanel();
		parentheses.setLayout(new GridLayout(1, 2));
		addButton("(", "left parens", parentheses, new InsertAction("("));
		addButton(")", "right parens", parentheses, new InsertAction(")"));
		misc.add(parentheses);

		parentheses = new JPanel();
		parentheses.setLayout(new GridLayout(1, 2));
		addButton("[", "left bracket", parentheses, new InsertAction("["));
		addButton("]", "right bracket", parentheses, new InsertAction("]"));
		misc.add(parentheses);

		addButton("clear", "clears the text field", misc, new ClearAction());
		addButton("delete", "deletes the last character in the text field",
				misc, new DeleteAction());
		addButton("submit", "submits what is in the text field", misc,
				submitAction);

		buttons.add(misc);
		add(buttons, BorderLayout.CENTER);
	}
	public String getText() {
		return textField.getText();
	}

	private void addButton(String label, String toolTip, JPanel panel,
			ActionListener listener) {
		JButton button = new JButton(label);
		button.setToolTipText(toolTip);
		button.addActionListener(listener);
		button.setBackground(Color.GREEN);
		button.setOpaque(true);
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
