package edu.lclark.logic;

import java.io.IOException;

import asg.cliche.Command;
import asg.cliche.ShellFactory;

public class LogicCLI {
		
	@Command
	public void tfwff(String s) {
		TfWffChecker tfwc = new TfWffChecker(s);
		System.out.println(tfwc.isWff());
	}
	
	@Command
	public void tftree(String s) {
		TfWffChecker tfwc = new TfWffChecker(s);
		tfwc.printTree();
	}
	
	@Command
	public void tfgui(String s) {
		TfWffChecker tfwc = new TfWffChecker(s);
		tfwc.guiTree();
	}

	@Command
	public void qfwff(String s) {
		QfWffChecker qfwc = new QfWffChecker(s);
		System.out.println(qfwc.isWff());
	}

	@Command
	public void qftree(String s) {
		QfWffChecker qfwc = new QfWffChecker(s);
		qfwc.printTree();
	}

	@Command
	public void qfgui(String s) {
		QfWffChecker qfwc = new QfWffChecker(s);
		qfwc.guiTree();
	}

	public LogicCLI() {
		
	}

	public static void main(String[] args) throws IOException {
		ShellFactory.createConsoleShell("wffchecker", "", new LogicCLI())
				.commandLoop();
	}
}