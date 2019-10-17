package dev.gaellerauffet.escapegame.player;

import dev.gaellerauffet.escapegame.game.Combination;

/**
 * Contract to define player usable in game
 * @author gaelle
 *
 */
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
	 * Defines a test value of combination
	 * 
	 * @return
	 */
	public void guessCombination(Combination combinationToFind);

	/**
	 * Defines the combination value
	 * 
	 * @param combination
	 */
	void setValueCombination(Combination combination);

	/**
	 * Check that a given response is consistent with all previous test and response
	 * 
	 * @param combination
	 */
	public void checkConsistentResponse(Combination combination);
}
