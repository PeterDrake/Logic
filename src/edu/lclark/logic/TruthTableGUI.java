package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** A GUI that displays a truth table */
public class TruthTableGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_WIDTH = 1000;
    public static final int DEFAULT_HEIGHT = 750;

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
        private static final long serialVersionUID = 1L;

        private TruthTableGUI gui;
        private boolean firstClick;

        SubmitAction(TruthTableGUI gui) {
            this.gui = gui;
            firstClick = true;
        }

        public void actionPerformed(ActionEvent event) {
            buttons.removeHilits();
            String formula = buttons.getText();
            TfWffChecker tfWffChecker = new TfWffChecker(formula);
            if (firstClick && tfWffChecker.isWff()) {
                truthTablePanel = new TruthTablePanel(buttons);
                gui.add(truthTablePanel);
                firstClick = false;
                buttons.setErrorText("");
            }

            if (tfWffChecker.isWff()) {
                if (!firstClick) {
                    int numCols = truthTablePanel.getTruthTable().getNumRows();
                    TruthTableChecker checker = truthTablePanel.getChecker();
                    if (!checker.isSubFormula(formula)) {
                        buttons.setErrorText("Illegal column! " + formula
                                + " is not a sub-formula of "
                                + checker.getFormula());
                    } else {
                        buttons.clearText();
                        buttons.setVisible(false);
                        truthTablePanel.addColumn(new TruthTableColumn(formula,
                            new boolean[numCols]));
                        buttons.setErrorText("");
                    }
                }
            } else {
                buttons.setErrorText(tfWffChecker.getErrors());
                buttons.hilitTextField(tfWffChecker.getErrorPositionInLine(),
                        formula.length());
            }
        }

    }
}