package dev.gaellerauffet.escapegame.message.impl;

import dev.gaellerauffet.escapegame.message.Message;

/**
 * Display manage in standard output / to user interface
 * @author gaelle
 *
 */
public class DisplayMessage implements Message {
	/**
	 * Print a message in standard output
	 * @param string
	 */
	public void infoLine(String msg) {
		System.out.println(msg);
	}

	public void info(String msg) {
		System.out.print(msg);
	}
	
	public void errorLine(String msg) {
		System.out.println(msg);
	}
	
	public void errorLine(String msg, Exception e) {
		System.out.println(msg + e.getStackTrace());
	}	

}
