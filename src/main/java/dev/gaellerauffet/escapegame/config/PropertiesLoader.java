package dev.gaellerauffet.escapegame.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import dev.gaellerauffet.escapegame.exceptions.IllegalPropertiesValueException;

public class PropertiesLoader {
	
	private Properties prop;
	
	
	public PropertiesLoader() {
		this.prop = new Properties();
	}
	
	/**
	 * Loads properties from a .properties file in classpath
	 * @throws IllegalPropertiesValueException 
	 * @throws IOException 
	 */
	public void loadProperties(String filename) throws IllegalPropertiesValueException  {
		try {
			InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(filename);
			 //load a properties file from class path, inside static method
	        prop.load(input);
		} catch (NullPointerException e) {
			throw new IllegalPropertiesValueException("Le fichier de propriétés est introuvable. Sortie du programme.", e);
        } catch (IOException e) {
        	throw new IllegalPropertiesValueException("Le fichier de propriétés est illisible. Sortie du programme.", e);
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
	 * Get "modeDevelopper" property value
	 * @return
	 * @throws IllegalPropertiesValueException 
	 */
	public int getModeDev() throws IllegalPropertiesValueException {
		int modeDev = -1;
	
		try {
			modeDev = Integer.parseInt(this.getPropValue("modeDeveloper"));
			if(modeDev !=0 && modeDev!=1) {
				throw new IllegalPropertiesValueException("Paramètre de l'application non valide : la valeur du paramètre \"modeDeveloper\" doit être 0 ou 1");
			}
		} catch (NumberFormatException e) {
			throw new IllegalPropertiesValueException("Paramètre de l'application non valide : la valeur du paramètre \"modeDeveloper\" doit être 0 ou 1", e);
		}
		return modeDev;
		
	}

	/**
	 * Get "numberTests" property value
	 * @return
	 * @throws IllegalPropertiesValueException 
	 */
	public int getNumberTests() throws IllegalPropertiesValueException {
		int nbTests = -1;
		try {
			nbTests= Integer.parseInt(this.getPropValue("numberTests"));
			if(nbTests < 0 || nbTests > 20) {
				throw new IllegalPropertiesValueException("Paramètre de l'application non valide : la valeur du paramètre \"numberTests\" doit être compris entre 1 et 20");
			}
		} catch (NumberFormatException e) {
			throw new IllegalPropertiesValueException("Paramètre de l'application non valide : la valeur du paramètre \"numberTests\" doit être compris entre 1 et 20");
		}
		return nbTests;
	}


	/**
	 * Get "combinationLength" property value
	 * @return
	 * @throws IllegalPropertiesValueException 
	 */
	public int getCombinationLength() throws IllegalPropertiesValueException {
		int combinationLength =  -1;
		try { 
			combinationLength = Integer.parseInt(getPropValue("combinationLength"));
			if(combinationLength < 0 || combinationLength > 10) {
				throw new IllegalPropertiesValueException("Paramètre de l'application non valide : la valeur du paramètre \"combinationLength\" doit être compris entre 1 et 10");
			}
		} catch (NumberFormatException e) {
			throw new IllegalPropertiesValueException("Paramètre de l'application non valide : la valeur du paramètre \"combinationLength\" doit être compris entre 1 et 10");
		}
		return combinationLength;
	}

}
