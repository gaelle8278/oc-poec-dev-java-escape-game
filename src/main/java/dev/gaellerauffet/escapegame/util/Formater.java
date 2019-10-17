package dev.gaellerauffet.escapegame.util;

public class Formater {
	/**
	 * Concatenate value from integer array to string
	 * @return
	 */
	public final static String arrayToString(int[] intArray) {
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
	public final static String arrayToString(String[] stringArray) {
		String value = "";
		for(int i=0; i < stringArray.length; i++ ) {
			value += stringArray[i];
		}
		return value;
	}

	
}
