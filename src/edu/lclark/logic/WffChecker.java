package edu.lclark.logic;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.InputStream;

public class WffChecker {

	public static void main(String[] args) throws Exception {
//		String is = "((p . q v r) v (q . r))";
//		String is = "( p . q v r )";
		String is = "p & -q";
		ANTLRInputStream input = new ANTLRInputStream(is); 
		wffLexer lexer = new wffLexer(input); 
		CommonTokenStream tokens = new CommonTokenStream(lexer); 
		wffParser parser = new wffParser(tokens); 
		ParseTree tree = parser.formula(0);
		System.out.println(tree.toStringTree(parser));
	}
}

