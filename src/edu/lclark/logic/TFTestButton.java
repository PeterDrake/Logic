package edu.lclark.logic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class TFTestButton {

	private static String formula;
	private static ButtonPanel buttons;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setLayout(new BorderLayout());

				
				Action submitAction = new SubmitAction();
				frame.add(buttons = new TFButtonPanel(submitAction, null),
						BorderLayout.CENTER);
				frame.pack();
				frame.setTitle("Truth Functional Wff Checker");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			TfWffChecker wc = new TfWffChecker(formula);
			if (wc.isWff()) {
				wc.guiTree();
			} else {
				buttons.hilitTextField(wc.getErrorPositionInLine(),
						formula.length());
			}
			buttons.setErrorText(wc.getErrors());
		}
	}

	public static void newWindow(final String[] symbols) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setLayout(new BorderLayout());

				Action submitAction = new SubmitAction();
				frame.add(buttons = new TFButtonPanel(submitAction, symbols),
						BorderLayout.CENTER);
				frame.pack();
				frame.setTitle("Truth Functional Wff Checker");
				frame.setVisible(true);
			}
		});
	}

}
