package dev.gaellerauffet.escapegame.combination;

public interface CombinationCheck {
	
	void checkLengthTestValue(int length, String testValue);
	
	void checkContentTestValue(int[] testValue);

	void checkLengthResponseValue(int length, String strResponse);

	void checkContentResponseValue(String[] responseValue);

}
