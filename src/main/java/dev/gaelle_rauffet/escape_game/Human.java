package dev.gaelle_rauffet.escape_game;

import java.util.Scanner;

public class Human implements Player{
	public Scanner sc = new Scanner(System.in);
	
	@Override
	public String guessCombination(int size) {
		System.out.println("Votre propositon (combinaison à " + size + " chiffres) :");
	    String response = sc.nextLine();
		
		return response;
		
	}


	@Override
	public String[] checkCombination(int[] combinationTest, int[] combinationReference) {
		// TODO Auto-generated method stub
		return null;
	}

}
