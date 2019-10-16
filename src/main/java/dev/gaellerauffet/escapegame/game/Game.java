package dev.gaellerauffet.escapegame.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


import dev.gaellerauffet.escapegame.exceptions.IllegalItemException;
import dev.gaellerauffet.escapegame.exceptions.IllegalPropertiesValueException;
import dev.gaellerauffet.escapegame.exceptions.InconsistencyException;
import dev.gaellerauffet.escapegame.exceptions.MenuOptionException;
import dev.gaellerauffet.escapegame.menu.impl.EndMenu;
import dev.gaellerauffet.escapegame.menu.impl.StartMenu;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.mode.impl.ModeFactory;
import dev.gaellerauffet.escapegame.player.impl.AiPlayer;
import dev.gaellerauffet.escapegame.player.impl.HumanPlayer;
import dev.gaellerauffet.escapegame.util.Parameter;

public class Game {
	
	int gameMode;
	int endOption;
	DisplayMessage displayMsg;
	LogMessage logMsg;
	StartMenu startMenu;
	EndMenu endMenu;
	
	public Game() {
		displayMsg = new DisplayMessage();
		logMsg = new LogMessage();
		startMenu = new StartMenu();
		endMenu = new EndMenu();
	}
	
	
	/**
	 * Run all steps of a game
	 */
	public void run() {
		String selectedMode="";
		String selectedEndOption="";
		Mode gameMode = null;
		ModeFactory modeFactory = new ModeFactory();
		
		//TODO proper method intModeFactory() ?
		try {
			modeFactory.loadConfig();
		} catch (IllegalPropertiesValueException e) {
			displayMsg.errorLine(e.getMessage());
			logMsg.errorLine(e.getMessage());
		}
		
		startMenu.display();
		
		//TODO in proper method getSelectedMode ?
		while( "".equals(selectedMode)) {
			try {
				selectedMode = startMenu.getSelectedItem();
			} catch (MenuOptionException e) {
				displayMsg.errorLine("Vous n'avez pas choisi une option de menu valide.");
				logMsg.errorLine("Option de menu invalide : " + selectedMode);  
			}
		}
		
		//TODO where ?
		if(selectedMode.toUpperCase().equals(Parameter.OPTION_QUIT)) {
			displayMsg.infoLine("Bye bye !");
			logMsg.infoLine("Sortie du programme.");
			System.exit(0);	
		} else {
			logMsg.infoLine("Item menu de début sélectionné : " + selectedMode + " = " + startMenu.getLabelSelectedItem(selectedMode));
		}
		
	
		gameMode = modeFactory.getMode(selectedMode);
		gameMode.run();
		
		
		endMenu.display();
		
		//TODO in proper function getSelectEndOption ??
		while( "".equals(selectedEndOption)) {
			try {
				selectedEndOption = endMenu.getSelectedItem();
			} catch (MenuOptionException e) {
				displayMsg.errorLine("Vous n'avez pas choisi une option de menu valide.");
				logMsg.errorLine("Option de menu invalide : " + selectedMode);  
			}
		} 
		
		if(selectedEndOption.toUpperCase().equals(Parameter.OPTION_QUIT)) {
			displayMsg.infoLine("Bye bye !");
			logMsg.infoLine("Sortie du programme");
			System.exit(0);	
		} else {
			logMsg.infoLine("Item menu de fin sélectionné : " + selectedMode + " = " + endMenu.getLabelSelectedItem(selectedEndOption));
		}
		
		runEndOption(selectedEndOption);
	}

	
	
	
	/**
	 * Run an end option game
	 * 
	 * @param endOption
	 */
	private void runEndOption(String option) {
		if (option.equals(Parameter.REPLAY_OPTION)) {
			run();
		} else if (option.equals(Parameter.REPLAY_OPTION)) {
			runSameMode();
		}
		
	}

	private void runSameMode() {
		// TODO Auto-generated method stub
		
	}

	

	/**
	 * Manage the "challenger" mode of the game
	 */
	/*private void runChallengerMode() {
		displayMsg.printLineInfo("Démarrage mode Challengeur");
		displayMsg.logInfo("Mode de jeu : Challengeur");
		
		//1- set required elements to play "challenger" mode : 1 combination
		Combination combinationToFind = new Combination(combinationLength);
		
		//2- the aiPlayer set the value for combination
		setValueForCombination(combinationToFind);
		String strCombination = combinationToFind.valueToString(combinationToFind.getValue());
		// display value of combination if necessary
		if(modeDev == 1) {
			displayMsg.printLineInfo("(combinaison secrète : " + strCombination + ")");
		}
		// log value of combination
		displayMsg.logInfo("combinaison définit par l'IA : " + strCombination);
		
		//3- humanPlayer try to guess the combination = until the answer is good or there is no more test
		boolean responseIsGood = false;
		currentTest = 0;
		while(currentTest < nbTests && !responseIsGood) {
			try {
				askATestToHuman(combinationToFind);
				askResponseToAI(combinationToFind);
				
				//log 
				String strCombinationTest = combinationToFind.valueToString(combinationToFind.getGuessValue());
				String strResponseCombination = combinationToFind.valueToString(combinationToFind.getResponseValue());
				displayMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par le joueur : " + strCombinationTest + " / Réponse faites par l'IA " + strResponseCombination);
				
				responseIsGood = combinationToFind.checkTest();
				
				//new test if proposition is well-formatted
				currentTest++;
			} catch (IllegalItemException e) {
				//catch custom array if try to get char into int array
				displayMsg.printLineInfo( e.getMessage());
				displayMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
			}
			
		}
		
		if(responseIsGood) {
			displayMsg.printLineInfo("Combinaison trouvée ! Le joueur a gagné.");
			displayMsg.logInfo("Fin de partie mode Challenger : le joueur a gagné. Il a trouvé la combinaison.");
		} else {
			displayMsg.printLineInfo("Le joueur a perdu. La combinaison était : " +  strCombination);
			displayMsg.logInfo("Fin de partie mode Challenger : le joueur a perdu. Il n'a pas trouvé la combinaison.");
		}
	}*/
	
	


