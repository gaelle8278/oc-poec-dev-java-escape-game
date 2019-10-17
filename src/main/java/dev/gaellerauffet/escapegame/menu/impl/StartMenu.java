package dev.gaellerauffet.escapegame.menu.impl;

import dev.gaellerauffet.escapegame.menu.Menu;
import dev.gaellerauffet.escapegame.util.Parameter;

/**
 * Start menu of application
 * @author gaelle
 *
 */
public class StartMenu extends Menu {

	public StartMenu() {
		this.menuTitle = "Choissisez le mode jeu :";
		this.menuOptions.put(Parameter.CHALLENGER_MODE, "Challenger");
		this.menuOptions.put(Parameter.DEFENDER_MODE, "Défenseur");
		this.menuOptions.put(Parameter.DUEL_MODE, "Duel");
		this.menuOptions.put(Parameter.OPTION_QUIT, "Quitter l'application");
	}

}
