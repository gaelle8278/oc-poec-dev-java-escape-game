package dev.gaelle_rauffet.escape_game;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
	private Message msgManager = new Message();
	private Scanner sc = new Scanner(System.in);
	
	String startMenuTitle;
	List<String> startMenuOptions = new ArrayList<String>();;
	String endMenuTitle;
	List<String> endMenuOptions = new ArrayList<String>();
	

	/**
	 * Displays menu at starting
	 */
	public void displayStartMenu() {
		startMenuTitle = ("Choissisez le mode jeu :");
		startMenuOptions.add("Challenger");
		startMenuOptions.add("Défenseur");
		startMenuOptions.add("Duel");
		startMenuOptions.add("Quitter l'application");
		
		this.displayMenu(startMenuTitle, startMenuOptions);
	}
	
	/**
	 * Display menu at ending 
	 */
	public void displayEndMenu() {
		endMenuTitle = ("Fin de partie : ");
		endMenuOptions.add("Rejouer au même mode");
		endMenuOptions.add("Rejouer avec un mode différent");
		endMenuOptions.add("Quitter l'application");
		
		this.displayMenu(endMenuTitle, endMenuOptions);
	}
	
	/**
	 * Get selected item from start menu
	 * 
	 * @return
	 */
	public int getSelectedStartMenuItem() {
		int choice = getMenuiItem(startMenuOptions);
		checkifExitProgram(startMenuOptions, choice);
		
		return choice;
	}
	
	/**
	 * Get selected item from end menu
	 * 
	 * @return
	 */
	public int getSelectedEndMenuItem() {
		int choice = getMenuiItem(endMenuOptions);
		checkifExitProgram(endMenuOptions, choice);
		
		return choice;
	}
	
	/**
	 * Exit program if it's the item selected in list options
	 * @param menuOptions
	 * @param choice
	 */
	private void checkifExitProgram(List<String> menuOptions, int choice) {
		//@TODO change to better process : here based on convention ie last option is for exit
		if(choice == menuOptions.size()) {
			msgManager.printInfo("Bye bye !");
			msgManager.logInfo("Sortie du programme");
			System.exit(0);
		}
		
		
	}

	/**
	 * Get option selected in a menu
	 * @param menuOptions		list of menu items
	 * @return
	 */
	private int getMenuiItem(List<String> menuOptions) {
		int selectedItem = 0;
        boolean responseCheck = false;
        do {
            try {
                selectedItem = sc.nextInt();
                responseCheck = (selectedItem > 0 && selectedItem <= menuOptions.size());
            } catch (InputMismatchException e) {
                sc.next();
            }
            
            if( responseCheck ) {
                //msgManager.printInfo("Option sélectionnée : " + menuOptions.get(selectedItem - 1));
                msgManager.logInfo("Item sélectionné : " + selectedItem + " = " + menuOptions.get(selectedItem - 1));
            } else {
            	msgManager.printInfo("Vous n'avez pas choisi une option valide");
                msgManager.logInfo("Option de menu invalide : " + selectedItem);  
            }
        } while ( ! responseCheck  );



        return selectedItem;
	}
	
	/**
	 * Display a menu
	 * @param title			menu title
	 * @param endMenuOptions2		options of menu
	 */
	private void displayMenu(String title, List<String> menuOptions) {
		msgManager.printInfo(title);
		for(int i = 0; i < menuOptions.size(); i++) {
			msgManager.printInfo((i+1) + " - " + menuOptions.get(i));
		}
		
	}

	
	
	

}
