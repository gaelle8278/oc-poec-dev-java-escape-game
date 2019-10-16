package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.config.PropertiesLoader;
import dev.gaellerauffet.escapegame.exceptions.IllegalPropertiesValueException;
import dev.gaellerauffet.escapegame.game.Combination;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.player.impl.PlayerFactory;
import dev.gaellerauffet.escapegame.util.Parameter;

public class ModeFactory {
	int modeDev;
	int nbTests;
	int combinationLength;
	PlayerFactory playerFactory = new PlayerFactory();

	
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
		Player playerA = playerFactory.getPlayer(Parameter.AI_PLAYER);
		Player playerB = playerFactory.getPlayer(Parameter.HUMAN_PLAYER);
		switch(modeType) {
			case  Parameter.CHALLENGER_MODE :
				gameMode = new ChallengerMode(playerA, playerB, combinationLength, nbTests, modeDev);
				break;
			case Parameter.DEFENDER_MODE:
				gameMode = new DefenderMode(playerB, playerA, combinationLength, nbTests, modeDev);
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
