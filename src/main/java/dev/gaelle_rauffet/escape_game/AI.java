package dev.gaelle_rauffet.escape_game;

import java.util.Random;

/**
 * 
 * @author gaelle
 *
 */
public class AI implements Player {
	
	

	/**
	 * Compare to combination
	 * 
	 * @param combinationTest			combination test
	 * @param combinationToFind			combination reference, the combination to find
	 * @return 
	 */
	@Override
	public String[] checkCombination(Combination combinationTest, int[] combinationToFind) {
		//check both arrays have the same size ?
		String[] response = new String[combinationToFind.length];
		int[] combinationTestValue = combinationTest.getValue();
		for (int i=0; i < combinationToFind.length; i++) {
			if(combinationTestValue[i] == combinationToFind[i]) {
				response[i] = "=";
			} else if (combinationTestValue[i] > combinationToFind[i]) {
				response[i] = "-";
			} else {
				response[i] = "+";
			}
		}
		
		return response;
		
	}


	@Override
	public void guessCombination(Combination combination) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * Define a combination of a given size
	 * 
	 * @param size
	 * @return
	 */
	/*public int[] setCombination(int size) {
		int[] combination = new int[size];
		for(int i = 0; i < size; i++) {
			combination[i] = setRandomNumber();
		}
		return combination;
	}*/
	
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
	
	

}
