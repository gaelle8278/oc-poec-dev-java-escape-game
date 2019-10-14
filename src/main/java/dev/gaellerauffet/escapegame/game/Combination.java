package dev.gaellerauffet.escapegame.game;

import dev.gaellerauffet.escapegame.exceptions.IllegalItemException;

public class Combination {
	private int length;
	private int[] value;
	private int[] guessValue;
	private String[] responseValue;
	
	public Combination(int length) {
		this.length = length;
		this.value = new int[length];
		this.guessValue = new int[length];
		this.responseValue = new String[length];
	}
	
	/**
	 * Set the game combination from a string
	 * @param strCombination
	 */
	public void setGuessTestValueFromString(String strCombination) {	
		this.checkLengthGuessValue(strCombination);
		try {
			for(int i=0; i < strCombination.length(); i++) {
				if(Character.isDigit(strCombination.charAt(i))) {
					this.guessValue[i] = Character.getNumericValue(strCombination.charAt(i));
				} else {
					this.guessValue[i] = -1;
				}
			} 
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalItemException("La combinaison proposée doit contenir " + length + " chiffres.");
		}	
		
		this.checkContentGuessValue();
	}
	
	/**
	 * Set the response value from a response string
	 * @param userResponse
	 */
	public void setResponseValueFromString(String strResponse) {
		this.checkLengthResponseValue(strResponse);
		try {
			for(int i=0; i < strResponse.length(); i++) {
				this.responseValue[i] = String.valueOf(strResponse.charAt(i));
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalItemException("La réponse proposée doit contenir " + length + " caractères (parmi +, - et =).");
		}
		this.checkContentResponseValue();
		
		
	}
	

	/**
	 * Check if the length of given guess combination is equals to the length of combination
	 * @param strCombination
	 */
	private void checkLengthGuessValue(String strCombination) {
		if(strCombination.length() != length) {
			throw new IllegalItemException("La combinaison proposée doit contenir " + length + " chiffres.");
		} 
		 
	}
	
	/**
	 * Check if the length of given response is equals to the length of combination
	 * @param strCombination
	 */
	private void checkLengthResponseValue(String strResponse) {
		if(strResponse.length() != length) {
			throw new IllegalItemException("La réponse proposée doit contenir " + length + " caractères (parmi +, - et =).");
		} 
		 
	}

	/**
	 * Check if guess combination value contains only integer between 0 to 9
	 * @param combination
	 * @return
	 */
	private void checkContentGuessValue() {
		for(int i=0; i < length; i++) {
			// check value between 0 to 9
			if(guessValue[i] < 0 || guessValue[i] >= 10) {
				// throw Exception
				throw new IllegalItemException("La combinaison proposée ne doit contenir que des entiers compris entre 0 et 9.");
			} 
			
		} 
	}
	
	/**
	 * Check if response to a combination value contains only character among +, - and =
	 *
	 * @return
	 */
	private void checkContentResponseValue() {
		for(int i=0; i < length; i++) {
			// check character among +, - and =
			if(!responseValue[i].equals("-") && !responseValue[i].equals("+") && !responseValue[i].equals("=")) {
				// throw Exception
				throw new IllegalItemException("La réponse proposée ne doit contenir que des caractères parmi +, - et =.");
			} 
			
		} 
	}
	
	/**
	 * Check consistency between given combination value and response
	 */
	/*private void checkConsistencyResponseValue() {
		//for each number of a a given guess value
		for (int i = 0; i < guessValue.length; i++) {
			if(guessValue[i] == 0 && responseValue[i].equals("-")) {
				//check consistency between - and 0
				throw new IllegalItemException("Réponse incohérente : le "+ (i+1) + "ème chiffre proposé est 0, la réponse ne peut pas être \"-\".");
			} else if (guessValue[i] == 9 && responseValue[i].equals("+")) {
				//check consistency between + and 9
				throw new IllegalItemException("Réponse incohérente : le "+ (i+1) + "ème chiffre proposé est 9, la réponse ne peut pas être \"+\".");
			}
		}
		
	}*/
	
	/**
	 * Check if the test to guess combination value is good or not
	 * @return
	 */
	public boolean checkTest() {
		boolean check = true;
		for(int i = 0; i < responseValue.length; i++) {
			if(!responseValue[i].equals("=")) {
				check = false;
				break;
			}
		}
		return check;
	}
	
	
	/**
	 * Concatenate value from integer array to string
	 * @return
	 */
	public String valueToString(int[] intArray) {
		String value = "";
		for(int i=0; i < intArray.length; i++ ) {
			value += String.valueOf(intArray[i]);
		}
		return value;
		
	}
	
	/**
	 * Concatenate value from string array to string
	 * @return
	 */
	public String valueToString(String[] stringArray) {
		String value = "";
		for(int i=0; i < stringArray.length; i++ ) {
			value += stringArray[i];
		}
		return value;
	}
	
	/**
	 * Check if a response is set
	 * @return
	 */
	public boolean responseIsNull() {
		boolean check = false;
		for(int i=0; i < responseValue.length; i++) {
			if(responseValue[i] == null) {
				check = true;
				break;
			}
		}
		return check;
	}
	
	/**
	 * Reset the responseValue
	 * 
	 * Set a new String array with null values
	 */
	public void resetResponseValue() {
		this.responseValue = new String[length];
		
	}
	
	/*
	 * Getters and Setters
	 * ****************************** */
	public int[] getValue() {
		return this.value;
	}

	public int getLength() {
		return this.length;
	}
	
	public int[] getGuessValue() {
		return this.guessValue;
	}
	
	public String[] getResponseValue() {
		return this.responseValue;
	}

	public void setValue(int[] combinationValue) {
		this.value = combinationValue;
		
	}

	public void setGuessValue(int[] guessValue) {
		this.guessValue = guessValue;
	}
	
	public void setResponseValue(String[] responseValue) {
		this.responseValue = responseValue;
		
	}

	

	

	

	

	

	

}
