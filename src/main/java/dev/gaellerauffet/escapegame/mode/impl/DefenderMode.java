package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.combination.impl.Combination;
import dev.gaellerauffet.escapegame.exceptions.InvalidResponseException;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;

public class DefenderMode extends Mode {

	public DefenderMode(Player aiPlayer, Player humanPlayer, int nbTests, int combinationLength, int modeDev) {
		this.nbTests = nbTests;
		this.modeDev = modeDev;
		this.humanPlayer = humanPlayer;
		this.aiPlayer = aiPlayer;
		this.combinationHumanPlayer = new Combination(combinationLength); 
		this.displayMsg = new DisplayMessage();
		this.logMsg = new LogMessage();
		
	}

	@Override
	protected void displayStartMsg() {
		displayMsg.infoLine("Démarrage mode Défenseur");
		logMsg.infoLine("Mode de jeu : Défenseur");
		
	}

	@Override
	protected void displayEndMsg(boolean resultGame) {
		if(resultGame) {
			displayMsg.infoLine("Le joueur a perdu. L'IA a trouvé la combinaison.");
			logMsg.infoLine("Fin de partie mode Défenseur : le joueur a perdu, l'IA a trouvé la combinaison.");
		} else {
			displayMsg.infoLine("Le joueur a gagné. L'IA n'a pas trouvé la combinaison.");
			logMsg.infoLine("Fin de partie mode Défenseur : le joueur a gagné, l'IA n'a pas trouvé la combinaison.");
		}
	}

	@Override
	protected void initMode() {
		
		
	}

	@Override
	protected void displayMsgBeforeRun() {
		if(modeDev == 1) {
			displayMsg.infoLine("(combinaison secrète : définie par le joueur)");
		}
		logMsg.infoLine("combinaison définit par le joueur et connue de lui seul");
		
	}

	@Override
	protected void runLap(int currentLap) {
		boolean validHumanResponse=false;
		while(!validHumanResponse) {
			try {
				askATestToAi(currentLap);
				askAResponseToHuman();
				askACheckResponseToAi();
				validHumanResponse = true;
			} catch (InvalidResponseException e) {
				displayMsg.errorLine(e.getMessage());
				logMsg.errorLine("essai " + (currentLap + 1) + " " + e.getMessage());
				combinationHumanPlayer.resetResponseValue();
			} 
		}
		
	}

	@Override
	protected void logLap(int currentLap) {
		logMsg.infoLine("essai " + (currentLap + 1) + " combinaison donnée par l'IA : " +  Formater.arrayToString(combinationHumanPlayer.getGuessValue()) 
		+ " / Réponse faites par le joueur " +  Formater.arrayToString(combinationHumanPlayer.getResponseValue()));
		
	}

	@Override
	protected boolean checkLapResult() {
		
		return combinationHumanPlayer.checkTest();
	}

	
	/*public void run() {
		displayMsg.infoLine("Démarrage mode Défenseur");
		logMsg.infoLine("Mode de jeu : Défenseur");
		
		if(modeDev == 1) {
			displayMsg.infoLine("(combinaison secrète : définie par le joueur)");
		}
		logMsg.infoLine("combinaison définit par le joueur et connue de lui seul");
		
		//3- aiPlayer try to guess the combination = until the answer is good or there is no more test
		boolean responseIsGood = false;
		int currentTest = 0;
		while(currentTest < nbTests && !responseIsGood) {
			boolean validHumanResponse=false;
			while(!validHumanResponse) {
				try {
					runSet(currentTest);
					validHumanResponse = true;
				} catch (InvalidResponseException e) {
					displayMsg.errorLine(e.getMessage());
					logMsg.errorLine("essai " + (currentTest + 1) + " " + e.getMessage());
					combinationHumanPlayer.resetResponseValue();
				} 
			}
			
			responseIsGood = combinationHumanPlayer.checkTest();
			currentTest++;
		}
					
		if(responseIsGood) {
			displayMsg.infoLine("Le joueur a perdu. L'IA a trouvé la combinaison.");
			logMsg.infoLine("Fin de partie mode Défenseur : le joueur a perdu, l'IA a trouvé la combinaison.");
		} else {
			displayMsg.infoLine("Le joueur a gagné. L'IA n'a pas trouvé la combinaison.");
			logMsg.infoLine("Fin de partie mode Défenseur : le joueur a gagné, l'IA n'a pas trouvé la combinaison.");
		}
		
	}

	private void runSet(int currentTest) {
		logMsg.infoLine("essai " + (currentTest + 1) + " combinaison donnée par l'IA : " +  Formater.arrayToString(combinationHumanPlayer.getGuessValue()) 
							+ " / Réponse faites par le joueur " +  Formater.arrayToString(combinationHumanPlayer.getResponseValue()));
			
		
	}*/


}
