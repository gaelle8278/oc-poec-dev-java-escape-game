package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.combination.impl.Combination;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;
import dev.gaellerauffet.escapegame.util.Parameter;

public class DuelMode extends Mode {

	public DuelMode(Player aiPlayer, Player humanPlayer, Combination Aicombination,  Combination Humancombination, int nbTests,  int modeDev) {
		this.nbTests = nbTests;
		this.modeDev = modeDev;
		this.humanPlayer = humanPlayer;
		this.aiPlayer = aiPlayer;
		this.combinationHumanPlayer = Humancombination;
		this.combinationAiPlayer = Aicombination;
		this.displayMsg = new DisplayMessage();
		this.logMsg = new LogMessage();
	}

	@Override
	protected void displayStartMsg() {
		displayMsg.infoLine("Démarrage mode Duel");
		logMsg.infoLine("Mode de jeu : Duel");
		
	}

	@Override
	protected void displayEndMsg(int resultGame) {
		if (resultGame == Parameter.BOTH_ARE_WINNERS) {
			displayMsg.infoLine("Egalité ! Le joueur et l'IA ont deviné la combinaison. ");
			logMsg.infoLine("Fin de partie mode Duel : égalité.");
		} else if (resultGame == Parameter.WINNER_IS_HUMAN) {
			displayMsg.infoLine("Le joueur a gagné. Il a trouvé la combinaison définit par l'IA.");
			logMsg.infoLine(
					"Fin de partie mode Duel : le joueur a gagné, il a trouvé la combinaison définit par l'IA.");
		} else if (resultGame == Parameter.WINNER_IS_AI) {
			displayMsg.infoLine(
					"L'IA a gagné. L'IA a trouvé la combinaison définit par le joueur. La combinaison définit par l'IA était : "
							+ Formater.arrayToString(combinationAiPlayer.getValue()));
			logMsg.infoLine("Fin de partie mode Duel : l'IA a gagné, elle a trouvé la combinaison définit par le joueur.");
		} else {
			displayMsg.infoLine(
					"Aucun gagnant. Personne n'a trouvé la cominaison secrète. La combinaison définit par l'IA était : "
							+ Formater.arrayToString(combinationAiPlayer.getValue()));
			logMsg.infoLine("Fin de partie mode Duel : personne n'a trouvé la combinaison.");
		}
		
	}

	@Override
	protected void initMode() {
		// humanPlayer and aiPlayer set the value to find for its own combination
		//// humanPlayer in his head
		//// the aiPlayer set the value for combination
		aiPlayer.giveCombinationValue(combinationAiPlayer);
	}

	@Override
	protected void displayMsgBeforeRun() {
		String strCombination = Formater.arrayToString(combinationAiPlayer.getValue());
		if (modeDev == 1) {
			displayMsg.infoLine("(combinaison secrète définie par le joueur : connue de lui seul)");
			displayMsg.infoLine("(combinaison secrète définit par l'ia : " + strCombination + ")");
		}
		logMsg.infoLine("combinaison définit par le joueur et connue de lui seul");
		logMsg.infoLine("combinaison définit par l'IA : " + strCombination);
	}


	@Override
	protected void logLap(int currentLap) {
		logMsg.infoLine("essai " + (currentLap + 1) + " combinaison donnée par le joueur : "
				+ Formater.arrayToString(combinationAiPlayer.getGuessValue()) + " / Réponse faites par l'IA "
				+ Formater.arrayToString(combinationAiPlayer.getResponseValue()));
		
		logMsg.infoLine("essai " + (currentLap + 1) + " combinaison donnée par l'IA : "
				+ Formater.arrayToString(combinationHumanPlayer.getGuessValue()) + " / Réponse faites par le joueur "
				+ Formater.arrayToString(combinationHumanPlayer.getResponseValue()));
		
	}

	@Override
	protected int getLapResult() {
		int result = Parameter.NO_WINNER;
		boolean hasAIFoundCombination = combinationHumanPlayer.checkTest();
		boolean hasHumanFoundCombination = combinationAiPlayer.checkTest();
		
		if(hasAIFoundCombination && hasHumanFoundCombination) {
			result = Parameter.BOTH_ARE_WINNERS;
		} else if (hasAIFoundCombination) {
			result = Parameter.WINNER_IS_AI;
		} else if (hasHumanFoundCombination) {
			result = Parameter.WINNER_IS_HUMAN;
		}
		
		return result;
	}

	@Override
	protected void askTest(int currentLap) {
		//not ask test directly uses challenger and defender mode instead
	}

	@Override
	protected void askResponse() {
		//not ask test directly uses challenger and defender mode instead
	}

	@Override
	public void runLap(int currentLap) {
		Mode challenger = new ChallengerMode(aiPlayer, humanPlayer, combinationAiPlayer, nbTests, modeDev);
		challenger.runLap(currentLap);
		
		Mode defender = new DefenderMode(aiPlayer, humanPlayer, combinationHumanPlayer, nbTests, modeDev);
		defender.runLap(currentLap);
		
	}

	@Override
	protected void executeActionsIfError() {
		//not do this directly delegates to challenger and defender mode instead
	}
	
}
