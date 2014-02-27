package edu.lclark.logic;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class TestButton {

	private static String formula;
	private static ButtonPanel buttons;
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable()
        {
           public void run()
           {
              JFrame frame = new JFrame();
              ActionListener submitAction = new SubmitAction();
              frame.add(buttons = new ButtonPanel(submitAction));
              frame.pack();
              frame.setTitle("Buttons");               
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
           }
        });
	}

	private static class SubmitAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			formula = buttons.getText();
			System.out.println(formula);
		}
	}
}
