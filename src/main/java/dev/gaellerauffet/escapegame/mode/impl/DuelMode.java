package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.exceptions.InvalidTestException;
import dev.gaellerauffet.escapegame.combination.impl.Combination;
import dev.gaellerauffet.escapegame.exceptions.InvalidResponseException;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;

public class DuelMode extends Mode {

	public DuelMode(Player aiPlayer, Player humanPlayer, int nbTests, int combinationLength, int modeDev) {
		this.nbTests = nbTests;
		this.modeDev = modeDev;
		this.humanPlayer = humanPlayer;
		this.aiPlayer = aiPlayer;
		this.combinationHumanPlayer = new Combination(combinationLength);
		this.combinationAiPlayer = new Combination(combinationLength);
		this.displayMsg = new DisplayMessage();
		this.logMsg = new LogMessage();
	}

	@Override
	protected void displayStartMsg() {
		displayMsg.infoLine("Démarrage mode Duel");
		logMsg.infoLine("Mode de jeu : Duel");
		
	}

	@Override
	protected void displayEndMsg(boolean resultGame) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void displayMsgBeforeRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void runLap(int currentLap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void logLap(int currentLap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean checkLapResult() {
		// TODO Auto-generated method stub
		return false;
	}

	
	/*public void run() {
		displayMsg.infoLine("Démarrage mode Duel");
		logMsg.infoLine("Mode de jeu : Duel");

		// 2- the humanPlayer and aiPlayer set the value to find for its own combination
		// humanPlayer in his head
		if (modeDev == 1) {
			displayMsg.infoLine("(combinaison secrète définie par le joueur : connue de lui seul)");
		}
		logMsg.infoLine("combinaison définit par le joueur et connue de lui seul");

		// the aiPlayer set the value for combination
		aiPlayer.giveCombinationValue(combinationAiPlayer);
		
		String strCombination = Formater.arrayToString(combinationAiPlayer.getValue());
		if (modeDev == 1) {
			displayMsg.infoLine("(combinaison secrète définit par l'ia : " + strCombination + ")");
		}
		logMsg.infoLine("combinaison définit par l'IA : " + strCombination);

		// 3 - aiPlayer and humanPlayer try to guess the combination = until one give
		// the good answer or there is no more test
		boolean responseHumanIsGood = false;
		boolean responseAIIsGood = false;

		int currentTest = 0;
		while (currentTest < nbTests && !responseHumanIsGood && !responseAIIsGood) {

			boolean validHumanTest = false;
			boolean validHumanResponse = false;
			while (!validHumanTest) {
				try {
					// 3a - humanPlayer do a test and aiPlayer evaluate the test
					runHumanSet(currentTest);
					validHumanTest = true;
				} catch (InvalidTestException e) {
					displayMsg.errorLine(e.getMessage());
					logMsg.errorLine("essai " + (currentTest + 1) + " " + e.getMessage());
				}
			}

			while (!validHumanResponse) {
				try {
					// 3b - aiPlayer do a test and humanPlayer evaluate the test
					runAiSet(currentTest);
					validHumanResponse = true;
				} catch (InvalidResponseException e) {
					displayMsg.errorLine(e.getMessage());
					logMsg.errorLine("essai " + (currentTest + 1) + " " + e.getMessage());
					// reset responseValue 
					combinationHumanPlayer.resetResponseValue();
				} 
			}

			// 3c - checks if the tests done are good
			responseAIIsGood = combinationHumanPlayer.checkTest();
			responseHumanIsGood = combinationAiPlayer.checkTest();

			// 3d - a test is lost
			currentTest++;
		}

		// 4 - when the loop is terminated display the right end game message according
		// to responses checks
		if (responseHumanIsGood && responseAIIsGood) {
			displayMsg.infoLine("Egalité ! Le joueur et l'IA ont deviné la combinaison. ");
			logMsg.infoLine("Fin de partie mode Duel : égalité.");
		} else if (responseHumanIsGood) {
			displayMsg.infoLine("Le joueur a gagné. Il a trouvé la combinaison définit par l'IA.");
			logMsg.infoLine(
					"Fin de partie mode Duel : le joueur a gagné, il a trouvé la combinaison définit par l'IA.");
		} else if (responseAIIsGood) {
			displayMsg.infoLine(
					"L'IA a gagné. L'IA a trouvé la combinaison définit par le joueur. La combinaison définit par l'IA était : "
							+ strCombination);
			logMsg.infoLine("Fin de partie mode Duel : l'IA a gagné, elle a trouvé la combinaison définit par l'IA.");
		} else {
			displayMsg.infoLine(
					"Aucun gagant. Personne n'a trouvé la cominaison secrète. La combinaison définit par l'IA était : "
							+ strCombination);
			logMsg.infoLine("Fin de partie mode Duel : personne n'a gagné.");
		}
	}

	private void runAiSet(int currentTest) {
		aiPlayer.giveTest(combinationHumanPlayer);
		displayMsg.infoLine("Proposition de l'IA - " + (nbTests - currentTest) + " essai(s) restant(s) : "
				+ Formater.arrayToString(combinationHumanPlayer.getGuessValue()));

		displayMsg.info(" -> votre réponse : ");
		humanPlayer.giveResponse(combinationHumanPlayer);

		aiPlayer.checkGivenResponse(combinationHumanPlayer);

		// messages
		logMsg.infoLine("essai " + (currentTest + 1) + " combinaison donnée par l'IA : "
				+ Formater.arrayToString(combinationHumanPlayer.getGuessValue()) + " / Réponse faites par le joueur "
				+ Formater.arrayToString(combinationHumanPlayer.getResponseValue()));

	}

	private void runHumanSet(int currentTest) {
		displayMsg.info("Votre propositon (combinaison à " + combinationAiPlayer.getLength() + " chiffres) - "
				+ (nbTests - currentTest) + " essai(s) restant(s) : ");
		humanPlayer.giveTest(combinationAiPlayer);

		aiPlayer.giveResponse(combinationAiPlayer);
		displayMsg.infoLine(" -> réponse de l'IA : " + Formater.arrayToString(combinationAiPlayer.getResponseValue()));

		// log
		logMsg.infoLine("essai " + (currentTest + 1) + " combinaison donnée par le joueur : "
				+ Formater.arrayToString(combinationAiPlayer.getGuessValue()) + " / Réponse faites par l'IA "
				+ Formater.arrayToString(combinationAiPlayer.getResponseValue()));

	}*/

}
