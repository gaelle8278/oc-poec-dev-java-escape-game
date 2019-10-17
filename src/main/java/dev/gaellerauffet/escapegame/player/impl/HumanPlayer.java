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
		//human set value in his head, so value is set with invalid number (ie -1)
		int[] combinationValue = new int[combination.getLength()];
		//human player set value of combination in his head
		for (int i=0; i < combination.getLength(); i++) {
			combinationValue[i] = -1;
			
		}
		
		combination.setValue(combinationValue);
	}

	@Override
	public void checkConsistentResponse(Combination combination) {
		//human not check consistency of ai response he has confidence :-)
		
	}
	
	
	
}
