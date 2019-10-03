package dev.gaelle_rauffet.escape_game;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class PropertiesLoader {
	
	private Properties prop;
	
	
	public PropertiesLoader() {
		this.prop = new Properties();
	}
	
	/**
	 * Loads properties from a .properties file in classpath
	 * @throws IOException 
	 */
	public void loadProperties(String filename) throws IOException {
		InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(filename);

        //load a properties file from class path, inside static method
        prop.load(input);
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
	 * @throws IllegalGamePropertiesValue 
	 */
	public int getModeDev() throws IllegalGamePropertiesValue {
		int modeDev= Integer.parseInt(this.getPropValue("modeDeveloper"));
		if(modeDev !=0 && modeDev!=1) {
			throw new IllegalGamePropertiesValue("La valeur du paramètre \"modeDeveloper\" doit être 0 ou 1");
		}
		return modeDev;
		
	}

	/**
	 * Get "numberTests" property value
	 * @return
	 * @throws IllegalGamePropertiesValue 
	 */
	public int getNumberTests() throws IllegalGamePropertiesValue {
		int nbTests= Integer.parseInt(this.getPropValue("numberTests"));
		if(nbTests < 0 || nbTests > 20) {
			throw new IllegalGamePropertiesValue("La valeur du paramètre \"numberTests\" doit être compris entre 1 et 20");
		}
		return nbTests;
	}


	/**
	 * Get "combinationLength" property value
	 * @return
	 * @throws IllegalGamePropertiesValue 
	 */
	public int getCombinationLength() throws IllegalGamePropertiesValue {
		int combinationLength =  Integer.parseInt(getPropValue("combinationLength"));
		if(combinationLength < 0 || combinationLength > 10) {
			throw new IllegalGamePropertiesValue("La valeur du paramètre \"combinationLength\" doit être compris entre 1 et 10");
		}
		return combinationLength;
	}

	
	

}
