package dev.gaellerauffet.escapegame.menu;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import dev.gaellerauffet.escapegame.exceptions.MenuOptionException;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.util.Parameter;

public abstract class Menu {
	// a menu displays options
	protected DisplayMessage displayMessage = new DisplayMessage();
	// a menu read input to get the selected option
	protected Scanner sc = new Scanner(System.in);
	
	protected String menuTitle;
	protected Map<String, String> menuOptions = new LinkedHashMap<String, String>();
	
	/**
	 * Display the menu
	 */
	public void display() {
		displayMessage.printLineInfo(this.menuTitle);
		for (Map.Entry<String, String> option : menuOptions.entrySet()) {
			displayMessage.printLineInfo( option.getKey() + " - " + option.getValue());
		}
		
	}
	
	/**
	 * Get option selected among menu options
	 * @param menuOptions		list of menu items
	 * @return
	 * @throws MenuOptionException 
	 */
	public String getSelectedItem() throws MenuOptionException {
		String selectedItem = "";
        //boolean responseCheck = false;
       
        selectedItem = sc.nextLine();
        checkValiditySelectedItem(selectedItem);
       
        return selectedItem;
	}
	
	/**
	 * Exit program if it's the item selected in list options
	 * @param menuOptions
	 * @param choice
	 */
	public void checkifExitProgram(int choice) {
		//@TODO change to better process : here based on convention ie last option is for exit
		/*if(choice == menuOptions.size()) {
			msgManager.printLineInfo("Bye bye !");
			msgManager.logInfo("Sortie du programme");
			System.exit(0);
		}*/
		
		
	}
	
	public String getLabelSelectedItem(String selectedItem) {
		return menuOptions.get(selectedItem);
	}
	
	protected void checkValiditySelectedItem(String selectedItem) throws MenuOptionException {
		if(! menuOptions.containsKey(selectedItem.toUpperCase()) ) {
			throw new MenuOptionException();
		}
		
	};

}
