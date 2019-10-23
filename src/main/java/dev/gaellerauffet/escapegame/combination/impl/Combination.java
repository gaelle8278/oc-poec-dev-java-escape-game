package dev.gaellerauffet.escapegame.combination.impl;

import dev.gaellerauffet.escapegame.combination.CombinationCheck;

public class Combination {
	private int length;
	private int[] value;
	private int[] guessValue;
	private String[] responseValue;
	private CombinationCheck combinationCheck;
	
	public Combination(int length) {
		this.length = length;
		this.value = new int[length];
		this.guessValue = new int[length];
		this.responseValue = new String[length];
		this.combinationCheck = new IntegerCombinationCheck();
	}
	
	/**
	 * Set the game combination from a string
	 * @param strValue
	 */
	public void setTestValueFromString(String strValue) {	
		combinationCheck.checkLengthTestValue(length, strValue);
	
		for(int i=0; i < strValue.length(); i++) {
			if(Character.isDigit(strValue.charAt(i))) {
				this.guessValue[i] = Character.getNumericValue(strValue.charAt(i));
			} else {
				this.guessValue[i] = -1;
			}
		} 
		
		combinationCheck.checkContentTestValue(guessValue);
	}
	
	/**
	 * Set the response value from a response string
	 * @param userResponse
	 */
	public void setResponseValueFromString(String strResponse) {
		combinationCheck.checkLengthResponseValue(length, strResponse);
		
		for(int i=0; i < strResponse.length(); i++) {
			this.responseValue[i] = String.valueOf(strResponse.charAt(i));
		}
		
		combinationCheck.checkContentResponseValue(responseValue);
		
	}
	
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
