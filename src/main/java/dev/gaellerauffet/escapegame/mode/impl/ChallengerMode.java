package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.combination.impl.Combination;
import dev.gaellerauffet.escapegame.exceptions.InvalidTestException;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;
import dev.gaellerauffet.escapegame.util.Parameter;

public class ChallengerMode extends Mode {

	public ChallengerMode(Player aiPlayer, Player humanPlayer, Combination AiCombination, int nbTests, int modeDev) {
		this.nbTests = nbTests;
		this.modeDev = modeDev;
		this.humanPlayer = humanPlayer;
		this.aiPlayer = aiPlayer;
		this.combinationAiPlayer = AiCombination; 
	}
	
	@Override
	protected void displayStartMsg() {
		displayMsg.infoLine("Démarrage mode : mode Challenger" );
		logMsg.infoLine("Démarrage mode de jeu : Challenger");
	}
	
	@Override
	protected void displayEndMsg(int resultGame) {
		if(resultGame == 1) {
			displayMsg.infoLine("Combinaison trouvée ! Le joueur a gagné.");
			logMsg.infoLine("Fin de partie mode Challenger : le joueur a gagné. Il a trouvé la combinaison.");
		} else  {
			displayMsg.infoLine("Le joueur n'a pas trouvée la combinaison. La combinaison était : " +  Formater.arrayToString(combinationAiPlayer.getValue()));
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
	protected void logLap(int currentLap) {
		logMsg.infoLine("essai " + (currentLap + 1) + " combinaison donnée par le joueur : " + Formater.arrayToString(combinationAiPlayer.getGuessValue()) 
		+ " / Réponse faites par l'IA " + Formater.arrayToString(combinationAiPlayer.getResponseValue()));
		
	}

	@Override
	protected int getLapResult() {
		int result = Parameter.NO_WINNER;
		boolean hasHumanFoundCombination = combinationAiPlayer.checkTest();
		if(hasHumanFoundCombination) {
			result = Parameter.WINNER_IS_HUMAN;
		} 
		
		return result;
	}

	@Override
	protected void askTest(int currentLap) {
		displayMsg.info("Votre propositon (combinaison à " + combinationAiPlayer.getLength() + " chiffres) - " + (nbTests - currentLap) + " essai(s) restant(s) : ");
		humanPlayer.giveTest(combinationAiPlayer);
	}

	@Override
	protected void askResponse() {		
		aiPlayer.giveResponse(combinationAiPlayer);
		displayMsg.infoLine(" -> réponse de l'IA : " + Formater.arrayToString(combinationAiPlayer.getResponseValue()));
	}

	@Override
	protected void executeActionsIfError() {
		//no reset actions necessary if errors during lap in challenger mode
		//if error in human test => mode ask a new test to human until valid test value
	}

}
