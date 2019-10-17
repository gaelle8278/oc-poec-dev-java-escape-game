package dev.gaellerauffet.escapegame.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


import dev.gaellerauffet.escapegame.exceptions.InvalidTestException;
import dev.gaellerauffet.escapegame.exceptions.IllegalPropertiesValueException;
import dev.gaellerauffet.escapegame.exceptions.InvalidResponseException;
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

}
