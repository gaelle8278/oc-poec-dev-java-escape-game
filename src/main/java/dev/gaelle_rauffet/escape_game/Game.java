package dev.gaelle_rauffet.escape_game;



public class Game {
	int modeDev;
	int nbTests;
	int combinationLength;
	Message gameMsg;
	Menu gameMenu;
	
	
	
	public Game() {
		this.gameMsg = new Message();
		this.gameMenu = new Menu();
	}
	
	/**
	 * Run a game
	 */
	public void run() {
		gameMsg.logInfo("Démarrage du jeu");
		loadConfig();
		gameMenu.displayStartMenu();
		int gameMode = gameMenu.getSelectedStartMenuItem();
		runMode(gameMode);
		gameMenu.displayEndMenu();
		int endOption = gameMenu.getSelectedEndMenuItem();
		runEndOption(endOption);
		gameMsg.logInfo("Fin du jeu");
	}
	
	

	/**
	 * Load a configuration file
	 */
	private void loadConfig() {
		// load a properties files
		PropertiesLoader gameProps = new PropertiesLoader();
		gameProps.loadProperties("game.properties");
		// set attributes with properties values
		modeDev=Integer.parseInt(gameProps.getPropValue("modeDeveloper"));
		nbTests=Integer.parseInt(gameProps.getPropValue("numberTests"));
		combinationLength=Integer.parseInt(gameProps.getPropValue("combinationLength"));
		
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
		gameMsg.printInfo("Pas encore implémenté");
		gameMsg.logInfo("Pas encore implémenté");
		System.exit(0);
		
	}

	/**
	 * Manage the "challenger" mode of the game
	 */
	private void runChallengerMode() {
		gameMsg.printInfo("Démarrage mode challenger");
		gameMsg.logInfo("Mode de jeu : challenger");
		
		//set players
		AI ai = new AI();
		Human human = new Human();
		
		//set the combination to guess
		int[] combinationToFind = ai.setCombination(combinationLength);
		String strCombination = this.getStringCombinationFromArray(combinationToFind);
		if(modeDev == 1) {
			gameMsg.printInfo("(combinaison secrète : " + strCombination + ")");
		}
		gameMsg.logInfo("combinaison définit par l'ia : " + strCombination);
		
		//until the answer is good or there is no more test
		boolean responseIsGood = false;
		int currentTest = 1;
		String[] responseCombination = new String[combinationLength];
		while(currentTest <= nbTests && !responseIsGood) {
			int[] combinationTest = this.validCombination(human.guessCombination(responseCombination, combinationLength), combinationLength);
			responseCombination = ai.checkCombination(combinationTest, combinationToFind);
			responseIsGood = this.checkResponse(responseCombination);
			
			//messages
			String strCombinationTest = this.getStringCombinationFromArray(combinationTest);
			String strResponseCombination = this.getStringCombinationFromArray(responseCombination);
			gameMsg.printInfo("Proposition : " + strCombinationTest + " -> Réponse : " + strResponseCombination);
			gameMsg.logInfo("essai " + currentTest + " combinaison donnée par le joueur : " + strCombinationTest + "/ Réponse faites par l'ia " + strResponseCombination);
			
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
	private int[] validCombination(int[] combination, int size) {
		int[] tabCombination = new int[size];
		//check length of combination
		if(combination.length == size ) {
			for(int i=0; i < size; i++) {
				// check value between 0 to 9
				if(combination[i] >= 0 && combination[i] < 10) {
					tabCombination[i] = combination[i];
				} else {
					// throw Exception "wrong value"
				}
			}
		} else {
			//throw Exception "wrong length"
		}
		
		return tabCombination;
	}
	
	/**
	 * Check response given by AI corresponds to a valid response
	 * 
	 * @param responseTest
	 * @return
	 */
	private boolean checkResponse(String[] responseTest) {
		boolean check = true;
		for(int i = 0; i < responseTest.length; i++) {
			if(responseTest[i] != "=") {
				check = false;
				break;
			}
		}
		return check;
	}

}
