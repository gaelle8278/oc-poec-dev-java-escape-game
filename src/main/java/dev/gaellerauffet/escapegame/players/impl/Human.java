package dev.gaellerauffet.escapegame.players.impl;

import java.util.Scanner;

import dev.gaellerauffet.escapegame.game.Combination;
import dev.gaellerauffet.escapegame.players.Player;

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
