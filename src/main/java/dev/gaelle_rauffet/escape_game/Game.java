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
		//modeDev=Integer.parseInt(gameProps.getPropValue("modeDeveloper"));
		try{
			modeDev=gameProps.getModeDev();
			nbTests=gameProps.getNumberTests();
			combinationLength=gameProps.getCombinationLength();
		} catch(NumberFormatException e){
			gameMsg.printInfo("Paramètres du jeu non valides. Sortie du programme.");
			gameMsg.logError("Paramètres du jeu non valides. Sortie du programme.");
			System.exit(0);
		} catch (IllegalGamePropertiesValue e) {
			gameMsg.printInfo("Paramètre du jeu non valide : " + e.getMessage() + ". Sortie du programme.");
			gameMsg.logError("Paramètres du jeu non valides. " + e.getMessage() + ". Sortie du programme.");
			System.exit(0);
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
				gameMsg.printInfo("Proposition : " + strCombinationTest + " -> Réponse : " + strResponseCombination);
				gameMsg.logInfo("essai " + currentTest + " combinaison donnée par le joueur : " + strCombinationTest + "/ Réponse faites par l'ia " + strResponseCombination);
				
			} catch (IndexOutOfBoundsException e) {
				gameMsg.printInfo("Proposition : la combinaison est trog longue");
				gameMsg.logError("essai " + currentTest + " la combination  trog longue");
			} catch (IllegalCombinationItem e) {
				//catch custom array if try to get char into int array
				gameMsg.printInfo("Proposition : la combinaison contient des caractères non valides");
				gameMsg.logError("essai " + currentTest + " la combinaison contient des caractères non valides");
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
