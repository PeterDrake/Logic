package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class QTestButton {

	private static String formula;
	private static ButtonPanel buttons;

	public static void main(String[] args) {
	    newWindow(null);
	}
	
	public static void newWindow(final String[] symbols) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setLayout(new BorderLayout());

				Action submitAction = new SubmitAction();
				frame.add(buttons = new QButtonPanel(submitAction, symbols),
						BorderLayout.CENTER);
				frame.pack();
				frame.setTitle("Quantificational Wff Checker");
				frame.setVisible(true);
			}
		});
	}

	private static class SubmitAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent event) {
			buttons.removeHilits();
			formula = buttons.getText();
			QfWffChecker wc = new QfWffChecker(formula);
			if (wc.isWff()) {
				wc.guiTree();
			} else {
				buttons.hilitTextField(wc.getErrorPositionInLine(),
						formula.length());
			}
			buttons.setErrorText(wc.getErrors());
		}
	}
}
