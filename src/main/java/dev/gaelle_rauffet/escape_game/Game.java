package dev.gaelle_rauffet.escape_game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Game {
	int modeDev = 0;
	int nbTests = 4;
	int nbX = 4;
	private static final Logger logger = LogManager.getLogger(Game.class);
	
	
	/**
	 * Run a game
	 */
	public void run() {
		logger.info("Démarrage du jeu");
		loadConfig();
		displayStartdMsg();
		int gameMode = 1;
		runMode(gameMode);
		displayEndMsg();
		logger.info("Fin du jeu");
	}
	
	/**
	 * Load a configuration file
	 */
	private void loadConfig() {
		// load a properties files
		// set attributes with properties values
		
	}

	/**
	 * Display message at starting game
	 */
	private void displayStartdMsg() {
		String[] gameModes = {"challenger","défenseur","duel"};
		System.out.println("Choississez le mode de jeu : ");
		for(int i = 0; i < gameModes.length; i++) {
			System.out.println((i+1) + " - " + gameModes[i]);
		}
		
	}

	/**
	 * Display message at ending game
	 */
	private void displayEndMsg() {
		String[] endOptions = {"Rejouer au même mode","Rejouer avec un mode différent","Quitter l'application"};
		System.out.println("Fin de partie : ");
		for(int i = 0; i < endOptions.length; i++) {
			System.out.println((i+1) + " - " + endOptions[i]);
		}
		
	}

	/**
	 * Run a game mode
	 * 
	 * @param mode
	 */
	private void runMode(int mode) {
		if(mode == 1) {
			runChallengerMode();
		}
		
	}

	/**
	 * Manage the "challenger" mode of the game
	 */
	private void runChallengerMode() {
		System.out.println("Mode challenger");
		logger.info("Mode de jeu : challenger");
		
		//set players
		AI ai = new AI();
		Human human = new Human();
		
		//set the combination to guess
		int[] combinationToFind = ai.setCombination(nbX);
		String strCombination = this.getStringCombinationFromArray(combinationToFind);
		if(modeDev == 1) {
			System.out.println("(combinaison secrète : " + strCombination + ")");
		}
		logger.info("combinaison définit par l'ia : " + strCombination);
		
		//until the answer is good or there is no more test
		boolean responseIsGood = false;
		int currentTest = 1;
		while(currentTest <= nbTests && !responseIsGood) {
			int[] combinationTest = this.validCombination(human.guessCombination(), nbX);
			String[] responseCombination = ai.checkCombination(combinationTest, combinationToFind);
			responseIsGood = ai.checkResponse(responseCombination);
			
			//messages
			String strCombinationTest = this.getStringCombinationFromArray(combinationTest);
			String strResponseCombination = this.getStringCombinationFromArray(responseCombination);
			System.out.println("Proposition : " + strCombinationTest + " -> Réponse : " + strResponseCombination);
			logger.info("essai " + currentTest + " combinaison donnée par le joueur : " + strCombinationTest + "/ Réponse faites par l'ia " + strResponseCombination);
			
			currentTest++;
		}
		
		if(responseIsGood) {
			System.out.println("Le joueur a gagné");
			logger.info("Fin de partie : le joueur a gagné");
		} else {
			System.out.println("Le joueur a perdu. La combinaison était : " +  strCombination);
			logger.info("Fin de partie : le joueur a perdu");
		}
	}

	/**
	 * Concatenate elements from an array that contains integers
	 * 
	 * @param combination
	 */
	private String getStringCombinationFromArray(int[] combination) {
		String strCombination = "";
		for(int i=0; i < combination.length; i++ ) {
			strCombination += String.valueOf(combination[i]);
		}
		
		return strCombination;
		
	}
	
	/**
	 * Concatenate elements from an array that contains strings
	 * 
	 * @param combination
	 * @return
	 */
	private String getStringCombinationFromArray(String[] combination) {
		String strCombination = "";
		for(int i=0; i < combination.length; i++ ) {
			strCombination += combination[i];
		}
		
		return strCombination;
		
	}

	/**
	 * Check a given combination
	 * 
	 * @param combination
	 * @return
	 */
	private int[] validCombination(String combination, int size) {
		int[] tabCombination = new int[size];
		//check length of combination
		if(combination.length() == size ) {
			for(int i=0; i < size; i++) {
				if(Character.isDigit(combination.charAt(i))) {
					tabCombination[i] = Character.getNumericValue(combination.charAt(i));
				} else {
					//throw Exception "combination must only contains integer"
				}
			}
			
		} else {
			//throw Exception "wrong length"
		}
		
		return tabCombination;
	}

}
