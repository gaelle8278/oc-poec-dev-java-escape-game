package dev.gaelle_rauffet.escape_game;

import java.util.Random;

/**
 * 
 * @author gaelle
 *
 */
public class AI implements Player {
	/**
	 * Define a combination of a given size
	 * 
	 * @param size
	 * @return
	 */
	public int[] setCombination(int size) {
		int[] combination = new int[size];
		for(int i = 0; i < size; i++) {
			combination[i] = setRandomNumber();
		}
		return combination;
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

	/**
	 * Compare to combination
	 * 
	 * @param combinationTest			combination test
	 * @param combinationToFind			combination reference, the combination to find
	 * @return 
	 */
	@Override
	public String[] checkCombination(int[] combinationTest, int[] combinationToFind) {
		//check both arrays have the same size ?
		String[] response = new String[combinationToFind.length];
		
		for (int i=0; i < combinationToFind.length; i++) {
			if(combinationTest[i] == combinationToFind[i]) {
				response[i] = "=";
			} else if (combinationTest[i] > combinationToFind[i]) {
				response[i] = "-";
			} else {
				response[i] = "+";
			}
		}
		
		return response;
		
	}


	@Override
	public int[] guessCombination(String[] indications,  int size) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
