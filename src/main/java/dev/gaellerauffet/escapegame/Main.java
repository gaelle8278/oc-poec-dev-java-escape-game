package dev.gaellerauffet.escapegame;

import dev.gaellerauffet.escapegame.game.Game;
import dev.gaellerauffet.escapegame.util.Message;

public class Main {
	
	public static void main(String[] args) {
		Message gameMsg = new Message();
		Game game = new Game();
		gameMsg.logInfo("Démarrage du jeu");
		game.run(true);
		gameMsg.logInfo("Fin du jeu");
	}

}
