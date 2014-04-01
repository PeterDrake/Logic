package edu.lclark.logic;

import java.util.HashSet;

public class VennDiagrams {
	
	private HashSet<PredicateLetter> hashSet;
	
	public VennDiagrams() {
		hashSet = new HashSet<PredicateLetter>();
	}
	
	public void remove(PredicateLetter p) {
		hashSet.remove(p);
		
	}
	
	public void add(PredicateLetter p) {
		hashSet.add(p);
	}
	
}
