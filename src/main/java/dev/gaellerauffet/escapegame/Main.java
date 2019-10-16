package dev.gaellerauffet.escapegame;

import dev.gaellerauffet.escapegame.game.Game;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;

public class Main {
	
	public static void main(String[] args) {
		DisplayMessage gameMsg = new DisplayMessage();
		Game game = new Game();
		gameMsg.logInfo("Démarrage du jeu");
		game.run(true);
		gameMsg.logInfo("Fin du jeu");
	}

}
