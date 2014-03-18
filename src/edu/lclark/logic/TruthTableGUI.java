package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/** A GUI that displays a truth table */
public class TruthTableGUI extends JFrame {
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 693;

    private static ButtonPanel buttons;
    private static JTextField errorField;
    
    public TruthTableGUI() {
    	errorField = new JTextField();
    	JPanel panel = new JPanel();
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	System.out.println(panel.getLayout());
    	buttons = new TFButtonPanel(new SubmitAction(this));
    	panel.add(buttons);
    	panel.add(errorField);
    	add(panel, BorderLayout.NORTH);
        //pack();
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
		
		SubmitAction(TruthTableGUI gui) {
			this.gui = gui;
		}
		
		public void actionPerformed(ActionEvent event) {
			String formula = buttons.getText();
			WffChecker checker = new WffChecker();
			if (checker.setInputString(formula)) {
				gui.getContentPane().removeAll();
				gui.add(new TruthTablePanel(formula));
				gui.setVisible(true);
			} else {
				errorField.setText(checker.getErrors());
				buttons.hilitTextField(checker.getErrorPositionInLine());
			}
		}
	}
}