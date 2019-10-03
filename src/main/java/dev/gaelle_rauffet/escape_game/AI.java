package dev.gaelle_rauffet.escape_game;

import java.util.Random;

/**
 * 
 * @author gaelle
 *
 */
public class AI implements Player {
	
	@Override
	public void checkCombination(Combination combinationToFind) {
		String[] responseGuessTest = new String[combinationToFind.getLength()];
		int[] valueGuessTest = combinationToFind.getGuessValue();
		int[] valueCombination = combinationToFind.getValue();
		for (int i=0; i < combinationToFind.getLength(); i++) {
			if(valueGuessTest[i] == valueCombination[i]) {
				responseGuessTest[i] = "=";
			} else if (valueGuessTest[i] > valueCombination[i]) {
				responseGuessTest[i] = "-";
			} else {
				responseGuessTest[i] = "+";
			}
		}
		
		combinationToFind.setResponseValue(responseGuessTest);
	}
	
	@Override
	public void guessCombination(Combination combination) {
		// TODO Auto-generated method stub
		
	}
	
	/** 
	 * Define a value for combination
	 * @param combinationToFind
	 */
	public void setValueCombination(Combination combinationToFind) {
		int[] combination = new int[combinationToFind.getLength()];
		for(int i = 0; i < combinationToFind.getLength(); i++) {
			combination[i] = setRandomNumber();
		}
		combinationToFind.setValue(combination);
		
	}
	
	
	/**
	 * Defines a random number between o and 9
	 * 
	 * @return
	 */
	private int setRandomNumber() {
	    Random random = new Random();

	    return random.ints(0,10).findFirst().getAsInt();
	
	}

}
