package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.game.Combination;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;

public class DefenderMode implements Mode {
	private int modeDev;
	private int nbTests;
	private Player humanPlayer;
	private Player aiPlayer;
	private Combination combinationHumanPlayer;
	private DisplayMessage displayMsg;
	private LogMessage logMsg;
	private Formater formater;

	public DefenderMode(Player humanPlayer, Player aiPlayer, int nbTests, int combinationLength, int modeDev) {
		this.nbTests = nbTests;
		this.modeDev = modeDev;
		this.humanPlayer = humanPlayer;
		this.aiPlayer = aiPlayer;
		this.combinationHumanPlayer = new Combination(combinationLength); 
		this.displayMsg = new DisplayMessage();
		this.logMsg = new LogMessage();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}
