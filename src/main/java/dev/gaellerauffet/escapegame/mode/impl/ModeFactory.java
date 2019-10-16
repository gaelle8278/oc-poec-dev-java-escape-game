package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.config.PropertiesLoader;
import dev.gaellerauffet.escapegame.exceptions.IllegalPropertiesValueException;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.util.Parameter;

public class ModeFactory {
	int modeDev;
	int nbTests;
	int combinationLength;

	
	public void loadConfig() throws IllegalPropertiesValueException {
		PropertiesLoader gameProps = new PropertiesLoader();
		gameProps.loadProperties("game.properties");
		modeDev=gameProps.getModeDev();
		nbTests=gameProps.getNumberTests();
		combinationLength=gameProps.getCombinationLength();
		
	}

	/**
	 * Give concrete object according modeType parameter
	 * 
	 * @param modeType
	 * @return
	 */
	public Mode getMode(String modeType) {
		Mode gameMode = null;
		switch(modeType) {
			case  Parameter.CHALLENGER_MODE :
				gameMode = new ChallengerMode(nbTests, combinationLength, modeDev);
				break;
			case Parameter.DEFENDER_MODE:
				gameMode = new DefenderMode(nbTests, combinationLength, modeDev);
				break;
			case Parameter.DUEL_MODE:
				gameMode = new DuelMode(nbTests, combinationLength, modeDev);
				break;
			default:
				throw new IllegalArgumentException("Mode de jeu inconnu");
				
		}
		return gameMode;
	}

}
