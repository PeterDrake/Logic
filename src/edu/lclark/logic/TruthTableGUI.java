package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** A GUI that displays a truth table */
public class TruthTableGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 693;

	private static ButtonPanel buttons;
	private static TruthTablePanel truthTablePanel;

	public TruthTableGUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		buttons = new TFButtonPanel(new SubmitAction(this));
		panel.add(buttons);
		add(panel, BorderLayout.NORTH);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				TruthTableGUI gui = new TruthTableGUI();
				gui.setTitle("Truth Table");
				gui.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gui.setVisible(true);
			}
		});
	}

	private static class SubmitAction extends AbstractAction {

		private TruthTableGUI gui;
		private boolean firstClick;

		SubmitAction(TruthTableGUI gui) {
			this.gui = gui;
			firstClick = true;
		}

		public void actionPerformed(ActionEvent event) {
			buttons.removeHilits();
			String formula = buttons.getText();
			TfWffChecker checker = new TfWffChecker(formula);
			if (checker.isWff()) {
				if (firstClick) {
					truthTablePanel = new TruthTablePanel(buttons);
					gui.add(truthTablePanel);
					firstClick = false;
					buttons.setErrorText("");
					System.out.println("Finished creating truth table");
					buttons.setVisible(false);
					gui.validate(); // Because we've added a component: truthTablePanel
				} else {
					TruthTable table = truthTablePanel.getTruthTable();
					if (table.isValidColumn(formula)) {
						buttons.setVisible(false);
						int numRows = table.getNumRows();
						truthTablePanel.addColumn(new TruthTableColumn(formula,
								new boolean[numRows]));
						buttons.setErrorText("");
					} else {
						buttons.setErrorText(formula
								+ " is not a valid column for this truth table.");
					}
				}
				buttons.clearText();
			} else {
				buttons.setErrorText(checker.getErrors());
				buttons.hilitTextField(checker.getErrorPositionInLine(),
						formula.length());
			}
		}

	}
}