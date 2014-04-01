package edu.lclark.logic;

import java.util.HashSet;

public class PredicateLetter {

	private HashSet<Integer> hashSet;

	/** the name of the prediace letter. e.g F, G, H, UD. */
	private String name;

	public PredicateLetter(String name, String elements) {
		this.name = name;
		hashSet = new HashSet<Integer>();
		parse(elements);
	}

	public void remove(int i) {
		hashSet.remove(i);
	}

	public void add(int i) {
		hashSet.add(i);
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
		String[] letters = elements.substring(1, elements.length() - 1).split(
				",");
		for (String element : letters) {
			add(Integer.parseInt(element.trim()));
		}
	}

	public HashSet<Integer> getHashSet() {
		return hashSet;
	}

}
