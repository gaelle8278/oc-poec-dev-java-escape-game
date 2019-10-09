package dev.gaellerauffet.escapegame;

import java.util.Scanner;

public class Human implements Player{
	private Scanner sc = new Scanner(System.in); 
	
	


	@Override
	public void checkCombination(Combination combinationToFind) {
		String userResponse = sc.nextLine();
		combinationToFind.setResponseValueFromString(userResponse);
		
	}

	@Override
	public void guessCombination(Combination combinationToFind) {
	    String userResponse = sc.nextLine();
	    combinationToFind.setGuessTestValueFromString(userResponse);
		
	}

	
}
