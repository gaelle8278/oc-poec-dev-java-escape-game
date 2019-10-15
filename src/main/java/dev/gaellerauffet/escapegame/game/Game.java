package dev.gaellerauffet.escapegame.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import dev.gaellerauffet.escapegame.config.PropertiesLoader;
import dev.gaellerauffet.escapegame.exceptions.IllegalItemException;
import dev.gaellerauffet.escapegame.exceptions.IllegalPropertiesValueException;
import dev.gaellerauffet.escapegame.exceptions.InconsistencyException;
import dev.gaellerauffet.escapegame.players.impl.AI;
import dev.gaellerauffet.escapegame.players.impl.Human;
import dev.gaellerauffet.escapegame.util.Menu;
import dev.gaellerauffet.escapegame.util.Message;

public class Game {
	int modeDev;
	int nbTests;
	int combinationLength;
	int currentTest = 0;
	int gameMode;
	int endOption;
	AI ai;
	Human human;
	Message gameMsg;
	Menu startMenu;
	Menu endMenu;
	
	public Game() {
		gameMsg = new Message();
		loadConfig();
		ai = new AI();
		human = new Human();
		this.setStartMenu();
		this.setEndMenu();
	}
	
	
	/**
	 * Load a configuration file
	 */
	private void loadConfig() {
		// load a properties files
		try{
			PropertiesLoader gameProps = new PropertiesLoader();
			gameProps.loadProperties("game.properties");
			modeDev=gameProps.getModeDev();
			nbTests=gameProps.getNumberTests();
			combinationLength=gameProps.getCombinationLength();
		} catch (IllegalPropertiesValueException e) {
			gameMsg.printLineInfo("Paramètres du jeu non valide : " + e.getMessage() + ". Sortie du programme.");
			gameMsg.logError("Paramètres du jeu non valides. " + e.getMessage() + ". Sortie du programme.");
			System.exit(0);
		} catch (NullPointerException e) {
			gameMsg.printLineInfo("Le fichier de propriétés ne peut pas être lu. Sortie du programme.");
			gameMsg.logError("Erreur récupération fichier de propriétés. Sortie du programme.");
			System.exit(0);
        } catch (IOException e) {
        	gameMsg.printLineInfo("Le fichier de propriétés ne peut pas être lu. Sortie du programme.");
			gameMsg.logError("Fichier de propriétés illisible. Sortie du programme.");
			System.exit(0);
        } 
		
	}
	
	/**
	 * Defines the start menu of the game
	 * @return
	 */
	private Menu setStartMenu() {
		 ArrayList<String> menuOptions = new ArrayList<String>( 
		            Arrays.asList("Challenger", 
		                          "Défenseur", 
		                          "Duel",
		                          "Quitter l'application"
		                          )
		            ); 
		 
		String menuTitle = "Choissisez le mode jeu :";
		this.startMenu = new Menu(menuTitle, menuOptions);
		return startMenu;
	}
	
	/**
	 * Defines the end menu of the game
	 * @return
	 */
	private Menu setEndMenu() {
		 ArrayList<String> menuOptions = new ArrayList<String>( 
		            Arrays.asList("Rejouer au même mode", 
		                          "Rejouer", 
		                          "Quitter l'application"
		                          )
		            ); 
		 
		String menuTitle = "Choissisez le mode jeu :";
		this.endMenu = new Menu(menuTitle, menuOptions);
		return endMenu;
	}
	
	/**
	 * Run all steps of a game
	 */
	public void run(boolean setGame) {
		if( setGame == true) {
			setGameMode();
		}
		runMode();
		setEndOption();
		runEndOption();
	}
	
	
	/**
	 * Set the end option
	 */
	private void setEndOption() {
		endMenu.display();
		this.endOption = endMenu.getSelectedItem();
	}
	
	/**
	 * Set the game mode selected
	 * @return
	 */
	private void setGameMode() {
		startMenu.display();
		this.gameMode = startMenu.getSelectedItem();
	}

	
	/**
	 * Run a game mode
	 * 
	 * @param mode
	 */
	private void runMode() {
		ai.reset();
		if(this.gameMode == 1) {
			runChallengerMode();
		} else if (this.gameMode == 2) {
			runDefenseMode();
		} else if (this.gameMode == 3) {
			runDuelMode();
		}
		
	}
	
	/**
	 * Run an end option game
	 * 
	 * @param endOption
	 */
	private void runEndOption() {
		boolean setMode = false;
		if (this.endOption == 2 ) {
			//selected an another game mode
			setMode = true;
		} 
		run(setMode);
		
	}

