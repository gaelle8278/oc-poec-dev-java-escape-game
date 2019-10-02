package dev.gaelle_rauffet.escape_game;



public class Game {
	int modeDev;
	int nbTests;
	int combinationLength;
	Message gameMsg;
	
	
	
	public Game() {
		this.gameMsg = new Message();
	}
	
	/**
	 * Run a game
	 */
	public void run() {
		gameMsg.logInfo("Démarrage du jeu");
		loadConfig();
		displayStartdMsg();
		int gameMode = 1;
		runMode(gameMode);
		displayEndMsg();
		gameMsg.logInfo("Fin du jeu");
	}
	
	/**
	 * Load a configuration file
	 */
	private void loadConfig() {
		// load a properties files
		GameProperties gameProps = new GameProperties();
		gameProps.loadProperties();
		// set attributes with properties values
		modeDev=Integer.parseInt(gameProps.getPropValue("modeDeveloper"));
		nbTests=Integer.parseInt(gameProps.getPropValue("numberTests"));
		combinationLength=Integer.parseInt(gameProps.getPropValue("combinationLength"));
		
	}

	/**
	 * Display message at starting game
	 */
	private void displayStartdMsg() {
		String[] gameModes = {"challenger","défenseur","duel"};
		gameMsg.printInfo("Choississez le mode de jeu : ");
		for(int i = 0; i < gameModes.length; i++) {
			gameMsg.printInfo((i+1) + " - " + gameModes[i]);
		}
		
	}

	/**
	 * Display message at ending game
	 */
	private void displayEndMsg() {
		String[] endOptions = {"Rejouer au même mode","Rejouer avec un mode différent","Quitter l'application"};
		gameMsg.printInfo("Fin de partie : ");
		for(int i = 0; i < endOptions.length; i++) {
			gameMsg.printInfo((i+1) + " - " + endOptions[i]);
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
		gameMsg.printInfo("Mode challenger");
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
