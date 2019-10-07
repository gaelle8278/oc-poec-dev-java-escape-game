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
	 * Set the game combination from a string
	 * @param strCombination
	 */
	public void setGuessTestValueFromString(String strCombination) {	
		this.checkLengthGuessValue(strCombination);
		for(int i=0; i < strCombination.length(); i++) {
			if(Character.isDigit(strCombination.charAt(i))) {
				this.guessValue[i] = Character.getNumericValue(strCombination.charAt(i));
			} else {
				this.guessValue[i] = -1;
			}
		}	
		
		this.checkContentGuessValue();
	}
	
	/**
	 * Set the response value from a response string
	 * @param userResponse
	 */
	public void setResponseValueFromString(String strResponse) {
		this.checkLengthResponseValue(strResponse);
		for(int i=0; i < strResponse.length(); i++) {
			this.responseValue[i] = String.valueOf(strResponse.charAt(i));
		}
		this.checkContentResponseValue();
		
		
	}
	
	/**
	 * Check if the length of given guess combination is equals to the length of combination
	 * @param strCombination
	 */
	private void checkLengthGuessValue(String strCombination) {
		if(strCombination.length() != length) {
			throw new IllegalCombinationItem("La combinaison proposée doit contenir " + length + " chiffres.");
		}
		 
	}
	
	/**
	 * Check if the length of given response is equals to the length of combination
	 * @param strCombination
	 */
	private void checkLengthResponseValue(String strResponse) {
		if(strResponse.length() != length) {
			throw new IllegalCombinationItem("La réponse proposée doit contenir " + length + " caractères parmi +, - et =.");
		}
		 
	}

	/**
	 * Check if guess combination value contains only integer between 0 to 9
	 * @param combination
	 * @return
	 */
	private void checkContentGuessValue() {
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
	 * Check if response to a combination value contains only character among +, - and =
	 *
	 * @return
	 */
	private void checkContentResponseValue() {
		//check length of combination
		for(int i=0; i < length; i++) {
			// check character among +, - and =
			if(!responseValue[i].equals("-") && !responseValue[i].equals("+") && !responseValue[i].equals("=")) {
				// throw Exception
				throw new IllegalCombinationItem("La réponse proposée ne doit contenir que des cacartères parmi +, - et =.");
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
