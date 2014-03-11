package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class QTestButton {

	private static String formula;
	private static ButtonPanel buttons;
	private static JTextField output;
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable()
        {
           public void run()
           {
              JFrame frame = new JFrame();
              frame.setLayout(new BorderLayout());
              output = new JTextField();
              output.setEditable(false);
              
              Action submitAction = new SubmitAction();
              frame.add(buttons = new QButtonPanel(submitAction), BorderLayout.CENTER);
              frame.add(output, BorderLayout.SOUTH);
              frame.pack();
              frame.setTitle("Buttons");               
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
           }
        });
	}

	private static class SubmitAction extends AbstractAction {
		public void actionPerformed(ActionEvent event) {
			formula = buttons.getText();
			WffChecker wc = new WffChecker();
			wc.setInputString(formula);
			wc.guiTree();
			output.setText(wc.getErrors());
		}
	}
}