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
    
    private TruthTablePanel truthTablePanel;
    
    public TruthTableGUI() {
        buttons = new TFButtonPanel(new SubmitAction(this));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(buttons);
        add(panel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        newWindow();
    }

    public static void newWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
//                System.setProperty("apple.laf.useScreenMenuBar", "true");
                final TruthTableGUI gui = new TruthTableGUI();
                gui.setTitle("Truth Table");
                JMenuBar menuBar = new JMenuBar();
                JMenu fileMenu = new JMenu("Actions");
                JMenuItem addTargetFormulaItem = new JMenuItem("Add Target Formula");
                addTargetFormulaItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        newWindow();
                    }
                });
                fileMenu.add(addTargetFormulaItem);
                JMenuItem addColumnItem = new JMenuItem("Add Scratchwork Column");
                addColumnItem.addActionListener(new ActionListener() {                    
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        gui.truthTablePanel.addColumn();
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
            buttons.removeHilits();
            String formula = buttons.getText();
            TfWffChecker tfWffChecker = new TfWffChecker(formula);
            
            TruthTablePanel panel;
            if (firstClick && tfWffChecker.isWff()) {
                panel = new TruthTablePanel(buttons);
                gui.truthTablePanel = panel;
                gui.add(panel);
                firstClick = false;
                buttons.setErrorText("");
                gui.setTitle(formula);
            } else {
                panel = gui.truthTablePanel;
            }

            if (tfWffChecker.isWff()) {
                if (!firstClick) {
                    int numCols = panel.getTruthTable().getNumRows();
                    TruthTableChecker checker = panel.getChecker();
                    if (!checker.isSubFormula(formula)) {
                        buttons.setErrorText("Illegal column! " + formula
                                + " is not a sub-formula of "
                                + checker.getFormula());
                    } else {
                        buttons.clearText();
                        buttons.setVisible(false);
                        panel.addColumn(new TruthTableColumn(formula,
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