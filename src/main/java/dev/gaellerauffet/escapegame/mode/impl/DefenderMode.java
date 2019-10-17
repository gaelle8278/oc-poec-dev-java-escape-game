package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.exceptions.InvalidTestException;
import dev.gaellerauffet.escapegame.exceptions.InvalidResponseException;
import dev.gaellerauffet.escapegame.game.Combination;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;

public class DefenderMode implements Mode {
	private int modeDev;
	private int nbTests;
	private Player humanPlayer;
	private Player aiPlayer;
	private Combination combinationHumanPlayer;
	private DisplayMessage displayMsg;
	private LogMessage logMsg;

	public DefenderMode(Player humanPlayer, Player aiPlayer, int nbTests, int combinationLength, int modeDev) {
		this.nbTests = nbTests;
		this.modeDev = modeDev;
		this.humanPlayer = humanPlayer;
		this.aiPlayer = aiPlayer;
		this.combinationHumanPlayer = new Combination(combinationLength); 
		this.displayMsg = new DisplayMessage();
		this.logMsg = new LogMessage();
		
	}

	@Override
	public void run() {
		displayMsg.infoLine("Démarrage mode Défenseur");
		logMsg.infoLine("Mode de jeu : Défenseur");
		
	
		
		//TOTO in proper method setCombinationValue() => for human 
		if(modeDev == 1) {
			displayMsg.infoLine("(combinaison secrète : définie par le joueur)");
		}
		logMsg.infoLine("combinaison définit par le joueur et connue de lui seul");
		
		//3- aiPlayer try to guess the combination = until the answer is good or there is no more test
		boolean responseIsGood = false;
		int currentTest = 0;
		while(currentTest < nbTests && !responseIsGood) {
			try {
				runSet(currentTest);
				responseIsGood = combinationHumanPlayer.checkTest();
				currentTest++;
			} catch (InvalidResponseException e) {
				displayMsg.errorLine(e.getMessage());
				logMsg.errorLine("essai " + (currentTest + 1) + " " + e.getMessage());
				combinationHumanPlayer.resetResponseValue();
			} /*catch (InvalidTestException e){
				displayMsg.errorLine(e.getMessage());
				logMsg.errorLine("essai " + (currentTest + 1) + " " + e.getMessage());
				//reset responseValue
				combinationHumanPlayer.resetResponseValue();
			}*/
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
		aiPlayer.giveTest(combinationHumanPlayer);
		displayMsg.infoLine("Proposition de l'IA - " + (nbTests - currentTest) + " essai(s) restant(s) : " + Formater.arrayToString(combinationHumanPlayer.getGuessValue()));
		
		displayMsg.info(" -> votre réponse : ");
		humanPlayer.giveResponse(combinationHumanPlayer);
		
		aiPlayer.checkGivenResponse(combinationHumanPlayer);
		
		//messages
		logMsg.infoLine("essai " + (currentTest + 1) + " combinaison donnée par l'IA : " +  Formater.arrayToString(combinationHumanPlayer.getGuessValue()) 
							+ " / Réponse faites par le joueur " +  Formater.arrayToString(combinationHumanPlayer.getResponseValue()));
			
		
	}


}
