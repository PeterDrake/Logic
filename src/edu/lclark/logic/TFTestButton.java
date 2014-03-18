package edu.lclark.logic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TFTestButton {

	private static String formula;
	private static ButtonPanel buttons;
	private static JTextField output;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setLayout(new BorderLayout());
				output = new JTextField();
				output.setEditable(false);

				Action submitAction = new SubmitAction();
				frame.add(buttons = new TFButtonPanel(submitAction),
						BorderLayout.CENTER);
				frame.add(output, BorderLayout.SOUTH);
				frame.pack();
				frame.setTitle("Buttons");
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
			WffChecker wc = new WffChecker();
			if (wc.setInputString(formula)) {
				wc.guiTree();
			} else {
				buttons.hilitTextField(wc.getErrorPositionInLine(),
						formula.length());
			}
			output.setText(wc.getErrors());
		}
	}

}
