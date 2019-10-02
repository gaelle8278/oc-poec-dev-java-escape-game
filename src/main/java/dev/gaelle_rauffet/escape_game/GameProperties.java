package dev.gaelle_rauffet.escape_game;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class GameProperties {
	
	private Properties prop;
	private Message gameMsg;
	
	
	public GameProperties() {
		this.prop = new Properties();
		this.gameMsg  = new Message();
	}
	
	/**
	 * Loads properties from a .properties file in classpath
	 */
	public void loadProperties() {
		try {
			InputStream input = GameProperties.class.getClassLoader().getResourceAsStream("game.properties");

            //load a properties file from class path, inside static method
            prop.load(input);

        } catch (IOException e) {
        	gameMsg.logError("Erreur récupération fichier properties", e);
        } 
	}
	
	
	/**
	 * Returns value for the given properties key
	 * 
	 * @param propKey
	 * @return
	 */
	public String getPropValue(String propKey) {
		return prop.getProperty(propKey);
	}

}
