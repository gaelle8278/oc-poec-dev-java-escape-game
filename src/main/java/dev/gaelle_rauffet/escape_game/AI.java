package dev.gaelle_rauffet.escape_game;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author gaelle
 *
 */
public class AI implements Player {
	//int[][] storedTests;
	private ArrayList<int[]> storedTests = new ArrayList<int[]>();
	
	
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
	public void guessCombination(Combination combinationToFind) {
		if(storedTests.isEmpty()) {
			this.setGuessCombination(combinationToFind);
		} else {
			this.setGuessCombinationFromResponse(combinationToFind);
		}
		
		// add the new combination in the storage
		storedTests.add(combinationToFind.getGuessValue());
		
	}
	
	

	/** 
	 * Define a value for combination
	 * @param combinationToFind
	 */
	public void setValueCombination(Combination combinationToFind) {
		int[] combination = setCombination(combinationToFind.getLength());
		combinationToFind.setValue(combination);
		
	}
	
	/** 
	 * Define a value for combination test
	 * @param combinationToFind
	 */
	private void setGuessCombination(Combination combinationToFind) {
		int[] guessCombination = setCombination(combinationToFind.getLength());
 		combinationToFind.setGuessValue(guessCombination);
		
	}
	
	/**
	 * Define a value for combination test from indications 
	 * @param combinationToFind
	 */
	private void setGuessCombinationFromResponse(Combination combinationToFind) {
		int[] guessCombination = new int[combinationToFind.getLength()];
		//get last proposition
		int[] lastTest = storedTests.get(storedTests.size() - 1);
		//get response from player
		String[] responseValue = combinationToFind.getResponseValue();
		for (int i = 0; i < lastTest.length; i++) {
			if(responseValue[i].equals("=") ) {
				guessCombination[i] = lastTest[i];
			} else if (responseValue[i].equals("-") ){
				guessCombination[i] = this.setRandomNumber(0, lastTest[i]);
			} else {
				guessCombination[i] =  this.setRandomNumber(lastTest[i] + 1, 10);
			}
					
		}
		combinationToFind.setGuessValue(guessCombination);
	}
	
	/**
	 * Set combination value of a given size
	 * @param size
	 * @return
	 */
	private int[] setCombination(int size) {
		int[] combination = new int[size];
		for(int i = 0; i < size; i++) {
			combination[i] = setRandomNumber(0, 10);
		}
		
		return combination;
	}
	
	/**
	 * Defines a random number between 0 and 9
	 * 
	 * @return
	 */
	private int setRandomNumber(int min, int max) {
	    Random random = new Random();

	    return random.ints(min,max).findFirst().getAsInt();
	
	}

}
