package edu.lclark.logic;

public class LogicController implements Controller {

	private View view;
	private Model model;

	public LogicController(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	public void refresh() {

	}

}
