package dev.gaelle_rauffet.escape_game;

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
	 * Set the game combination
	 * @param strCombination
	 */
	public void setGuessTestValueFromString(String strCombination) {	
		for(int i=0; i < strCombination.length(); i++) {
			if(Character.isDigit(strCombination.charAt(i))) {
				this.guessValue[i] = Character.getNumericValue(strCombination.charAt(i));
			} else {
				this.guessValue[i] = -1;
			}
		}	
	}
	
	
	/**
	 * Check if guess combination value contains only integer between 0 to 9
	 * @param combination
	 * @return
	 */
	public void checkGuessTestValue() {
		//check length of combination
		for(int i=0; i < length; i++) {
			// check value between 0 to 9
			if(guessValue[i] < 0 || guessValue[i] >= 10) {
				// throw Exception
				throw new IllegalCombinationItem("La combinaison proposée ne doit contenir que des entiers compris entre 0 et 9.");
			} 
			
		} 
	}
	
	/**
	 * Check if the test to guess combination value is good or not
	 * @return
	 */
	public boolean checkTest() {
		boolean check = true;
		for(int i = 0; i < responseValue.length; i++) {
			if(responseValue[i] != "=") {
				check = false;
				break;
			}
		}
		return check;
	}
	
	
	/**
	 * Concatenate value to string integer array
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
	 * Concatenate value to string from string array
	 * @return
	 */
	public String valueToString(String[] stringArray) {
		String value = "";
		for(int i=0; i < stringArray.length; i++ ) {
			value += stringArray[i];
		}
		return value;
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

	public void setValue(int[] combinationValue) {
		this.value = combinationValue;
		
	}

	public int[] getGuessValue() {
		return this.guessValue;
	}

	public void setResponseValue(String[] responseValue) {
		this.responseValue = responseValue;
		
	}

	public String[] getResponseValue() {
		return this.responseValue;
	}

	

	

}
