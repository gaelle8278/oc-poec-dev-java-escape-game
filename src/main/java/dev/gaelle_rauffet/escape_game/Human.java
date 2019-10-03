package dev.gaelle_rauffet.escape_game;

import java.util.Scanner;

public class Human implements Player{
	private Scanner sc = new Scanner(System.in); 
	private Message msgManager = new Message();
	
	
	/*public Combination guessCombination(String[] indications, int size) {
		
	    
	    Combination proposedCombination  = new Combination(size);
	  
	    proposedCombination.setCombinationFromString(userResponse);
	    proposedCombination.checkCombinationContainsValidInt();
	   
	    
	    //int[] combination = this.convertStrCombinationToIntArray(userResponse);
	    
	    return proposedCombination;
		
	}*/


	@Override
	public String[] checkCombination(Combination combinationTest, int[] combinationReference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guessCombination(Combination combinationToFind) {
		msgManager.printInfo("Votre propositon (combinaison à " + combinationToFind.getLength() + " chiffres) :");
	    String userResponse = sc.nextLine();
	    
	    combinationToFind.setGuessTestValueFromString(userResponse);
	    combinationToFind.checkGuessTestValue();
		
	}

	
}
