package dev.gaellerauffet.escapegame;

import dev.gaellerauffet.escapegame.game.Game;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;

public class Main {
	
	public static void main(String[] args) {
		LogMessage gameLog = new LogMessage();
		Game game = new Game();
		gameLog.infoLine("Démarrage du jeu");
		game.run();
		gameLog.infoLine("Fin du jeu");
	}

}
