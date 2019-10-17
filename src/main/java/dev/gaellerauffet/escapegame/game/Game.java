package dev.gaellerauffet.escapegame.game;

import dev.gaellerauffet.escapegame.exceptions.IllegalPropertiesValueException;
import dev.gaellerauffet.escapegame.exceptions.MenuOptionException;
import dev.gaellerauffet.escapegame.menu.Menu;
import dev.gaellerauffet.escapegame.menu.impl.EndMenu;
import dev.gaellerauffet.escapegame.menu.impl.StartMenu;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.mode.impl.ModeFactory;
import dev.gaellerauffet.escapegame.util.Parameter;

public class Game {

	String selectedMode;
	DisplayMessage displayMsg;
	LogMessage logMsg;
	StartMenu startMenu;
	EndMenu endMenu;
	ModeFactory modeFactory;

	public Game() {
		displayMsg = new DisplayMessage();
		logMsg = new LogMessage();
		startMenu = new StartMenu();
		endMenu = new EndMenu();
		modeFactory = new ModeFactory();
	}

	/**
	 * Load an application
	 */
	public void load() {
		logMsg.infoLine("Initialisation de l'appliaction");
		init();
		logMsg.infoLine("Démarrage de l'application");
		run();

	}

	/**
	 * Executes steps necessary to init a game application
	 */
	private void init() {
		initModeFactory();
		// may have some init actions in the future
	}

	/**
	 * Executes all steps of a game
	 */
	private void run() {

		startMenu.display();
		String selectedStartOption = getSelectedOption(startMenu);
		exitIfQuitIsSelected(startMenu, selectedStartOption);
		
		selectedMode=selectedStartOption;
		Mode gameMode = modeFactory.getMode(selectedMode);
		gameMode.run();
		
		endMenu.display();
		String selectedEndOption = getSelectedOption(endMenu);
		exitIfQuitIsSelected(endMenu, selectedEndOption);

		runEndOption(selectedEndOption);
	}
	
	/**
	 * Execute a game without reselect a game game mode
	 */
	private void runSameMode() {
		Mode gameMode = modeFactory.getMode(selectedMode);
		gameMode.run();

		endMenu.display();
		String selectedEndOption = getSelectedOption(endMenu);
		exitIfQuitIsSelected(endMenu, selectedEndOption);

		runEndOption(selectedEndOption);

	}


	/**
	 * 
	 * @param menu
	 * @param selectedOption
	 */
	private void exitIfQuitIsSelected(Menu menu, String selectedOption) {
		if (selectedOption.toUpperCase().equals(Parameter.OPTION_QUIT)) {
			displayMsg.infoLine("Bye bye !");
			logMsg.infoLine("Sortie du programme.");
			System.exit(0);
		} else {
			logMsg.infoLine("Item menu de début sélectionné : " + selectedOption + " = "
					+ menu.getLabelSelectedItem(selectedOption));
		}

	}

	/**
	 * Give the mode selected among some choices
	 * 
	 * @return
	 */
	private String getSelectedOption(Menu menu) {
		String selectedOption = "";
		while ("".equals(selectedOption)) {
			try {
				selectedOption = menu.getSelectedItem();
			} catch (MenuOptionException e) {
				displayMsg.errorLine("Vous n'avez pas choisi une option de menu valide.");
				logMsg.errorLine("Option de menu invalide : " + selectedOption);
			}
		}

		return selectedOption;
	}

	/**
	 * Run an end option game
	 * 
	 * @param endOption
	 */
	private void runEndOption(String option) {
		if (option.equals(Parameter.REPLAY_OPTION)) {
			run();
		} else if (option.equals(Parameter.REPLAY_SAME_MODE_OPTION)) {
			runSameMode();
		}

	}

	/**
	 * Init factory that creates game mode object
	 */
	private void initModeFactory() {
		try {
			modeFactory.loadConfig();
		} catch (IllegalPropertiesValueException e) {
			displayMsg.errorLine(e.getMessage());
			displayMsg.infoLine("Sortie du programme");
			logMsg.errorLine(e.getMessage());
			logMsg.infoLine("Sortie du programme");
			System.exit(0);
		}
	}

}
