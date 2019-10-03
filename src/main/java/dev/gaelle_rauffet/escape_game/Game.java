package dev.gaelle_rauffet.escape_game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	int modeDev;
	int nbTests;
	int combinationLength;
	Message gameMsg;
	int gameMode;
	
	
	
	public Game() {
		this.gameMsg = new Message();
	}
	
	/**
	 * Init game parameters and run the game
	 */
	public void initAndRun() {
		loadConfig();
		this.run();
		
	}
	
	/**
	 * Run the game
	 */
	private void run() {
		this.gameMode = getMode();
		runMode(gameMode);
		int endOption = getEndOption();
		runEndOption(endOption);
	}
	
	
	/**
	 * Get the game mode selected
	 * @return
	 */
	private int getMode() {
		Menu startMenu = this.setStartMenu();
		startMenu.display();
		int gameMode = startMenu.getSelectedItem();
		
		return gameMode;
	}

	/**
	 * Get the end option selected
	 * @return
	 */
	private int getEndOption() {
		Menu endMenu = this.setEndMenu();
		endMenu.display();
		int endOption = endMenu.getSelectedItem();
		
		return endOption;
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
		} catch(NumberFormatException e){
			gameMsg.printInfo("Paramètres du jeu non valide. Sortie du programme.");
			gameMsg.logError("Paramètres du jeu non valide. Sortie du programme.");
			System.exit(0);
		} catch (IllegalGamePropertiesValue e) {
			gameMsg.printInfo("Paramètres du jeu non valide : " + e.getMessage() + ". Sortie du programme.");
			gameMsg.logError("Paramètres du jeu non valides. " + e.getMessage() + ". Sortie du programme.");
			System.exit(0);
		} catch (NullPointerException e) {
			gameMsg.printInfo("Le fichier de propriétés ne peut pas être lu. Sortie du programme.");
			gameMsg.logError("Erreur récupération fichier de propriétés. Sortie du programme.");
			System.exit(0);
        } catch (IOException e) {
        	gameMsg.printInfo("Le fichier de propriétés ne peut pas être lu. Sortie du programme.");
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
		Menu startMenu = new Menu(menuTitle, menuOptions);
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
		Menu endMenu = new Menu(menuTitle, menuOptions);
		return endMenu;
	}

	/**
	 * Run a game mode
	 * 
	 * @param mode
	 */
	private void runMode(int mode) {
		if(mode == 1) {
			runChallengerMode();
		} else {
			gameMsg.printInfo("Mode pas encore implémenté");
			gameMsg.logInfo("Mode pas encore implémenté");
		}
		
	}
	
	/**
	 * Run an end option game
	 * 
	 * @param endOption
	 */
	private void runEndOption(int endOption) {
		if(endOption == 1) {
			runMode(endOption);
		} else if (endOption == 2 ) {
			run();
		}
		
	}

	/**
	 * Manage the "challenger" mode of the game
	 */
	private void runChallengerMode() {
		gameMsg.printInfo("Démarrage mode challenger");
		gameMsg.logInfo("Mode de jeu : challenger");
		
		//1- set required elements to play challenger mode
		AI ai = new AI();
		Human human = new Human();
		Combination combinationToFind = new Combination(combinationLength);
		
		//2- the ai set the value for combination
		ai.setValueCombination(combinationToFind);
		//int[] combinationToFind = ai.setCombination(combinationLength);
		//String strCombination = this.getStringCombinationFromArray(combinationToFind);
		String strCombination = combinationToFind.valueToString(combinationToFind.getValue());
		if(modeDev == 1) {
			gameMsg.printInfo("(combinaison secrète : " + strCombination + ")");
		}
		gameMsg.logInfo("combinaison définit par l'ia : " + strCombination);
		
		//3- human try to guess the combination = until the answer is good or there is no more test
		boolean responseIsGood = false;
		int currentTest = 1;
		while(currentTest <= nbTests && !responseIsGood) {
			try {
				//Combination userResponseCombination = human.guessCombination(combinationIndications, combinationLength);
				human.guessCombination(combinationToFind);
				//int[] combinationTest = this.validCombination(human.guessCombination(responseCombination, combinationLength), combinationLength);
				
				ai.checkCombination(combinationToFind);
				responseIsGood = combinationToFind.checkTest();
				
				//messages
				//String strCombinationTest = this.getStringCombinationFromArray(userResponseCombination.getValue());
				String strCombinationTest = combinationToFind.valueToString(combinationToFind.getGuessValue());
				//String strResponseCombination = this.getStringCombinationFromArray(combinationIndications);
				//String strResponseCombination = combinationToFind.responseTestValueToString();
				String strResponseCombination = combinationToFind.valueToString(combinationToFind.getResponseValue());
				gameMsg.printInfo("Proposition (" + currentTest + ") : " + strCombinationTest + " -> Réponse : " + strResponseCombination);
				gameMsg.logInfo("essai " + currentTest + " combinaison donnée par le joueur : " + strCombinationTest + "/ Réponse faites par l'ia " + strResponseCombination);
				
			} catch (IndexOutOfBoundsException e) {
				gameMsg.printInfo("La combinaison est trog longue");
				gameMsg.logError("essai " + currentTest + " la combination  trog longue");
			} catch (IllegalCombinationItem e) {
				//catch custom array if try to get char into int array
				gameMsg.printInfo( e.getMessage());
				gameMsg.logError("essai " + currentTest + " " + e.getMessage());
			}
			//new test if good or bad combination format
			currentTest++;
		}
		
		if(responseIsGood) {
			gameMsg.printInfo("Le joueur a gagné");
			gameMsg.logInfo("Fin de partie : le joueur a gagné");
		} else {
			gameMsg.printInfo("Le joueur a perdu. La combinaison était : " +  strCombination);
			gameMsg.logInfo("Fin de partie : le joueur a perdu");
		}
	}

	

}
