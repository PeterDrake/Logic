package edu.lclark.logic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class LogicTrainer extends JFrame {
    private static final long serialVersionUID = 1L;
    
    public static final int DEFAULT_WIDTH = 200;
    public static final int DEFAULT_HEIGHT = 100;
    public static final int GAP = 5;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LogicTrainer gui = new LogicTrainer();
                FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
                flowLayout.setHgap(GAP);
                flowLayout.setVgap(GAP);
                gui.setLayout(flowLayout);
                gui.setTitle("Logic Trainer");
                
                JMenuBar menuBar = new JMenuBar();
                JMenu actionsMenu = new JMenu("Actions");
                JMenuItem preferencesItem = new JMenuItem("Preferences");
                preferencesItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        new PreferencePane();
                    }

                });
                
                menuBar.add(actionsMenu);
                gui.setJMenuBar(menuBar);
                
                JButton button1 = new JButton("Truth Table Builder");
                button1 .setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TruthTableGUI.newWindow();
                    }
                });
                gui.add(button1);
                JButton button2 = new JButton("Quantificational Logic");
                button2.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        QTestButton.newWindow();
                    }
                });
                gui.add(button2);
                JButton button3 = new JButton("Truth Functional Logic");
                button3.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        TFTestButton.newWindow();
                    }
                });
                gui.add(button3);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.pack();
                gui.setVisible(true);
            }
        });
	}
}