	/**
	 * Manage the "Défenseur" mode
	 */
	/*private void runDefenseMode() {
		displayMsg.printLineInfo("Démarrage mode Défenseur");
		displayMsg.logInfo("Mode de jeu : Défenseur");
		
		//1- set required elements to play "défenseur" mode
		Combination combinationToFind = new Combination(combinationLength);
		
		//2- the humanPlayer set the value for combination (in his head)
		if(modeDev == 1) {
			displayMsg.printLineInfo("(combinaison secrète : définie par le joueur)");
		}
		displayMsg.logInfo("combinaison définit par le joueur et connue de lui seul");
		
		//3- aiPlayer try to guess the combination = until the answer is good or there is no more test
		boolean responseIsGood = false;
		
		currentTest = 0;
		while(currentTest < nbTests && !responseIsGood) {
			try {
				askATestToAI(combinationToFind);
				askResponseToHuman(combinationToFind);
				aiPlayer.checkConsistentResponse(combinationToFind);	
				responseIsGood = combinationToFind.checkTest();
				
				//messages
				String strCombinationTest = combinationToFind.valueToString(combinationToFind.getGuessValue());
				String strResponseCombination = combinationToFind.valueToString(combinationToFind.getResponseValue());
				displayMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par l'IA : " + strCombinationTest + " / Réponse faites par le joueur " + strResponseCombination);
								
				//new test 
				currentTest++;
			} catch (InconsistencyException e) {
				displayMsg.printLineInfo(e.getMessage());
				displayMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
				//reset responseValue
				combinationToFind.resetResponseValue();
				//given back a try (because exception is raised the next round after humanPlayer response)
				//currentTest--;
			} catch (IllegalItemException e){
				displayMsg.printLineInfo(e.getMessage());
				displayMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
				//reset responseValue
				combinationToFind.resetResponseValue();
			}
		}
					
		if(responseIsGood) {
			displayMsg.printLineInfo("Le joueur a perdu. L'IA a trouvé la combinaison.");
			displayMsg.logInfo("Fin de partie mode Défenseur : le joueur a perdu, l'IA a trouvé la combinaison.");
		} else {
			displayMsg.printLineInfo("Le joueur a gagné. L'IA n'a pas trouvé la combinaison.");
			displayMsg.logInfo("Fin de partie mode Défenseur : le joueur a gagné, l'IA n'a pas trouvé la combinaison.");
		}
		
	}*/

