package edu.lclark.logic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VennDiagramGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	
	public VennDiagramGUI() {
		add(new TruthTablePanel("p.qvr"));
		pack();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				VennDiagramGUI gui = new VennDiagramGUI();
				gui.setTitle("Venn Diagrams");
				gui.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gui.setVisible(true);
			}
		});
	}

}
