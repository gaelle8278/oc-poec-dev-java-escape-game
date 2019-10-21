package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.combination.impl.Combination;
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
	private PlayerFactory playerFactory = new PlayerFactory();
	private int modeDev;
	private int nbTests;
	private int combinationLength;
	private Player playerA;
	private Player playerB;
	private Combination combinationPlayerA;
	private Combination combinationPlayerB;
	
	/**
	 * Init factory with data required to build Mode object
	 * 
	 * @throws IllegalPropertiesValueException 
	 */
	public void init() throws IllegalPropertiesValueException {
		loadConfig();
		loadObjects();	
	}

	/**
	 * Give concrete object according a mode type
	 * 
	 * @param modeType
	 * @return
	 */
	public Mode getMode(String modeType) {
		Mode gameMode = null;
		switch(modeType) {
			case  Parameter.CHALLENGER_MODE :
				gameMode = new ChallengerMode(playerA, playerB, combinationPlayerA, nbTests, modeDev);
				break;
			case Parameter.DEFENDER_MODE:
				gameMode = new DefenderMode(playerA, playerB, combinationPlayerB, nbTests, modeDev);
				break;
			case Parameter.DUEL_MODE:
				gameMode = new DuelMode(playerA, playerB, combinationPlayerA, combinationPlayerB, nbTests, modeDev);
				break;
			default:
				throw new IllegalArgumentException("Mode de jeu inconnu");
				
		}
		return gameMode;
	}
	
	
	/**
	 * Loads properties required to build Mode object
	 * 
	 * @throws IllegalPropertiesValueException
	 */
	private void loadConfig() throws IllegalPropertiesValueException {
		PropertiesLoader gameProps = new PropertiesLoader();
		gameProps.loadProperties("game.properties");
		modeDev=gameProps.getModeDev();
		nbTests=gameProps.getNumberTests();
		combinationLength=gameProps.getCombinationLength();
	}
	
	/**
	 * Creates objects required to build Mode object
	 */
	private void loadObjects() {
		playerA = playerFactory.getPlayer(Parameter.AI_PLAYER);
		playerB = playerFactory.getPlayer(Parameter.HUMAN_PLAYER);
		
		combinationPlayerA = new Combination(combinationLength);
		combinationPlayerB = new Combination(combinationLength);
		
	}

	

}
