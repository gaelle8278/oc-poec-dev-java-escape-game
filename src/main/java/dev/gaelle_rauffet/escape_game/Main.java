package dev.gaelle_rauffet.escape_game;

public class Main {
	
	public static void main(String[] args) {
		Message gameMsg = new Message();
		Game game = new Game();
		gameMsg.logInfo("Démarrage du jeu");
		game.initAndRun();
		gameMsg.logInfo("Fin du jeu");
	}

}
