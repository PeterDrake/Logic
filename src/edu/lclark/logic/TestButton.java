package edu.lclark.logic;

import java.awt.EventQueue;

import javax.swing.JFrame;


public class TestButton {

	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable()
        {
           public void run()
           {
              JFrame frame = new JFrame();
              frame.add(new ButtonPanel());
              frame.pack();
              frame.setTitle("Buttons");               
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setVisible(true);
           }
        });
	}

}
