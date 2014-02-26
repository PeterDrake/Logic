package edu.lclark.logic;

import java.io.IOException;

import asg.cliche.Command;
import asg.cliche.ShellFactory;

public class LogicCLI implements View {
	
	@Command
	public boolean wff(String s) {
		WffChecker wc = new WffChecker(s);
		return wc.isValidSyntax();
	}
	
	@Command
	public boolean tree(String s) {
		WffChecker wc = new WffChecker(s);
		return wc.isValidSyntaxTree();
	}
	
	public static void main(String[] args) throws IOException  {
		ShellFactory.createConsoleShell("wffchecker", "", new LogicCLI())
            .commandLoop();
    }
}