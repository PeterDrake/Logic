package edu.lclark.logic;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LogicTrainer extends JFrame {
    private static final long serialVersionUID = 1L;
    
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 150;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LogicTrainer gui = new LogicTrainer();
                gui.setTitle("Truth Table");
                JButton button1 = new JButton("Truth Table Builder");
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TruthTableGUI.newWindow();
                    }
                });
                gui.add(button1);
                gui.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setVisible(true);
            }
        });
	}
}
