package dev.gaellerauffet.escapegame.player.impl;

import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Parameter;

public class PlayerFactory {
	
	public Player getPlayer(int playerType) {
		Player player = null;
		switch(playerType) {
			case Parameter.AI_PLAYER:
				player = new AiPlayer();
				break;
			case Parameter.HUMAN_PLAYER:
				player = new HumanPlayer();
				break;
			default:
				throw new IllegalArgumentException("Type de joueur inconnu");
		}
		return player;
	}
}
