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

	/**
	 * Get "modeDevelopper" prrperty value
	 * @return
	 */
	public int getModeDev() {
		int modeDev= Integer.parseInt(this.getPropValue("modeDeveloper"));
		if(modeDev !=0 && modeDev!=1) {
			throw new IllegalGamePropertiesValue("La valeur du paramètre \"modeDeveloper\" doit être 0 ou 1");
		}
		return modeDev;
		
	}

	/**
	 * Get "numberTests" property value
	 * @return
	 */
	public int getNumberTests() {
		int nbTests= Integer.parseInt(this.getPropValue("numberTests"));
		if(nbTests < 0 || nbTests > 20) {
			throw new IllegalGamePropertiesValue("La valeur du paramètre \"numberTests\" doit être compris entre 1 et 20");
		}
		return nbTests;
	}


	public int getCombinationLength() {
		int combinationLength =  Integer.parseInt(getPropValue("combinationLength"));
		if(combinationLength < 0 || combinationLength > 10) {
			throw new IllegalGamePropertiesValue("La valeur du paramètre \"combinationLength\" doit être compris entre 1 et 10");
		}
		return combinationLength;
	}

	
	

}
