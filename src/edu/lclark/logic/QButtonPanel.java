package edu.lclark.logic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.lclark.logic.ButtonPanel.ClearAction;
import edu.lclark.logic.ButtonPanel.DeleteAction;
import edu.lclark.logic.ButtonPanel.InsertAction;

public class QButtonPanel extends ButtonPanel {

	public QButtonPanel(ActionListener submitAction) {
		setLayout(new BorderLayout());
		// textField = new JTextField();
		setTextField(new JTextField());
		// textField.setEditable(true);
		getTextField().setEditable(true);
		// add(textField, BorderLayout.NORTH);
		add(getTextField(), BorderLayout.NORTH);

		// A panel that all the buttons, but the submit button are added to
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		
		// Makes a panel with all the connectives
		JPanel connectives = new JPanel();
		connectives.setLayout(new GridLayout(5, 1));
		addButton("-", "negation", connectives, new InsertAction("-"));
		addButton(".", "and", connectives, new InsertAction("."));
		addButton("⋁", "or", connectives, new InsertAction("⋁"));
		addButton("→", "conditional", connectives, new InsertAction("→"));
		addButton("↔", "biconditional", connectives, new InsertAction("↔"));
		buttons.add(connectives);

		// Makes a panel with all the sentence letters
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
		
		// ///////////
		JPanel lowerCase = new JPanel();
		lowerCase.setLayout(new GridLayout(5, 1));

		addButton("x", "sentence letter", lowerCase, new InsertAction("x"));
		addButton("y", "sentence letter", lowerCase, new InsertAction("y"));
		addButton("z", "sentence letter", lowerCase, new InsertAction("z"));
		addButton("v", "sentence letter", lowerCase, new InsertAction("v"));
		addButton("w", "sentence letter", lowerCase, new InsertAction("w"));
		buttons.add(lowerCase);

		// Creates a new panel with the remaining buttons
		JPanel misc = new JPanel();
		misc.setLayout(new GridLayout(6, 1));
		
		// ///////////////
		JPanel capSentenceLetters = new JPanel();
		capSentenceLetters.setLayout(new GridLayout(6, 1));

		addButton("F", "sentence letter", capSentenceLetters, new InsertAction("F"));
		addButton("G", "sentence letter", capSentenceLetters, new InsertAction("G"));
		addButton("H", "sentence letter", capSentenceLetters, new InsertAction("H"));
		addButton("I", "sentence letter", capSentenceLetters, new InsertAction("I"));
		addButton("J", "sentence letter", capSentenceLetters, new InsertAction("J"));
		addButton("K", "sentence letter", capSentenceLetters, new InsertAction("K"));
		buttons.add(capSentenceLetters);
		
		JPanel quantifiers = new JPanel();
		quantifiers.setLayout(new GridLayout(1, 2));
		addButton("∀", "for all", quantifiers, new InsertAction("∀"));
		addButton("∃", "there exists", quantifiers, new InsertAction("∃"));
		misc.add(quantifiers);

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
		submitButton.setFocusable(false);
		add(submitButton, BorderLayout.SOUTH);

	}

}
