package edu.lclark.logic;

import java.io.IOException;

import asg.cliche.Command;
import asg.cliche.ShellFactory;

public class LogicCLI implements View {
	
	private WffChecker wc;
	
	@Command
	public boolean wff(String s) {
		wc = new WffChecker();
		wc.setInputString(s);
		return wc.isValidSyntax();
	}
	
	@Command
	public boolean tree(String s) {
		wc = new WffChecker();
		wc.setInputString(s);
		wc.printTree();
		return wc.isValidSyntax();
	}
	
	@Command
	public boolean gui(String s) {
		wc = new WffChecker();
		wc.setInputString(s);
		wc.guiTree();
		return wc.isValidSyntax();
	}
	
	public static void main(String[] args) throws IOException  {
		ShellFactory.createConsoleShell("wffchecker", "", new LogicCLI())
            .commandLoop();
    }
}