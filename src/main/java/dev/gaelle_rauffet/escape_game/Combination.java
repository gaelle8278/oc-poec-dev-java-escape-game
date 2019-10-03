package dev.gaelle_rauffet.escape_game;

public class Combination {
	private int length;
	private int[] value;
	private Message msgManager = new Message();
	
	public Combination(int length) {
		this.length = length;
		this.value = new int[length];
	}

	public void setCombinationFromString(String strCombination) {	
		for(int i=0; i < strCombination.length(); i++) {
			if(Character.isDigit(strCombination.charAt(i))) {
				this.value[i] = Character.getNumericValue(strCombination.charAt(i));
			} else {
				this.value[i] = -1;
			}
		}
	}
	
	/**
	 * Check if combination value contains only integer between 0 to 9
	 * @param combination
	 * @return
	 */
	public void checkCombinationContainsValidInt() {
		//check length of combination
		for(int i=0; i < length; i++) {
			// check value between 0 to 9
			if(value[i] < 0 || value[i] >= 10) {
				// throw Exception
				throw new IllegalCombinationItem("La combinaison ne doit contenir que des entiers compris entre 0 et 9.");
			} 
			
		} 
	}
	/**
	 * Concatenate combination value to astring
	 * 
	 * @return
	 */
	public String valueToString() {
		String strCombination = "";
		for(int i=0; i < length; i++ ) {
			strCombination += String.valueOf(value[i]);
		}
		
		return strCombination;
	}
	
	public int[] getValue() {
		return this.value;
	}
}
