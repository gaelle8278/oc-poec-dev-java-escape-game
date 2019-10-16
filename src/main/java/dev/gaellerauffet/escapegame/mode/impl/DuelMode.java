package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.game.Combination;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;

public class DuelMode implements Mode {
	private int modeDev;
	private int nbTests;
	private Player humanPlayer;
	private Player aiPlayer;
	private Combination combinationAiPlayer;
	private Combination combinationHumanPlayer;
	private DisplayMessage displayMsg;
	private LogMessage logMsg;
	private Formater formater;
	
	public DuelMode(Player humanPlayer, Player aiPlayer, int nbTests, int combinationLength, int modeDev) {
		this.nbTests = nbTests;
		this.modeDev = modeDev;
		this.combinationHumanPlayer = new Combination(combinationLength);
		this.combinationAiPlayer = new Combination(combinationLength);
		this.displayMsg = new DisplayMessage();
		this.logMsg = new LogMessage();
	}

	@Override
	public void run() {
		displayMsg.infoLine("Mode duel");
	}

	

}
