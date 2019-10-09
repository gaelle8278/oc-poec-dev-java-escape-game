package dev.gaellerauffet.escapegame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	int modeDev;
	int nbTests;
	int combinationLength;
	int gameMode;
	int endOption;
	Message gameMsg;
	Menu startMenu;
	Menu endMenu;
	
	public Game() {
		gameMsg = new Message();
		loadConfig();
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
			setMode = true;;
		} 
	
		run(setMode);
		
		
		
	}

	/**
	 * Manage the "challenger" mode of the game
	 */
	private void runChallengerMode() {
		gameMsg.printInfo("Démarrage mode Challengeur");
		gameMsg.logInfo("Mode de jeu : Challengeur");
		
		//1- set required elements to play "challenger" mode
		AI ai = new AI();
		Human human = new Human();
		Combination combinationToFind = new Combination(combinationLength);
		
		//2- the ai set the value for combination
		ai.setValueCombination(combinationToFind);
		String strCombination = combinationToFind.valueToString(combinationToFind.getValue());
		if(modeDev == 1) {
			gameMsg.printInfo("(combinaison secrète : " + strCombination + ")");
		}
		gameMsg.logInfo("combinaison définit par l'IA : " + strCombination);
		
		//3- human try to guess the combination = until the answer is good or there is no more test
		boolean responseIsGood = false;
		int currentTest = 0;
		while(currentTest < nbTests && !responseIsGood) {
			try {
				gameMsg.printInfo("Votre propositon (combinaison à " + combinationToFind.getLength() + " chiffres) - " + (this.nbTests - currentTest) + " essai(s) restant(s) :");
				human.guessCombination(combinationToFind);
				
				ai.checkCombination(combinationToFind);
				responseIsGood = combinationToFind.checkTest();
				
				//messages
				String strCombinationTest = combinationToFind.valueToString(combinationToFind.getGuessValue());
				String strResponseCombination = combinationToFind.valueToString(combinationToFind.getResponseValue());
				gameMsg.printInfo("Proposition : " + strCombinationTest + " -> Réponse : " + strResponseCombination);
				gameMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par le joueur : " + strCombinationTest + "/ Réponse faites par l'IA " + strResponseCombination);
				
				//new test if proposition is well-formatted
				currentTest++;
			} catch (IllegalCombinationItem e) {
				//catch custom array if try to get char into int array
				gameMsg.printInfo( e.getMessage());
				gameMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
			}
			
		}
		
		if(responseIsGood) {
			gameMsg.printInfo("Combinaison trouvée ! Le joueur a gagné.");
			gameMsg.logInfo("Fin de partie mode Challenger : le joueur a gagné. Il a trouvé la combinaison.");
		} else {
			gameMsg.printInfo("Le joueur a perdu. La combinaison était : " +  strCombination);
			gameMsg.logInfo("Fin de partie mode Challenger : le joueur a perdu. Il n'a pas trouvé la combinaison.");
		}
	}
	
	/**
	 * Manage the "Défenseur" mode
	 */
	private void runDefenseMode() {
		gameMsg.printInfo("Démarrage mode Défenseur");
		gameMsg.logInfo("Mode de jeu : Défenseur");
		
		//1- set required elements to play "défenseur" mode
		AI ai = new AI();
		Human human = new Human();
		Combination combinationToFind = new Combination(combinationLength);
		
		//2- the human set the value for combination (in his head)
		if(modeDev == 1) {
			gameMsg.printInfo("(combinaison secrète : définie par le joueur)");
		}
		gameMsg.logInfo("combinaison définit par le joueur et connue de lui seul");
		
		//3- ai try to guess the combination = until the answer is good or there is no more test
		boolean responseIsGood = false;
		int currentTest = 0;
		while(currentTest < nbTests && !responseIsGood) {
			try {
				ai.guessCombination(combinationToFind);
				String strCombinationTest = combinationToFind.valueToString(combinationToFind.getGuessValue());
				gameMsg.printInfo("Proposition de l'IA - " + (this.nbTests - currentTest) + " essai(s) restant(s) : " + strCombinationTest);
				gameMsg.printInfo("Votre réponse :");
				human.checkCombination(combinationToFind);
				
				responseIsGood = combinationToFind.checkTest();
								
				//messages
				String strResponseCombination = combinationToFind.valueToString(combinationToFind.getResponseValue());
				gameMsg.printInfo("Proposition : " + strCombinationTest + " -> Réponse : " + strResponseCombination);
				gameMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par l'IA : " + strCombinationTest + " / Réponse faites par le joueur " + strResponseCombination);
								
				//new test 
				currentTest++;
			} catch (IllegalArgumentException e) {
				//Exception levée par ints() de Random lorsqu'un chiffre ne peut pas être trouvé de façon logique 
				//raison précise non connue donc non catchée manuellement dans human.checkCombination
				gameMsg.printInfo("La réponse est incohérente.");
				if(modeDev == 1) {
					gameMsg.logError("La réponse est incohérente.", e);
				} else {
					gameMsg.logError("La réponse est incohérente.");
				}
				//reset responseValue
				combinationToFind.resetResponseValue();
				//given back a try (because exception is raised the next round after human response)
				currentTest--;
			} catch (IllegalCombinationItem e){
				gameMsg.printInfo(e.getMessage());
				//gameMsg.logError("essai " + (currentTest + 1) + " " + e.getMessage());
				//reset responseValue
				combinationToFind.resetResponseValue();
			}
		}
					
		if(responseIsGood) {
			gameMsg.printInfo("Le joueur a perdu. L'IA a trouvé la combinaison.");
			gameMsg.logInfo("Fin de partie mode Défenseur : le joueur a perdu, l'IA a trouvé la combinaison.");
		} else {
			gameMsg.printInfo("Le joueur a gagné. L'IA n'a pas trouvé la combinaison.");
			gameMsg.logInfo("Fin de partie mode Défenseur : le joueur a gagné, l'IA n'a pas trouvé la combinaison.");
		}
		
	}

	/**
	 * Manage the "Duel" mode
	 */
	private void runDuelMode() {
		gameMsg.printInfo("Démarrage mode Duel");
		gameMsg.logInfo("Mode de jeu : Duel");
		
		//1- set required elements to play "défenseur" mode
		AI ai = new AI();
		Human human = new Human();
		Combination humanCombination = new Combination(combinationLength);
		Combination aiCombination = new Combination(combinationLength);
		
		//2- the human and ai set the value to find for its own combination 
		//human in his head
		if(modeDev == 1) {
			gameMsg.printInfo("(combinaison secrète définie par le joueur : connue de lui seul)");
		}
		gameMsg.logInfo("combinaison définit par le joueur et connue de lui seul");
		
		//the ai set the value for combination
		ai.setValueCombination(aiCombination);
		String strCombination = aiCombination.valueToString(aiCombination.getValue());
		if(modeDev == 1) {
			gameMsg.printInfo("(combinaison secrète définit par l'ia : " + strCombination + ")");
		}
		gameMsg.logInfo("combinaison définit par l'IA : " + strCombination);
		
		//3- ai try to guess the combination = until the answer is good or there is no more test
		boolean responseHumanIsGood = false;
		boolean responseAIIsGood = false;
		int currentTest = 0;
		while(currentTest < nbTests && !responseHumanIsGood && !responseAIIsGood) {
			
				//3-a each player set a test to guess combination
				gameMsg.printInfo("Votre propositon (combinaison à " + aiCombination.getLength() + " chiffres) - " + (this.nbTests - currentTest) + " essai(s) restant(s) :");
				human.guessCombination(aiCombination);
				String strCombinationTestHuman = aiCombination.valueToString(aiCombination.getGuessValue());
				
				ai.guessCombination(humanCombination);
				String strCombinationTestAI = humanCombination.valueToString(humanCombination.getGuessValue());
				gameMsg.printInfo("Proposition de l'IA - " + (this.nbTests - currentTest) + " essai(s) restant(s) : " + strCombinationTestAI);
				
						
				
				
				//3-b evaluation of proposals
				gameMsg.printInfo("Votre réponse : ");
				human.checkCombination(humanCombination);
				ai.checkCombination(aiCombination);
				
			
				//3-c display of answers
				String strResponseAI = aiCombination.valueToString(aiCombination.getResponseValue());
				gameMsg.printInfo("Proposition du joueur : " + strCombinationTestHuman + " -> Réponse : " + strResponseAI);
				gameMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par le joueur : " + strCombinationTestHuman + "/ Réponse faite par l'IA " + strResponseAI);
				
				String strResponseHuman = humanCombination.valueToString(humanCombination.getResponseValue());
				gameMsg.printInfo("Proposition de l'AI : " + strCombinationTestAI + " -> Réponse : " + strResponseHuman);
				gameMsg.logInfo("essai " + (currentTest + 1) + " combinaison donnée par l'IA : " + strCombinationTestAI + " / Réponse faite par le joueur " + strResponseHuman);	
				
				//3-d Vérification si la bonne réponse a été donnée
				responseAIIsGood = humanCombination.checkTest();
				responseHumanIsGood = aiCombination.checkTest();
				
				//new test 
				currentTest++;
			
		
		}
		
		if(responseHumanIsGood && responseAIIsGood) {
			gameMsg.printInfo("Egalité ! Le joueur et l'IA ont deviné la combinaison.  ");
			gameMsg.logInfo("Fin de partie mode Duel : égalité.");
		} else if(responseHumanIsGood) {
			gameMsg.printInfo("Le joueur a gagné. Il a trouvé la combinaison définit par l'IA.");
			gameMsg.logInfo("Fin de partie mode Duel : le joueur a gagné, il a trouvé la combinaison définit par l'IA.");
		} else if (responseAIIsGood){
			gameMsg.printInfo("L'IA a gagné. L'IA a trouvé la combinaison définit par le joueur.");
			gameMsg.logInfo("Fin de partie mode Duel : l'IA a gagné, elle a trouvé la combinaison définit par l'IA.");
		}
	}

	
	
}
