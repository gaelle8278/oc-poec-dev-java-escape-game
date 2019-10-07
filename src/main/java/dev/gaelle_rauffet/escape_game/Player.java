package dev.gaelle_rauffet.escape_game;

public interface Player {
	
	/**
	 * Set a response, with code : +,-,=, to indicate if a given test combination corresponds to combination
	 * 
	 * Combinations are composed of integers.
	 * 
	 * @return
	 */
	public void checkCombination(Combination combinationToFind);
	
	/**
	 * Defines a guess value of combination
	 * 
	 * @return
	 */
	public void guessCombination(Combination combinationToFind);
}
