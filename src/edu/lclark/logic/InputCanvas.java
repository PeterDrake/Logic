package edu.lclark.logic;

import javax.swing.JFrame;

public class InputCanvas extends JFrame implements View {

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	public InputCanvas() {

		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new InputCanvas();
	}

}
