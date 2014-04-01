package edu.lclark.logic;

import java.util.HashSet;

public class VennDiagrams {

	PredicateLetter UD;

	private HashSet<PredicateLetter> hashSet;

	public VennDiagrams(String UDString) {
		hashSet = new HashSet<PredicateLetter>();
		UD = new PredicateLetter("UD", UDString);
	}

	public void remove(PredicateLetter p) {
		hashSet.remove(p);

	}

	public void add(PredicateLetter p) {
		hashSet.add(p);
	}

}
