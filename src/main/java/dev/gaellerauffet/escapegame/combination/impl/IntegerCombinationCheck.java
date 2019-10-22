package dev.gaellerauffet.escapegame.combination.impl;

import dev.gaellerauffet.escapegame.combination.CombinationCheck;
import dev.gaellerauffet.escapegame.exceptions.impl.InvalidResponseException;
import dev.gaellerauffet.escapegame.exceptions.impl.InvalidTestException;

public class IntegerCombinationCheck implements CombinationCheck {

	/**
	 * Check if a given test value has the right length
	 */
	@Override
	public void checkLengthTestValue(int length, String testValue) {
		if(testValue.length() != length) {
			throw new InvalidTestException("La combinaison proposée doit contenir " + length + " chiffres.");
		} 
		
	}

	/**
	 * Check if a combination test value contains only integer between 0 to 9
	 * @param combination
	 * @return
	 */
	@Override
	public void checkContentTestValue(int[] testValue) {
		for(int i=0; i < testValue.length; i++) {
			// check value between 0 to 9
			if(testValue[i] < 0 || testValue[i] >= 10) {
				throw new InvalidTestException("La combinaison proposée ne doit contenir que des entiers compris entre 0 et 9.");
			} 
		} 
	}
	
	/**
	 * Check if the length of given response is equals to the length of combination
	 * @param strCombination
	 */
	@Override
	public void checkLengthResponseValue(int length, String responseValue) {
		if(responseValue.length() != length) {
			throw new InvalidResponseException("La réponse proposée doit contenir " + length + " caractères (parmi +, - et =).");
		} 
		 
	}

	/**
	 * Check if response to a combination value contains only character among +, - and =
	 *
	 * @return
	 */
	@Override
	public void checkContentResponseValue(String[] responseValue) {
		for(int i=0; i < responseValue.length; i++) {
			// check character among +, - and =
			if(!responseValue[i].equals("-") && !responseValue[i].equals("+") && !responseValue[i].equals("=")) {
				// throw Exception
				throw new InvalidResponseException("La réponse proposée ne doit contenir que des caractères parmi +, - et =.");
			} 
			
		} 
	}

}
