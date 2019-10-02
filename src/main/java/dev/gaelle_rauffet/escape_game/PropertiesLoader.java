package dev.gaelle_rauffet.escape_game;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class PropertiesLoader {
	
	private Properties prop;
	private Message msgManager;
	
	
	public PropertiesLoader() {
		this.prop = new Properties();
		this.msgManager  = new Message();
	}
	
	/**
	 * Loads properties from a .properties file in classpath
	 */
	public void loadProperties(String filename) {
		try {
			InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(filename);

            //load a properties file from class path, inside static method
            prop.load(input);

        } catch (IOException e) {
        	msgManager.logError("Erreur récupération fichier de properties", e);
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
