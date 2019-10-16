package dev.gaellerauffet.escapegame.player.impl;

import java.util.Scanner;

import dev.gaellerauffet.escapegame.game.Combination;
import dev.gaellerauffet.escapegame.player.Player;

public class HumanPlayer implements Player{
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

	@Override
	public void setValueCombination(Combination combination) {
		//human player set value of combination in his head
	}
	
}
