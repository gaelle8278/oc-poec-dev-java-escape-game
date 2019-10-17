package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.exceptions.InvalidTestException;
import dev.gaellerauffet.escapegame.game.Combination;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;

public class ChallengerMode implements Mode {
	private int modeDev;
	private int nbTests;
	private Player humanPlayer;
	private Player aiPlayer;
	private Combination combinationAiPlayer;
	private DisplayMessage displayMsg = new DisplayMessage();
	private LogMessage logMsg = new LogMessage();

	public ChallengerMode(Player aiPlayer, Player humanPlayer, int combinationLength, int nbTests, int modeDev) {
		this.nbTests = nbTests;
		this.modeDev = modeDev;
		this.humanPlayer = humanPlayer;
		this.aiPlayer = aiPlayer;
		this.combinationAiPlayer = new Combination(combinationLength); 
	}
	
	@Override
	public void run() {
		displayMsg.infoLine("Démarrage mode Challengeur");
		logMsg.infoLine("Mode de jeu : Challengeur");
		
		//1- the playerA set the value for combination
		aiPlayer.setValueCombination(combinationAiPlayer);
		
		
		String strCombination = Formater.arrayToString(combinationAiPlayer.getValue());
		// display value of combination if necessary
		if(modeDev == 1) {
			displayMsg.infoLine("(combinaison secrète : " + strCombination + ")");
		}
		// log value of combination
		logMsg.infoLine("combinaison définit par l'IA : " + strCombination);
		
		//3- humanPlayer try to guess the combination = until the answer is good or there is no more test
		boolean responseIsGood = false;
		int currentTest = 0;
		while(currentTest < nbTests && !responseIsGood) {
			try {
				runSet(currentTest);
				responseIsGood = combinationAiPlayer.checkTest();
				currentTest++;
			} catch (InvalidTestException e) {
				displayMsg.errorLine(e.getMessage());
				logMsg.errorLine("essai " + (currentTest + 1) + " " + e.getMessage());
			}
			
		}
		
		if(responseIsGood) {
			displayMsg.infoLine("Combinaison trouvée ! Le joueur a gagné.");
			logMsg.infoLine("Fin de partie mode Challenger : le joueur a gagné. Il a trouvé la combinaison.");
		} else {
			displayMsg.infoLine("Le joueur a perdu. La combinaison était : " +  strCombination);
			logMsg.infoLine("Fin de partie mode Challenger : le joueur a perdu. Il n'a pas trouvé la combinaison.");
		}
		
	}
	
	
	private void runSet(int currentTest) {
		displayMsg.info("Votre propositon (combinaison à " + combinationAiPlayer.getLength() + " chiffres) - " + (nbTests - currentTest) + " essai(s) restant(s) : ");
		humanPlayer.guessCombination(combinationAiPlayer);
			
		aiPlayer.checkCombination(combinationAiPlayer);
		displayMsg.infoLine(" -> réponse de l'IA : " + Formater.arrayToString(combinationAiPlayer.getResponseValue()));
			
		//log 
		logMsg.infoLine("essai " + (currentTest + 1) + " combinaison donnée par le joueur : " + Formater.arrayToString(combinationAiPlayer.getGuessValue()) 
					+ " / Réponse faites par l'IA " + Formater.arrayToString(combinationAiPlayer.getResponseValue()));
			
		
	}

}
