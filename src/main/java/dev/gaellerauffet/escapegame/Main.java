package dev.gaellerauffet.escapegame;

public class Main {
	
	public static void main(String[] args) {
		Message gameMsg = new Message();
		Game game = new Game();
		gameMsg.logInfo("Démarrage du jeu");
		game.run(true);
		gameMsg.logInfo("Fin du jeu");
	}

}
