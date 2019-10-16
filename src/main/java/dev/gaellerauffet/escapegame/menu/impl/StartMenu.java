package dev.gaellerauffet.escapegame.menu.impl;

import java.util.HashMap;

import dev.gaellerauffet.escapegame.menu.Menu;
import dev.gaellerauffet.escapegame.util.Parameter;

public class StartMenu extends Menu {


	public StartMenu() {
		this.menuTitle = "Choissisez le mode jeu :";
		this.menuOptions.put(Parameter.CHALLENGER_MODE, "Mode Challenger");
		this.menuOptions.put(Parameter.DEFENDER_MODE, "Mode Défenseur");
		this.menuOptions.put(Parameter.DUEL_MODE, "Mode Duel");
		this.menuOptions.put(Parameter.OPTION_QUIT, "Quitter l'application");
		

	}

}
