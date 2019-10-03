package dev.gaelle_rauffet.escape_game;

import java.util.Scanner;

public class Human implements Player{
	private Scanner sc = new Scanner(System.in); 
	private Message msgManager = new Message();
	
	


	@Override
	public void checkCombination(Combination combinationTest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guessCombination(Combination combinationToFind) {
		msgManager.printInfo("Votre propositon (combinaison à " + combinationToFind.getLength() + " chiffres) :");
	    String userResponse = sc.nextLine();
	    
	    combinationToFind.setGuessTestValueFromString(userResponse);
		
	}

	
}
