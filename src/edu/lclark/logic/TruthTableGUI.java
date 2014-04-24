package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** A GUI that displays a truth table */
public class TruthTableGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;

    private ButtonPanel buttons;
    
    private TruthTablePanel truthTablePanel;
    
    public TruthTableGUI(String[] symbols) {
        buttons = new TFButtonPanel(new SubmitAction(this), symbols);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(buttons);
        add(panel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        newWindow(null);
    }

    public static void newWindow(final String[] symbols) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
//                System.setProperty("apple.laf.useScreenMenuBar", "true");
                final TruthTableGUI gui = new TruthTableGUI(symbols);
                gui.setTitle("Truth Table Builder");
                JMenuBar menuBar = new JMenuBar();
                JMenu fileMenu = new JMenu("Actions");
                JMenuItem addTargetFormulaItem = new JMenuItem("Add Target Formula");
                addTargetFormulaItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        newWindow(symbols);
                    }
                });
                fileMenu.add(addTargetFormulaItem);
                JMenuItem addColumnItem = new JMenuItem("Add Scratchwork Column");
                addColumnItem.addActionListener(new ActionListener() {                    
                    @Override
                    public void actionPerformed(ActionEvent event) {
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
//                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            gui.buttons.removeHilits();
            String formula = gui.buttons.getText();
            TfWffChecker tfWffChecker = new TfWffChecker(formula);
            
            TruthTablePanel panel;
            if (firstClick && tfWffChecker.isWff()) {
                panel = new TruthTablePanel(gui.buttons);
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
                        gui.buttons.setErrorText("Illegal column! " + formula
                                + " is not a sub-formula of "
                                + checker.getFormula());
                    } else {
                        gui.buttons.clearText();
                        gui.buttons.setVisible(false);
                        panel.addColumn(new TruthTableColumn(formula,
                            new boolean[numCols]));
                        gui.buttons.setErrorText("");
                        gui.pack();
                    }
                }
            } else {
                gui.buttons.setErrorText(tfWffChecker.getErrors());
                gui.buttons.hilitTextField(tfWffChecker.getErrorPositionInLine(),
                        formula.length());
            }
        }
    }
}