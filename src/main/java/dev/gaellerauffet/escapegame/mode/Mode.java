package dev.gaellerauffet.escapegame.mode;

import dev.gaellerauffet.escapegame.combination.impl.Combination;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;

public abstract class Mode {
	protected int modeDev;
	protected int nbTests;
	protected Player humanPlayer;
	protected Player aiPlayer;
	protected Combination combinationHumanPlayer;
	protected Combination combinationAiPlayer;
	protected DisplayMessage displayMsg = new DisplayMessage();
	protected LogMessage logMsg = new LogMessage();
	
	

	public void run() {
		displayStartMsg();
		
		initMode();
		displayMsgBeforeRun();
		
		boolean resultGame = runMode();
		
		displayEndMsg(resultGame);

	}
	
	protected abstract void displayStartMsg();
	protected abstract void displayEndMsg(boolean resultGame);
	protected abstract void initMode();
	protected abstract void displayMsgBeforeRun();

	protected boolean runMode() {
		boolean isValidLap = false;
		int currentLap = 0;
		while(currentLap < nbTests && !isValidLap) {
			runLap(currentLap);
			logLap(currentLap);
			/*boolean validTest = false;
			while(!validTest) {
				try {
					runLap(currentTest);
					logLap();
					validTest = true;
				} catch (InvalidItemException e) {
					displayMsg.errorLine(e.getMessage());
					logMsg.errorLine("essai " + (currentTest + 1) + " " + e.getMessage());
				}
			}*/
			
			isValidLap = checkLapResult();
			currentLap++;
		}
		
		return isValidLap;
	}
	
	protected abstract void runLap(int currentLap);

	protected abstract void logLap(int currentLap);

	protected abstract boolean checkLapResult();
	
	protected void askATestToHuman(int currentLap) {
		displayMsg.info("Votre propositon (combinaison à " + combinationAiPlayer.getLength() + " chiffres) - " + (nbTests - currentLap) + " essai(s) restant(s) : ");
		humanPlayer.giveTest(combinationAiPlayer);
	}

	
	protected void askAResponseToAi() {		
		aiPlayer.giveResponse(combinationAiPlayer);
		displayMsg.infoLine(" -> réponse de l'IA : " + Formater.arrayToString(combinationAiPlayer.getResponseValue()));
	}
	
	protected void askATestToAi(int currentLap) {
		aiPlayer.giveTest(combinationHumanPlayer);
		displayMsg.infoLine("Proposition de l'IA - " + (nbTests - currentLap) + " essai(s) restant(s) : " + Formater.arrayToString(combinationHumanPlayer.getGuessValue()));
	}
	
	protected void askAResponseToHuman() {
		displayMsg.info(" -> votre réponse : ");
		humanPlayer.giveResponse(combinationHumanPlayer);
	}
	
	protected void askACheckResponseToAi() {
		aiPlayer.checkGivenResponse(combinationHumanPlayer);
	}

	
	
}