	/**
	 * Manage the "challenger" mode of the game
	 */
	private void runChallengerMode() {
		gameMsg.printLineInfo("Démarrage mode Challengeur");
		gameMsg.logInfo("Mode de jeu : Challengeur");
		
		//1- set required elements to play "challenger" mode : 1 combination
		Combination combinationToFind = new Combination(combinationLength);
		
		//2- the ai set the value for combination
		setValueForCombination(combinationToFind);
		String strCombination = combinationToFind.valueToString(combinationToFind.getValue());
		// display value of combination if necessary
		if(modeDev == 1) {
			gameMsg.printLineInfo("(combinaison secrète : " + strCombination + ")");
		}
		// log value of combination
		gameMsg.logInfo("combinaison définit par l'IA : " + strCombination);
		
		//3- human try to guess the combination = until the answer is good or there is no more test
		boolean responseIsGood = false;
		currentTest = 0;
		while(currentTest < nbTests && !responseIsGood) {
			try {
				askATestToHuman(combinationToFind);
				askResponseToAI(combinationToFind);
				
				//log 
				String strCombinationTest = combinationToFind.valueToString(combinationToFind.getGuessValue());
				String strResponseCombination = combinationToFind.valueToString(combinationToFind.getResponseValue());
				gameMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par le joueur : " + strCombinationTest + " / Réponse faites par l'IA " + strResponseCombination);
				
				responseIsGood = combinationToFind.checkTest();
				
				//new test if proposition is well-formatted
				currentTest++;
			} catch (IllegalItemException e) {
				//catch custom array if try to get char into int array
				gameMsg.printLineInfo( e.getMessage());
				gameMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
			}
			
		}
		
		if(responseIsGood) {
			gameMsg.printLineInfo("Combinaison trouvée ! Le joueur a gagné.");
			gameMsg.logInfo("Fin de partie mode Challenger : le joueur a gagné. Il a trouvé la combinaison.");
		} else {
			gameMsg.printLineInfo("Le joueur a perdu. La combinaison était : " +  strCombination);
			gameMsg.logInfo("Fin de partie mode Challenger : le joueur a perdu. Il n'a pas trouvé la combinaison.");
		}
	}
	
	


	/**
	 * Manage the "Défenseur" mode
	 */
	private void runDefenseMode() {
		gameMsg.printLineInfo("Démarrage mode Défenseur");
		gameMsg.logInfo("Mode de jeu : Défenseur");
		
		//1- set required elements to play "défenseur" mode
		Combination combinationToFind = new Combination(combinationLength);
		
		//2- the human set the value for combination (in his head)
		if(modeDev == 1) {
			gameMsg.printLineInfo("(combinaison secrète : définie par le joueur)");
		}
		gameMsg.logInfo("combinaison définit par le joueur et connue de lui seul");
		
		//3- ai try to guess the combination = until the answer is good or there is no more test
		boolean responseIsGood = false;
		
		currentTest = 0;
		while(currentTest < nbTests && !responseIsGood) {
			try {
				askATestToAI(combinationToFind);
				askResponseToHuman(combinationToFind);
				ai.checkConsistentResponse(combinationToFind);	
				responseIsGood = combinationToFind.checkTest();
				
				//messages
				String strCombinationTest = combinationToFind.valueToString(combinationToFind.getGuessValue());
				String strResponseCombination = combinationToFind.valueToString(combinationToFind.getResponseValue());
				gameMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par l'IA : " + strCombinationTest + " / Réponse faites par le joueur " + strResponseCombination);
								
				//new test 
				currentTest++;
			} catch (InconsistencyException e) {
				gameMsg.printLineInfo(e.getMessage());
				gameMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
				//reset responseValue
				combinationToFind.resetResponseValue();
				//given back a try (because exception is raised the next round after human response)
				//currentTest--;
			} catch (IllegalItemException e){
				gameMsg.printLineInfo(e.getMessage());
				gameMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
				//reset responseValue
				combinationToFind.resetResponseValue();
			}
		}
					
		if(responseIsGood) {
			gameMsg.printLineInfo("Le joueur a perdu. L'IA a trouvé la combinaison.");
			gameMsg.logInfo("Fin de partie mode Défenseur : le joueur a perdu, l'IA a trouvé la combinaison.");
		} else {
			gameMsg.printLineInfo("Le joueur a gagné. L'IA n'a pas trouvé la combinaison.");
			gameMsg.logInfo("Fin de partie mode Défenseur : le joueur a gagné, l'IA n'a pas trouvé la combinaison.");
		}
		
	}

