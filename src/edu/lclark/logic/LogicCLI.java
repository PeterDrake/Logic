package edu.lclark.logic;

import asg.cliche.Command;
import asg.cliche.ShellFactory;

import java.io.IOException;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class LogicCLI implements View {
	
	@Command
	public boolean wff(String s) {
		WffChecker wc = new WffChecker(s);
		return wc.isValidSyntax();
	}
	
	public static void main(String[] args) throws IOException  {
		ShellFactory.createConsoleShell("wffchecker", "", new LogicCLI())
            .commandLoop();
    }
}