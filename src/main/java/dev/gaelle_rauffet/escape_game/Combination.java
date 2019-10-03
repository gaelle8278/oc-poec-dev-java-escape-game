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

	/*public void setCombinationFromString(String strCombination) {	
		for(int i=0; i < strCombination.length(); i++) {
			if(Character.isDigit(strCombination.charAt(i))) {
				this.value[i] = Character.getNumericValue(strCombination.charAt(i));
			} else {
				this.value[i] = -1;
			}
		}
	}*/
	
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
	 * Check if combination value contains only integer between 0 to 9
	 * @param combination
	 * @return
	 */
	/*public void checkCombinationContainsValidInt() {
		//check length of combination
		for(int i=0; i < length; i++) {
			// check value between 0 to 9
			if(value[i] < 0 || value[i] >= 10) {
				// throw Exception
				throw new IllegalCombinationItem("La combinaison ne doit contenir que des entiers compris entre 0 et 9.");
			} 
			
		} 
	}*/
	
	/**
	 * Check if combination value contains only integer between 0 to 9
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
	 * Check if test to guess combination valkue is good or not
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
	 * Concatenate value to string
	 * @return
	 */
	public String valueToString(int[] intArray) {
		String value = intArrayToString(intArray);
		return value;
		
	}
	
	/**
	 * Concatenate value to string
	 * @return
	 */
	public String valueToString(String[] stringArray) {
		String value = stringArrayToString(stringArray);
		return value;
	}
	
	/**
	 * Concatenate Integer array to a string
	 * 
	 * @return
	 */
	private String intArrayToString(int[] intArrayValue) {
		String strCombination = "";
		for(int i=0; i < intArrayValue.length; i++ ) {
			strCombination += String.valueOf(intArrayValue[i]);
		}
		
		return strCombination;
	}
	
	/**
	 * Concatenate String array to a string
	 * 
	 * @return
	 */
	private String stringArrayToString(String[] stringArrayValue) {
		String strCombination = "";
		for(int i=0; i < stringArrayValue.length; i++ ) {
			strCombination += stringArrayValue[i];
		}
		
		return strCombination;
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
