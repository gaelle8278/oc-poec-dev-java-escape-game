package dev.gaelle_rauffet.escape_game;

import java.util.Scanner;

public class Human implements Player{
	public Scanner sc = new Scanner(System.in);
	
	@Override
	public int[] guessCombination(String[] indications, int size) {
		System.out.println("Votre propositon (combinaison à " + size + " chiffres) :");
	    String userResponse = sc.nextLine();
		
	    int[] combination = this.convertStrCombinationToIntArray(userResponse);
		
	    return combination;
		
	}


	@Override
	public String[] checkCombination(int[] combinationTest, int[] combinationReference) {
		// TODO Auto-generated method stub
		return null;
	}

	private int[] convertStrCombinationToIntArray(String strCombination) {
		int combinationSize = strCombination.length();
		int[] tabCombination = new int[combinationSize];
		
		for(int i=0; i < combinationSize; i++) {
			if(Character.isDigit(strCombination.charAt(i))) {
				tabCombination[i] = Character.getNumericValue(strCombination.charAt(i));
			} else {
				tabCombination[i] = -1;
			}
		}
		
		return tabCombination;
	}
}
