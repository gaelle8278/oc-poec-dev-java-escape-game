package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.util.Parameter;

public class ModeFactory {
	
	public ModeFactory() {
		loadConfig();
	}
	
	private void loadConfig() {
		
		
	}

	public Mode getMode(int modeType) {
		Mode gameMode = null;
		switch(modeType) {
			case  Parameter.CHALLENGER_MODE :
				gameMode = new ChallengerMode();
				break;
			case Parameter.DEFENDER_MODE:
				gameMode = new DefenderMode();
				break;
			case Parameter.DUEL_MODE:
				gameMode = new DuelMode();
				break;
			default:
				throw new IllegalArgumentException("Mode de jeu inconnu");
				
		}
		return gameMode;
	}

}
