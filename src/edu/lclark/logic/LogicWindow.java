package edu.lclark.logic;

import javax.swing.JWindow;

public class LogicWindow extends JWindow implements View {
	Controller controller;
	Model model;

	public void init() {
		this.controller = new LogicController(this, this.model);

		// Swing stuff goes here

		this.controller.refresh();
	}

}
