package dev.gaellerauffet.escapegame.game;

import dev.gaellerauffet.escapegame.exception.IllegalPropertiesValueException;
import dev.gaellerauffet.escapegame.exception.MenuOptionException;
import dev.gaellerauffet.escapegame.menu.Menu;
import dev.gaellerauffet.escapegame.menu.impl.EndMenu;
import dev.gaellerauffet.escapegame.menu.impl.StartMenu;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.mode.GameMode;
import dev.gaellerauffet.escapegame.mode.impl.GameModeFactory;
import dev.gaellerauffet.escapegame.util.Parameter;

/**
 * Manage the course of a game
 * @author gaelle
 *
 */
public class Game {

	private String selectedMode;
	private DisplayMessage displayMsg;
	private LogMessage logMsg;
	private StartMenu startMenu;
	private EndMenu endMenu;
	private GameModeFactory gameModeFactory;

	public Game() {
		displayMsg = new DisplayMessage();
		logMsg = new LogMessage();
		startMenu = new StartMenu();
		endMenu = new EndMenu();
		gameModeFactory = new GameModeFactory();
	}

	/**
	 * Load an application
	 */
	public void load() {
		logMsg.infoLine("Initialisation de l'application");
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
	 * Executes all steps of a game.
	 * 
	 * Display start menu and execute steps of a game mode
	 */
	private void run() {
		startMenu.display();
		String selectedStartOption = getSelectedOption(startMenu);
		exitIfQuitIsSelected(startMenu, selectedStartOption);
		
		selectedMode=selectedStartOption;
		runMode();
	}
	
	/**
	 * Execute a game Mode
	 * 
	 * Get and execute an already selected game mode then display end menu and execute end option
	 */
	private void runMode() {
		GameMode gameMode = gameModeFactory.getMode(selectedMode);
		gameMode.run();

		endMenu.display();
		String selectedEndOption = getSelectedOption(endMenu);
		exitIfQuitIsSelected(endMenu, selectedEndOption);

		runEndOption(selectedEndOption);
	}


	/**
	 * Exit application if "quit" option is selected in the given menu
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
			logMsg.infoLine("Item menu sélectionné : " + selectedOption + " = "
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
				displayMsg.errorLine(e.getMessage());
				displayMsg.info(menu.getMenuTitle() + " ");
				logMsg.errorLine("Option de menu invalide : " + selectedOption);
			}
		}

		return selectedOption;
	}

	/**
	 * Run the given end option game
	 * 
	 * @param endOption
	 */
	private void runEndOption(String option) {
		if (option.equals(Parameter.REPLAY_OPTION)) {
			run();
		} else if (option.equals(Parameter.REPLAY_SAME_MODE_OPTION)) {
			runMode();
		}

	}

	/**
	 * Init factory that creates game mode object
	 */
	private void initModeFactory() {
		try {
			gameModeFactory.loadConfig();
		} catch (IllegalPropertiesValueException e) {
			displayMsg.errorLine(e.getMessage());
			displayMsg.infoLine("Sortie du programme");
			logMsg.errorLine(e.getMessage()); //ajouter méthode dans logMsg pour log la stacktrace loe.printStackTrace() 
			logMsg.infoLine("Sortie du programme");
			System.exit(0);
		}
	}

}
