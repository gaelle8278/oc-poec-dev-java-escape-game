package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.config.PropertiesLoader;
import dev.gaellerauffet.escapegame.exceptions.IllegalPropertiesValueException;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.player.impl.PlayerFactory;
import dev.gaellerauffet.escapegame.util.Parameter;

/**
 * Create game mode object
 * @author gaelle
 *
 */
public class ModeFactory {
	private int modeDev;
	private int nbTests;
	private int combinationLength;
	private PlayerFactory playerFactory = new PlayerFactory();

	/**
	 * Loads properties required for game mode
	 * 
	 * @throws IllegalPropertiesValueException
	 */
	public void loadConfig() throws IllegalPropertiesValueException {
		PropertiesLoader gameProps = new PropertiesLoader();
		gameProps.loadProperties("game.properties");
		modeDev=gameProps.getModeDev();
		nbTests=gameProps.getNumberTests();
		combinationLength=gameProps.getCombinationLength();
	}
	
	/**
	 * Give concrete object according a mode type
	 * 
	 * @param modeType
	 * @return
	 */
	public Mode getMode(String modeType) {
		Mode gameMode = null;
		Player playerA = playerFactory.getPlayer(Parameter.AI_PLAYER);
		Player playerB = playerFactory.getPlayer(Parameter.HUMAN_PLAYER);
		switch(modeType) {
			case  Parameter.CHALLENGER_MODE :
				gameMode = new ChallengerMode(playerA, playerB, combinationLength, nbTests, modeDev);
				break;
			case Parameter.DEFENDER_MODE:
				gameMode = new DefenderMode(playerA, playerB, combinationLength, nbTests, modeDev);
				break;
			case Parameter.DUEL_MODE:
				gameMode = new DuelMode(playerA, playerB, combinationLength, nbTests, modeDev);
				break;
			default:
				throw new IllegalArgumentException("Mode de jeu inconnu");
				
		}
		return gameMode;
	}


}
