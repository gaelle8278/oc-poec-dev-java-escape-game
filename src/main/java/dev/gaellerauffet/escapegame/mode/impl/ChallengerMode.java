package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.mode.Mode;

public class ChallengerMode implements Mode {
	int modeDev;
	int combinationLength;
	int nbTests;
	DisplayMessage displayMsg;
	LogMessage logMsg;

	public ChallengerMode(int nbTests, int combinationLength, int modeDev) {
		this.nbTests = nbTests;
		this.combinationLength = combinationLength;
		this.modeDev = modeDev;
		this.displayMsg = new DisplayMessage();
		this.logMsg = new LogMessage();
	}

	@Override
	public void run() {
		displayMsg.infoLine("Mode challenger");
		
	}

}
