package dev.gaelle_rauffet.escape_game;

public interface Player {
	
	/**
	 * Return a response, with code : +,-,=, to indicate if a given combination corresponds to a reference combination
	 * 
	 * Combinations are composed of integers.
	 * 
	 * @param combinationTest			combination to test
	 * @param combinationReference		combination to compare to
	 * @return
	 */
	public String[] checkCombination(int[] combinationTest, int[] combinationReference);
	
	/**
	 * Defines a combination of the given size
	 * 
	 * @param size		size of the combination
	 * @return
	 */
	public int[] guessCombination(String[] indications, int size);
}
