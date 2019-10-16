package dev.gaellerauffet.escapegame.mode;

import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;

public interface Mode {
	int modeDev = -1;
	int combinationLength = -1;
	int nbTests = -1;
	
	DisplayMessage displayMsg = null;;
	LogMessage logMsg = null;
	
	public void run();
}
