package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.combination.impl.Combination;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;
import dev.gaellerauffet.escapegame.util.Parameter;

public class DefenderMode extends Mode {

	public DefenderMode(Player aiPlayer, Player humanPlayer, Combination HumanCombination, int nbTests , int modeDev) {
		this.nbTests = nbTests;
		this.modeDev = modeDev;
		this.humanPlayer = humanPlayer;
		this.aiPlayer = aiPlayer;
		this.combination = HumanCombination; 
	}


	@Override
	protected void displayStartMsg() {
		displayMsg.infoLine("Démarrage mode Défenseur");
		logMsg.infoLine("Mode de jeu : Défenseur");
		
	}

	@Override
	protected void displayEndMsg(int resultGame) {
		if(resultGame == 2) {
			displayMsg.infoLine("Combinaison trouvée ! L'IA a gagné.");
			logMsg.infoLine("Fin de partie mode Défenseur : l'IA a trouvé la combinaison, le joueur a perdu.");
		} else {
			displayMsg.infoLine(" L'IA a perdu, elle n'a pas trouvé la combinaison.");
			logMsg.infoLine("Fin de partie mode Défenseur :  l'IA n'a pas trouvé la combinaison.");
		}
	}

	@Override
	public void initMode() {
		//human set value of combination in his head
		
	}

	@Override
	public void displayMsgBeforeRun() {
		if(modeDev == 1) {
			displayMsg.infoLine("(combinaison secrète : définie par le joueur)");
		}
		logMsg.infoLine("combinaison définit par le joueur et connue de lui seul");
		
	}

	@Override
	public void logLap(int currentLap) {
		logMsg.infoLine("essai " + (currentLap + 1) + " combinaison donnée par l'IA : " +  Formater.arrayToString(combination.getGuessValue()) 
		+ " / Réponse faites par le joueur " +  Formater.arrayToString(combination.getResponseValue()));
		
	}

	@Override
	public int getLapResult() {
		int result = Parameter.NO_WINNER;
		boolean hasAIFoundCombination = combination.checkTest();
		if(hasAIFoundCombination) {
			result = Parameter.WINNER_IS_AI;
		}
		return result;
	}

	@Override
	protected void askTest(int currentLap) {
		aiPlayer.giveTest(combination);
		displayMsg.infoLine("Proposition de l'IA - " + (nbTests - currentLap) + " essai(s) restant(s) : " + Formater.arrayToString(combination.getGuessValue()));
		
	}

	@Override
	protected void askResponse() {
		displayMsg.info(" -> votre réponse : ");
		humanPlayer.giveResponse(combination);
		aiPlayer.checkGivenResponse(combination);
		
	}

	@Override
	protected void executeActionsIfError() {
		//in the defender mode, if human set an invalid response the combination response must be reset 
		//so that AI does not a new test based on a wrong answer
		combination.resetResponseValue();
	}


}
