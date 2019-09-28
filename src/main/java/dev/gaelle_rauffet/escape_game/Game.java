package dev.gaelle_rauffet.escape_game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Game {
	int modeDev = 1;
	int nbTests = 4;
	int nbX = 4;
	private static final Logger logger = LogManager.getLogger(Game.class);
	
	
	/**
	 * Run a game
	 */
	public void run() {
		loadConfig();
		displayStartdMsg();
		int gameMode = 1;
		runMode(gameMode);
		displayEndMsg();
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
	public void runMode(int mode) {
		
	}

}
