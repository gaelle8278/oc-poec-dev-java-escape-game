package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.combination.impl.Combination;
import dev.gaellerauffet.escapegame.config.PropertiesLoader;
import dev.gaellerauffet.escapegame.exceptions.impl.IllegalPropertiesValueException;
import dev.gaellerauffet.escapegame.mode.GameMode;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.player.impl.PlayerFactory;
import dev.gaellerauffet.escapegame.util.Parameter;

/**
 * Create game mode object
 * @author gaelle
 *
 */
public class GameModeFactory {
	private PlayerFactory playerFactory = new PlayerFactory();
	private int modeDev;
	private int nbTests;
	private int combinationLength;
	
	/**
	 * Loads properties required to build Mode object
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
	public GameMode getMode(String modeType) {
		GameMode gameMode = null;
		//objects required to build a game mode must be reset each time
		//loadObjects();
		switch(modeType) {
			case  Parameter.CHALLENGER_MODE :
				gameMode = getChallengerMode();
				break;
			case Parameter.DEFENDER_MODE:
				gameMode = getDefenderMode();
				break;
			case Parameter.DUEL_MODE:
				ChallengerMode gameModeChallenger = getChallengerMode();
				DefenderMode gameModeDefender = getDefenderMode();
				gameMode = new DuelMode(gameModeChallenger, gameModeDefender, nbTests);
				break;
			default:
				throw new IllegalArgumentException("Mode de jeu inconnu");
				
		}
		return gameMode;
	}
	
	/**
	 * Creates challenger Mode
	 */
	private ChallengerMode getChallengerMode() {
		Player playerA = playerFactory.getPlayer(Parameter.AI_PLAYER);
		Player playerB = playerFactory.getPlayer(Parameter.HUMAN_PLAYER);
		Combination combinationGame = new Combination(combinationLength);
		
		ChallengerMode gameMode = new ChallengerMode(playerA, playerB, combinationGame, nbTests, modeDev);
		
		return gameMode;
		
	}
	
	private DefenderMode getDefenderMode() {
		Player playerA = playerFactory.getPlayer(Parameter.AI_PLAYER);
		Player playerB = playerFactory.getPlayer(Parameter.HUMAN_PLAYER);
		Combination combinationGame = new Combination(combinationLength);
		
		DefenderMode gameMode = new DefenderMode(playerA, playerB, combinationGame, nbTests, modeDev);
		
		return gameMode;
		
	}

	

}
