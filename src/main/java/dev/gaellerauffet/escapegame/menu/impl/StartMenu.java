package dev.gaellerauffet.escapegame.menu.impl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dev.gaellerauffet.escapegame.menu.Menu;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;

public class StartMenu extends Menu {
	private DisplayMessage msgManager = new DisplayMessage();
	private Scanner sc = new Scanner(System.in);
	
	private String menuTitle;
	private List<String> menuOptions = new ArrayList<String>();
	

	public StartMenu(String title, List<String> options) {
		this.menuTitle = title;
		this.menuOptions = options;
	}
	
	/**
	 * Display the menu
	 */
	public void display() {
		msgManager.printLineInfo(this.menuTitle);
		for(int i = 0; i < menuOptions.size(); i++) {
			msgManager.printLineInfo((i+1) + " - " + menuOptions.get(i));
		}
		
	}

	
	/**
	 * Get selected item from the menu or exits if necessary
	 * 
	 * @return
	 */
	public int getSelectedItem() {
		int choice = getMenuiItem();
		checkifExitProgram(choice);
		
		return choice;
	}
	

	
	/**
	 * Exit program if it's the item selected in list options
	 * @param menuOptions
	 * @param choice
	 */
	private void checkifExitProgram(int choice) {
		//@TODO change to better process : here based on convention ie last option is for exit
		if(choice == menuOptions.size()) {
			msgManager.printLineInfo("Bye bye !");
			msgManager.logInfo("Sortie du programme");
			System.exit(0);
		}
		
		
	}

	/**
	 * Get option selected among menu options
	 * @param menuOptions		list of menu items
	 * @return
	 */
	private int getMenuiItem() {
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
                msgManager.logInfo("Item sélectionné : " + selectedItem + " = " + menuOptions.get(selectedItem - 1));
            } else {
            	msgManager.printLineInfo("Vous n'avez pas choisi une option de menu valide");
                msgManager.logInfo("Option de menu invalide : " + selectedItem);  
            }
        } while ( ! responseCheck  );



        return selectedItem;
	}
	
	
	
	
	

}
