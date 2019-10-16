package dev.gaellerauffet.escapegame.menu.impl;

import dev.gaellerauffet.escapegame.menu.Menu;
import dev.gaellerauffet.escapegame.util.Parameter;

public class EndMenu extends Menu {

	public EndMenu() {
		this.menuTitle = "Faites votre choix :";
		this.menuOptions.put(Parameter.REPLAY_OPTION, "Rejouer");
		this.menuOptions.put(Parameter.REPLAY_SAME_MODE_OPTION, "Rejouer au même mode");
		this.menuOptions.put(Parameter.OPTION_QUIT, "Quitter l'application");

	}
}
