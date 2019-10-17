package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.combination.impl.Combination;
import dev.gaellerauffet.escapegame.exceptions.InvalidTestException;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;

public class ChallengerMode extends Mode {

	public ChallengerMode(Player aiPlayer, Player humanPlayer, int combinationLength, int nbTests, int modeDev) {
		this.nbTests = nbTests;
		this.modeDev = modeDev;
		this.humanPlayer = humanPlayer;
		this.aiPlayer = aiPlayer;
		this.combinationAiPlayer = new Combination(combinationLength); 
	}
	
	@Override
	protected void displayStartMsg() {
		displayMsg.infoLine("Démarrage mode : mode Challenger" );
		logMsg.infoLine("Démarrage mode de jeu : Challenger");
	}
	
	@Override
	protected void displayEndMsg(boolean resultGame) {
		if(resultGame) {
			displayMsg.infoLine("Combinaison trouvée ! Le joueur a gagné.");
			logMsg.infoLine("Fin de partie mode Challenger : le joueur a gagné. Il a trouvé la combinaison.");
		} else {
			displayMsg.infoLine("Le joueur a perdu. La combinaison était : " +  Formater.arrayToString(combinationAiPlayer.getValue()));
			logMsg.infoLine("Fin de partie mode Challenger : le joueur a perdu. Il n'a pas trouvé la combinaison.");
		}
	}
	
	@Override
	protected void initMode() {
		aiPlayer.giveCombinationValue(combinationAiPlayer);
		
	}
	
	@Override
	protected void displayMsgBeforeRun() {
		String combinationValue = Formater.arrayToString(combinationAiPlayer.getValue());
		if(modeDev == 1) {
			displayMsg.infoLine("(combinaison secrète : " + combinationValue);
		}
		logMsg.infoLine("combinaison définie par l'IA = " + combinationValue);
		
	}
	
	@Override
	protected void runLap(int currentLap) {
		boolean validHumanTest = false;
		while(!validHumanTest) {
			try {
				askATestToHuman(currentLap);
				askAResponseToAi();
				validHumanTest = true;
			} catch (InvalidTestException e) {
				displayMsg.errorLine(e.getMessage());
				logMsg.errorLine("essai " + (currentLap + 1) + " " + e.getMessage());
			}
		}
	}

	@Override
	protected void logLap(int currentLap) {
		logMsg.infoLine("essai " + (currentLap + 1) + " combinaison donnée par le joueur : " + Formater.arrayToString(combinationAiPlayer.getGuessValue()) 
		+ " / Réponse faites par l'IA " + Formater.arrayToString(combinationAiPlayer.getResponseValue()));
		
	}

	@Override
	protected boolean checkLapResult() {
		return combinationAiPlayer.checkTest();
	}

}