	/**
	 * Manage the "Duel" mode
	 */
	private void runDuelMode() {
		gameMsg.printLineInfo("Démarrage mode Duel");
		gameMsg.logInfo("Mode de jeu : Duel");
		
		//1- set required elements to play "duel" mode : 2 combinations
		Combination humanCombination = new Combination(combinationLength);
		Combination aiCombination = new Combination(combinationLength);
		
		//2- the human and ai set the value to find for its own combination 
		//human in his head
		if(modeDev == 1) {
			gameMsg.printLineInfo("(combinaison secrète définie par le joueur : connue de lui seul)");
		}
		gameMsg.logInfo("combinaison définit par le joueur et connue de lui seul");
		
		//the ai set the value for combination
		setValueForCombination(aiCombination);
		String strCombination = aiCombination.valueToString(aiCombination.getValue());
		if(modeDev == 1) {
			gameMsg.printLineInfo("(combinaison secrète définit par l'ia : " + strCombination + ")");
		}
		gameMsg.logInfo("combinaison définit par l'IA : " + strCombination);
		
		//3 - ai and human try to guess the combination = until one give the good answer or there is no more test
		boolean responseHumanIsGood = false;
		boolean responseAIIsGood = false;
		
		currentTest = 0;
		while(this.currentTest < nbTests && !responseHumanIsGood && !responseAIIsGood) {
			boolean validHumanTest = false;
			boolean validHumanResponse = false;
			while(!validHumanTest) {
				try {
					//3a - human do a test and ai evaluate the test
					
					askATestToHuman(aiCombination);
					askResponseToAI(aiCombination);
					
					//log 3a
					String strCombinationTestHuman = aiCombination.valueToString(aiCombination.getGuessValue());
					String strResponseAI = aiCombination.valueToString(aiCombination.getResponseValue());
					gameMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par le joueur : " + strCombinationTestHuman + "/ Réponse faite par l'IA " + strResponseAI);
					validHumanTest = true;
				} catch (IllegalItemException e) {
					gameMsg.printLineInfo(e.getMessage());
					gameMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
					humanCombination.resetResponseValue();
				}
			}
			
			while(!validHumanResponse) {
				try {
					//3b - ai do a test and human evaluate the test
					askATestToAI(humanCombination);
					askResponseToHuman(humanCombination);
					ai.checkConsistentResponse(humanCombination);
					
					//log 3b
					String strCombinationTestAI = humanCombination.valueToString(humanCombination.getGuessValue());
					String strResponseHuman = humanCombination.valueToString(humanCombination.getResponseValue());
					gameMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par l'IA : " + strCombinationTestAI + " / Réponse faite par le joueur " + strResponseHuman);
				
					validHumanResponse =true;
				}  catch (InconsistencyException e) {
					gameMsg.printLineInfo(e.getMessage());
					gameMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
					//reset responseValue
					humanCombination.resetResponseValue();
				} catch (IllegalItemException e) {
					gameMsg.printLineInfo(e.getMessage());
					gameMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
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
			gameMsg.printLineInfo("Egalité ! Le joueur et l'IA ont deviné la combinaison. ");
			gameMsg.logInfo("Fin de partie mode Duel : égalité.");
		} else if(responseHumanIsGood) {
			gameMsg.printLineInfo("Le joueur a gagné. Il a trouvé la combinaison définit par l'IA.");
			gameMsg.logInfo("Fin de partie mode Duel : le joueur a gagné, il a trouvé la combinaison définit par l'IA.");
		} else if (responseAIIsGood){
			gameMsg.printLineInfo("L'IA a gagné. L'IA a trouvé la combinaison définit par le joueur. La combinaison définit par l'IA était : " + strCombination);
			gameMsg.logInfo("Fin de partie mode Duel : l'IA a gagné, elle a trouvé la combinaison définit par l'IA.");
		} else {
			gameMsg.printLineInfo("Aucun gagant. Personne n'a trouvé la cominaison secrète. La combinaison définit par l'IA était : " + strCombination);
			gameMsg.logInfo("Fin de partie mode Duel : personne n'a gagné.");
		}
	}

	/**
	 * Set the value to find for a combination
	 * 
	 * @param combination		game combination
	 */
	private void setValueForCombination(Combination combination) {
		ai.setValueCombination(combination);
		
	}


	/**
	 * Ask a test to human for a given combination
	 * 
	 * @param combination		combination to guess
	 */
	private void askATestToHuman(Combination combination) {
		//ask a proposition for combination
		gameMsg.printInfo("Votre propositon (combinaison à " + combination.getLength() + " chiffres) - " + (nbTests - currentTest) + " essai(s) restant(s) : ");
		human.guessCombination(combination);
	}
	
	/**
	 * Ask a test to AI for a given combination
	 * 
	 * @param combination		combination to guess
	 */
	private void askATestToAI(Combination combination) {
		//ai set a proposition for the combination
		ai.guessCombination(combination);
		//display the proposition done by ai
		gameMsg.printLineInfo("Proposition de l'IA - " + (nbTests - currentTest) + " essai(s) restant(s) : " + combination.valueToString(combination.getGuessValue()));
	}
	
	/**
	 * Ask a response to human for a given combination
	 * 
	 * @param combination		combination to guess
	 */
	private void askResponseToHuman(Combination combination) {
			//ask for an answer to proposition done by ai
			gameMsg.printInfo(" -> votre réponse : ");
			human.checkCombination(combination);
	}
	
	/**
	 * Ask a response to ai for a given combination
	 * 
	 * @param combination		combination to guess
	 */
	private void askResponseToAI(Combination combination) {
		ai.checkCombination(combination);
		gameMsg.printLineInfo(" -> réponse de l'IA : " + combination.valueToString(combination.getResponseValue()));
		
		
	}
}
