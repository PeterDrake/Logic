package edu.lclark.logic;

import java.io.IOException;

import asg.cliche.Command;
import asg.cliche.ShellFactory;

public class LogicCLI implements View {

	private WffChecker wc;

	@Command
	public void wff(String s) {
		System.out.println(wc.setInputString(s));
	}

	@Command
	public void tree(String s) {
		wc.setInputString(s);
		System.out.println(wc.printTree());
	}

	@Command
	public void gui(String s) {
		wc.setInputString(s);
		wc.guiTree();
	}

	public LogicCLI() {
		wc = new WffChecker();
	}

	public static void main(String[] args) throws IOException {
		ShellFactory.createConsoleShell("wffchecker", "", new LogicCLI())
				.commandLoop();
	}
}