	/**
	 * Manage the "Duel" mode
	 */
	/*private void runDuelMode() {
		displayMsg.printLineInfo("Démarrage mode Duel");
		displayMsg.logInfo("Mode de jeu : Duel");
		
		//1- set required elements to play "duel" mode : 2 combinations
		Combination humanCombination = new Combination(combinationLength);
		Combination aiCombination = new Combination(combinationLength);
		
		//2- the humanPlayer and aiPlayer set the value to find for its own combination 
		//humanPlayer in his head
		if(modeDev == 1) {
			displayMsg.printLineInfo("(combinaison secrète définie par le joueur : connue de lui seul)");
		}
		displayMsg.logInfo("combinaison définit par le joueur et connue de lui seul");
		
		//the aiPlayer set the value for combination
		setValueForCombination(aiCombination);
		String strCombination = aiCombination.valueToString(aiCombination.getValue());
		if(modeDev == 1) {
			displayMsg.printLineInfo("(combinaison secrète définit par l'ia : " + strCombination + ")");
		}
		displayMsg.logInfo("combinaison définit par l'IA : " + strCombination);
		
		//3 - aiPlayer and humanPlayer try to guess the combination = until one give the good answer or there is no more test
		boolean responseHumanIsGood = false;
		boolean responseAIIsGood = false;
		
		currentTest = 0;
		while(this.currentTest < nbTests && !responseHumanIsGood && !responseAIIsGood) {
			boolean validHumanTest = false;
			boolean validHumanResponse = false;
			while(!validHumanTest) {
				try {
					//3a - humanPlayer do a test and aiPlayer evaluate the test
					
					askATestToHuman(aiCombination);
					askResponseToAI(aiCombination);
					
					//log 3a
					String strCombinationTestHuman = aiCombination.valueToString(aiCombination.getGuessValue());
					String strResponseAI = aiCombination.valueToString(aiCombination.getResponseValue());
					displayMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par le joueur : " + strCombinationTestHuman + "/ Réponse faite par l'IA " + strResponseAI);
					validHumanTest = true;
				} catch (IllegalItemException e) {
					displayMsg.printLineInfo(e.getMessage());
					displayMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
					humanCombination.resetResponseValue();
				}
			}
			
			while(!validHumanResponse) {
				try {
					//3b - aiPlayer do a test and humanPlayer evaluate the test
					askATestToAI(humanCombination);
					askResponseToHuman(humanCombination);
					aiPlayer.checkConsistentResponse(humanCombination);
					
					//log 3b
					String strCombinationTestAI = humanCombination.valueToString(humanCombination.getGuessValue());
					String strResponseHuman = humanCombination.valueToString(humanCombination.getResponseValue());
					displayMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par l'IA : " + strCombinationTestAI + " / Réponse faite par le joueur " + strResponseHuman);
				
					validHumanResponse =true;
				}  catch (InconsistencyException e) {
					displayMsg.printLineInfo(e.getMessage());
					displayMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
					//reset responseValue
					humanCombination.resetResponseValue();
				} catch (IllegalItemException e) {
					displayMsg.printLineInfo(e.getMessage());
					displayMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
					humanCombination.resetResponseValue();
				}
			}
				
			//3c - checks if the tests done are good
			responseAIIsGood = humanCombination.checkTest();
			responseHumanIsGood = aiCombination.checkTest();
				
			//3d - a test is lost
			this.currentTest++;
				
			
				
			
			
			
		}
		
		//4 - when the loop is terminated display the right end game message according to responses checks
		if(responseHumanIsGood && responseAIIsGood) {
			displayMsg.printLineInfo("Egalité ! Le joueur et l'IA ont deviné la combinaison. ");
			displayMsg.logInfo("Fin de partie mode Duel : égalité.");
		} else if(responseHumanIsGood) {
			displayMsg.printLineInfo("Le joueur a gagné. Il a trouvé la combinaison définit par l'IA.");
			displayMsg.logInfo("Fin de partie mode Duel : le joueur a gagné, il a trouvé la combinaison définit par l'IA.");
		} else if (responseAIIsGood){
			displayMsg.printLineInfo("L'IA a gagné. L'IA a trouvé la combinaison définit par le joueur. La combinaison définit par l'IA était : " + strCombination);
			displayMsg.logInfo("Fin de partie mode Duel : l'IA a gagné, elle a trouvé la combinaison définit par l'IA.");
		} else {
			displayMsg.printLineInfo("Aucun gagant. Personne n'a trouvé la cominaison secrète. La combinaison définit par l'IA était : " + strCombination);
			displayMsg.logInfo("Fin de partie mode Duel : personne n'a gagné.");
		}
	}*/

	/**
	 * Set the value to find for a combination
	 * 
	 * @param combination		game combination
	 */
	/*private void setValueForCombination(Combination combination) {
		aiPlayer.setValueCombination(combination);
		
	}*/


	/**
	 * Ask a test to humanPlayer for a given combination
	 * 
	 * @param combination		combination to guess
	 */
	/*private void askATestToHuman(Combination combination) {
		//ask a proposition for combination
		displayMsg.printInfo("Votre propositon (combinaison à " + combination.getLength() + " chiffres) - " + (nbTests - currentTest) + " essai(s) restant(s) : ");
		humanPlayer.guessCombination(combination);
	}*/
	
	/**
	 * Ask a test to AiPlayer for a given combination
	 * 
	 * @param combination		combination to guess
	 */
	/*private void askATestToAI(Combination combination) {
		//aiPlayer set a proposition for the combination
		aiPlayer.guessCombination(combination);
		//display the proposition done by aiPlayer
		displayMsg.printLineInfo("Proposition de l'IA - " + (nbTests - currentTest) + " essai(s) restant(s) : " + combination.valueToString(combination.getGuessValue()));
	}*/
	
	/**
	 * Ask a response to humanPlayer for a given combination
	 * 
	 * @param combination		combination to guess
	 */
	/*private void askResponseToHuman(Combination combination) {
			//ask for an answer to proposition done by aiPlayer
			displayMsg.printInfo(" -> votre réponse : ");
			humanPlayer.checkCombination(combination);
	}*/
	
	/**
	 * Ask a response to aiPlayer for a given combination
	 * 
	 * @param combination		combination to guess
	 */
	/*private void askResponseToAI(Combination combination) {
		aiPlayer.checkCombination(combination);
		displayMsg.printLineInfo(" -> réponse de l'IA : " + combination.valueToString(combination.getResponseValue()));
		
		
	}*/
}
