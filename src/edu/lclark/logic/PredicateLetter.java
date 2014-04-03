package edu.lclark.logic;

import java.util.ArrayList;

public class PredicateLetter {

	private ArrayList<String> elements;

	/** the name of the predicate letter. e.g F, G, H, UD. */
	private String name;

	public PredicateLetter(String name, String elements) {
		this.name = name;
		this.elements = new ArrayList<String>();
		parse(elements);
	}

	public void remove(String s) {
		elements.remove(s);
	}

	public void add(String s) {
		elements.add(s);
	}

	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @param elements
	 *            contains the user input for the elements in the predicate set
	 */
	public void parse(String elements) {
		String[] letters = elements.substring(1, elements.length() - 1).split(",");
		for (String element : letters) {
			add(element.trim());
		}
	}

	public ArrayList<String> getElements() {
		return elements;
	}

}
