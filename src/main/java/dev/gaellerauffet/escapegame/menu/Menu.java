package dev.gaellerauffet.escapegame.menu;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import dev.gaellerauffet.escapegame.exception.MenuOptionException;
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
	 * 
	 * Display title then option associated to menu
	 */
	public void display() {
		displayMessage.infoLine(this.menuTitle);
		for (Map.Entry<String, String> option : menuOptions.entrySet()) {
			displayMessage.infoLine(option.getKey() + " - " + option.getValue());
		}
	}
	
	/**
	 * Get option selected among menu options
	 * @param menuOptions		list of menu items
	 * @return
	 * @throws MenuOptionException 
	 */
	public String getSelectedItem() throws MenuOptionException {
		String selectedItem = sc.nextLine();
        checkValiditySelectedItem(selectedItem);
       
        return selectedItem;
	}
	
	
	/**
	 * Get label associated to a given menu option value
	 * @param selectedItem
	 * @return
	 */
	public String getLabelSelectedItem(String selectedItem) {
		return menuOptions.get(selectedItem);
	}
	
	/**
	 * Check if a given menu option corresponds to valid option according to business rules
	 * @param selectedItem
	 * @throws MenuOptionException
	 */
	protected void checkValiditySelectedItem(String selectedItem) throws MenuOptionException {
		if(! menuOptions.containsKey(selectedItem.toUpperCase()) ) {
			throw new MenuOptionException("Vous n'avez pas choisi une option de menu valide.");
		}
	}

	public String getMenuTitle() {
		return menuTitle;
	};
	
	

}
