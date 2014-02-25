package edu.lclark.logic;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class InputFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 693;
    
    public InputFrame() {
        add(new InputCanvas());
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                InputFrame frame = new InputFrame();
                frame.setTitle("Truth Table Builder");
                frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}