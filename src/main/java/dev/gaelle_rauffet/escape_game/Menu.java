package dev.gaelle_rauffet.escape_game;

public class Menu {
	private Message msgManager = new Message();

	/**
	 * Displays menu at starting
	 */
	public void displayStartMenu() {
		String menuTitle = ("Choissisez le mode jeu :");
		String[] menuOptions = {"challenger", "défenseur", "duel"};
		
		this.displayMenu(menuTitle, menuOptions);
	}
	
	/**
	 * Display menu at ending 
	 */
	public void displayEndMenu() {
		String menuTitle = ("Fin de partie : ");
		String[] menuOptions = {"Rejouer au même mode","Rejouer avec un mode différent","Quitter l'application"};
		
		this.displayMenu(menuTitle, menuOptions);
	}
	
	/**
	 * Display a menu
	 * @param title			menu title
	 * @param options		options of menu
	 */
	private void displayMenu(String title, String [] options) {
		msgManager.printInfo(title);
		for(int i = 0; i < options.length; i++) {
			msgManager.printInfo((i+1) + " - " + options[i]);
		}
		
	}
	
	

}
