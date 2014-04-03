package edu.lclark.logic;

import java.util.ArrayList;

public class VennDiagrams {

	PredicateLetter UD;

	private ArrayList<PredicateLetter> sets;

	public VennDiagrams(String UDString) {
		sets = new ArrayList<PredicateLetter>();
		UD = new PredicateLetter("UD", UDString);
	}

	public void remove(PredicateLetter p) {
		sets.remove(p);
	}

	public void add(PredicateLetter p) {
		sets.add(p);
	}

	public ArrayList<PredicateLetter> getSets() {
	    return sets;
	}
}
