package edu.lclark.logic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class ButtonPanel extends JPanel {

	/**
	 * The text field that buttons output to
	 */

	private static final long serialVersionUID = 1L;

	private JTextField textField;
	private Highlighter hilit;
	private Highlighter.HighlightPainter painter;
	private JTextField errorField;

	private boolean firstPress = true;

	public void setFirstPress(boolean value) {
		firstPress = value;
	}

	public void initializeEnterKey(Action submitAction) {
		InputMap imap = getTextField().getInputMap(
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "panel.submit");

		ActionMap amap = getTextField().getActionMap();
		amap.put("panel.submit", submitAction);
	}

	public void setErrorTextField(JTextField errorField) {
		this.errorField = errorField;
		this.errorField.setEditable(false);
	}

	public JTextField getErrorTextField() {
		return errorField;
	}

	public void setErrorText(String error) {
		errorField.setText(error);
	}

	public String getErrorText() {
		return errorField.getText();
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField() {
		return textField;
	}

	public String getText() {
		return textField.getText();
	}

	public void clearText() {
		textField.setText("");
	}

	protected void addButton(String label, String toolTip, JPanel panel,
			ActionListener listener) {
		JButton button = new JButton(label);
		button.setToolTipText(toolTip);
		button.addActionListener(listener);
		button.setFocusable(false);
		panel.add(button);
	}

	public void initializeHilit() {
		hilit = new DefaultHighlighter();
		painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
		textField.setHighlighter(hilit);
	}

	public void removeHilits() {
		hilit.removeAllHighlights();
	}

	public void hilitTextField(int errorPositionInLine, int formulaLength) {

		try {
			if (!textField.getText().isEmpty()
					&& !textField.getText().equals(null)) {
				if (errorPositionInLine == formulaLength
						&& !textField.getText()
								.substring(formulaLength - 1, formulaLength)
								.equals(" ")) {
					textField.setText(textField.getText() + " ");
				} else if (errorPositionInLine == formulaLength
						&& textField.getText()
								.substring(formulaLength - 1, formulaLength)
								.equals(" ")) {
					errorPositionInLine--;
				}
				hilit.addHighlight(errorPositionInLine,
						errorPositionInLine + 1, painter);
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	protected class ClearAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			textField.setText("");
		}
	}

	protected class InsertAction implements ActionListener {

		private String symbol;

		public InsertAction(String symbol) {
			this.symbol = symbol;
		}

		public void actionPerformed(ActionEvent event) {
			if (firstPress) {
				textField.setText(symbol);
				firstPress = false;
			} else {
				String result = textField.getText();
				textField.setText(result + symbol);
			}
		}
	}

	protected class DeleteAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String text = textField.getText();
			if (!text.isEmpty()) {
				String result = text.substring(0, text.length() - 1);
				textField.setText(result);
			}
		}
	}

}
