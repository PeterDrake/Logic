package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/** A GUI that displays a truth table */
public class TruthTableGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 300;

	private ButtonPanel buttons;
	private Highlighter hilit;
	private Highlighter.HighlightPainter painter;
	private TruthTablePanel truthTablePanel;

	public TruthTableGUI(String[] symbols) {
		buttons = new TFButtonPanel(new SubmitAction(this), symbols);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(buttons);
		add(panel, BorderLayout.NORTH);
		buttons.getErrorTextField().setEditable(false);
	}

	public ButtonPanel getButtons() {
		return buttons;
	}

	public static void newWindow(final String[] symbols) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// System.setProperty("apple.laf.useScreenMenuBar", "true");
				final TruthTableGUI gui = new TruthTableGUI(symbols);
				gui.setTitle("Truth Table Builder");
				JMenuBar menuBar = new JMenuBar();
				JMenu fileMenu = new JMenu("Actions");
				JMenuItem addTargetFormulaItem = new JMenuItem(
						"Add Target Formula");
				addTargetFormulaItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						newWindow(symbols);
					}
				});
				fileMenu.add(addTargetFormulaItem);
				JMenuItem addColumnItem = new JMenuItem(
						"Add Scratchwork Column");
				addColumnItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						if (gui.truthTablePanel == null) {
							String error = "Add a target formula first";
							gui.buttons.setErrorText(error);
							gui.highlight(error);
							return;
						}
						gui.buttons.getTextField().setText(
								"Type a scratchwork formula");
						gui.truthTablePanel.addColumn();
						gui.pack();
					}
				});
				fileMenu.add(addColumnItem);
				JMenuItem checkValuesItem = new JMenuItem("Check Values");
				checkValuesItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						gui.truthTablePanel.checkValues();
					}
				});
				fileMenu.add(checkValuesItem);
				menuBar.add(fileMenu);
				gui.setJMenuBar(menuBar);
				gui.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
				// gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gui.setVisible(true);
			}
		});
	}

	private void highlight(String error) {
		hilit = new DefaultHighlighter();
		painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
		buttons.getErrorTextField().setHighlighter(hilit);
		try {
			hilit.addHighlight(0, error.length(), painter);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static class SubmitAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private TruthTableGUI gui;
		private boolean firstClick;

		SubmitAction(TruthTableGUI gui) {
			this.gui = gui;
			firstClick = true;
		}

		public void actionPerformed(ActionEvent event) {
			gui.buttons.removeHilits();
			String formula = gui.buttons.getText();
			TfWffChecker tfWffChecker = new TfWffChecker(formula);

			TruthTablePanel panel;
			if (firstClick && tfWffChecker.isWff()) {
				panel = new TruthTablePanel(gui);
				gui.truthTablePanel = panel;
				gui.add(panel);
				firstClick = false;
				gui.buttons.setErrorText("");
				gui.setTitle(formula);
				gui.pack();
			} else {
				panel = gui.truthTablePanel;
			}

			if (tfWffChecker.isWff()) {
				if (!firstClick) {
					int numCols = panel.getTruthTable().getNumRows();
					TruthTableChecker checker = panel.getChecker();
					if (!checker.isSubFormula(formula)) {
						String error = "Illegal column: " + formula
								+ " is not a sub-formula of "
								+ checker.getFormula();
						gui.buttons.setErrorText(error);
						gui.highlight(error);
					} else {
						if (!panel.addColumn(new TruthTableColumn(formula,
								new boolean[numCols]))) {
							String error = "Redundant column";
							gui.buttons.setErrorText(error);
							gui.highlight(error);
						} else {
							gui.buttons.clearText();
							gui.buttons.setErrorText("");
							gui.buttons.setVisible(false);
							gui.buttons.setFirstPress(true);
						}
						gui.pack();
					}
				}
			} else {
				gui.buttons.setErrorText(tfWffChecker.getErrors());
				gui.buttons
						.hilitTextField(tfWffChecker.getErrorPositionInLine(),
								formula.length());
			}
		}
	}
}