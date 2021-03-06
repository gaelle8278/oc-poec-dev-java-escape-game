package dev.gaellerauffet.escapegame.player.impl;

import java.util.ArrayList;
import dev.gaellerauffet.escapegame.combination.impl.Combination;
import dev.gaellerauffet.escapegame.exception.InvalidGameLapItemException;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Arithmetic;


public class AiPlayer implements Player {
	private ArrayList<int[]> storedTests; 
	
	public AiPlayer() {
		storedTests = new ArrayList<int[]>();
	}
	
	/** 
	 * Define a value for combination
	 * @param combinationToFind
	 */
	@Override
	public void giveCombinationValue(Combination combination) {
		int[] combinationValue = new int[combination.getLength()];
		for(int i = 0; i < combination.getLength(); i++) {
			combinationValue[i] = Arithmetic.getRandomNumber(0, 10);
		}
		
		combination.setValue(combinationValue);
	}
	
	@Override
	public void giveResponse(Combination combinationToFind) {
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
	public void giveTest(Combination combinationToFind) {
		int[] guessCombination = new int[combinationToFind.getLength()];
		
		// if at least one guess value is set and response value is null (= last given response is mal-formatted or inconsistency)
		// the same last guess value is kept 
		// otherwise a new guess value is set from zero or from the last correct response
		if( !( !storedTests.isEmpty() && combinationToFind.responseIsNull() ) ) {
			if (storedTests.isEmpty()) {
				// if no yet test => set a new guess value from zero
				guessCombination = giveInitTest(combinationToFind.getLength());
			} else {
				// at least one test is stored and a correct response is set
				// => set a new guess value from response and last test
				guessCombination = giveTestFromRules(combinationToFind);
			}
		
			// guess value is set to combination
			combinationToFind.setGuessValue(guessCombination);
		
			// add the new combination to the aiPlayer tests stock
			storedTests.add(guessCombination);
		}
		
	}
	
	/**
	 * check consistency between a combination test value and the given response
	 * 
	 * @param combination
	 */
	@Override
	public void checkGivenResponse(Combination combination) {
		int[] test = combination.getGuessValue();
		String[] responseValue = combination.getResponseValue();
		for (int i = 0; i < test.length; i++) {
			if (responseValue[i].equals("-")) {
				if(test[i] == 0 ) {
					throw new InvalidGameLapItemException("Réponse incohérente : le " + (i+1) + "ème chiffre proposé est 0, la réponse ne peut pas être \"-\".");
				} else {
					int max = test[i];
					int min = getMinLimit(i, max);
					if(min == max) {
						String message = "Réponse incohérente pour le " + (i+1) + "ème chiffre : réponse donnée par IA = " + test[i] + ", réponse donnée par joueur : - "
								+ " alors que le dernier plus grand chiffre inférieur à " +  test[i] + " était " + (min-1);
						throw new InvalidGameLapItemException(message);
					}
				}
			} else if (responseValue[i].equals("+")) {
				if(test[i] == 9 ) {
					throw new InvalidGameLapItemException("Réponse incohérente : le "+ (i+1) + "ème chiffre proposé est 0, la réponse ne peut pas être \"-\".");
				} else {
					int min = test[i] + 1 ;
					int max = getMaxLimit(i, test[i]);
					if(min==max) {
						String message = "Réponse incohérente pour le " + (i+1) + "ème chiffre : réponse donnée par IA = " + test[i] + ", réponse donnée par joueur : + "
								+ " alors que le dernier plus petit chiffre supérieur à " +  test[i] + " était " + (max);
						throw new InvalidGameLapItemException(message);
					}
				}
			}
		}
	}

	
	
	/**
	 * Create combination with specified values
	 * @param length
	 * @return
	 */
	private int[] giveInitTest(int size) {
		int[] combination = new int[size];
		for(int i = 0; i < size; i++) {
			combination[i] = 5;
		}
		
		return combination;
	}

	/**
	 * Define a value for combination test from indications 
	 * @param combinationToFind
	 */
	private int[] giveTestFromRules(Combination combinationToFind) {
		int[] guessCombination = new int[combinationToFind.getLength()];
		//get last proposition
		int[] lastTest = storedTests.get(storedTests.size() - 1);
		//get response from player
		String[] responseValue = combinationToFind.getResponseValue();
		for (int i = 0; i < lastTest.length; i++) {
			if(responseValue[i].equals("=") ) {
				guessCombination[i] = lastTest[i];
			} else if (responseValue[i].equals("-")) {
				int max = lastTest[i];
				int min = getMinLimit(i, max);
				guessCombination[i] = Arithmetic.getRandomNumber(min, max);
				
			} else if (responseValue[i].equals("+")) {
				int min = lastTest[i] + 1 ;
				int max = getMaxLimit(i, lastTest[i]);
				guessCombination[i] =  Arithmetic.getRandomNumber(min, max);
			}
					
		}
		return guessCombination;
	}

	/**
	 * Get minimum limit to set a random number for combination
	 * 
	 * @param positionNumber		position of number in combination
	 * @return
	 */
	private int getMinLimit(int positionNumber, int maxValue) {
		int min;
		
		//retrieves all values tested for number with positionNumber in combination
		ArrayList<Integer> testValuesForPosition = getValuesForPositionLowerMax(positionNumber, maxValue);
		
		if(testValuesForPosition.isEmpty()) {
			min = 0;
		} else {
			min = Arithmetic.getMaxNumber(testValuesForPosition) + 1;
		}
		
		return min;
	}
	
	/**
	 * Retrieve all tested values lower than a maximum value for a number position in combination
	 * 
	 * @param 	position		the position of number in combination
	 * @param	maxValue		the maximum limit
	 * @return
	 */
	private ArrayList<Integer> getValuesForPositionLowerMax(int position, int maxLimit) {
		ArrayList<Integer> testValuesForPosition = new ArrayList<Integer>();
		for(int i=0; i < storedTests.size(); i++) {
			int[] combination = storedTests.get(i);
			if( combination[position] < maxLimit) {
				testValuesForPosition.add(combination[position]);
			}
		}
		
		return testValuesForPosition;
	}
	
	
	/**
	 * Get maximum limit to set a random number for combination
	 * 
	 * @param positionNumber		position of number in combination
	 * @return
	 */
	private int getMaxLimit(int positionNumber, int minValue) {
		int max;
		
		//retrieves all values tested for number with positionNumber in combination
		ArrayList<Integer> testValuesForPosition = getValuesForPositionGreaterMin(positionNumber, minValue);
		
		if(testValuesForPosition.isEmpty()) {
			max = 10;
		} else {
			max = Arithmetic.getMinNumber(testValuesForPosition);
		}
		
		return max;
	}
	
	
	/**
	 * Retrieve all tested values greater than a minimum value for a number position in combination
	 * 
	 * @param	position		the position of number in combination
	 * @param	minValue		the minimum limit
	 * @return
	 */
	private ArrayList<Integer> getValuesForPositionGreaterMin(int position, int minValue) {
		ArrayList<Integer> testValuesForPosition = new ArrayList<Integer>();
		for(int i=0; i < storedTests.size(); i++) {
			int[] combination = storedTests.get(i);
			if( combination[position] > minValue) {
				testValuesForPosition.add(combination[position]);
			}
		}
		return testValuesForPosition;
	}

	
	
	
	
	
	
	
	


	

}
