package edu.lclark.logic;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class TruthTableFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 693;
    
    public TruthTableFrame() {
        add(new TruthTableGUI());
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                TruthTableFrame frame = new TruthTableFrame();
                frame.setTitle("Truth Table Builder");
                frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}