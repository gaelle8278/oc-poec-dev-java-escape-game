package dev.gaellerauffet.escapegame.mode;

import dev.gaellerauffet.escapegame.combination.impl.Combination;
import dev.gaellerauffet.escapegame.exception.InvalidGameLapItemException;
import dev.gaellerauffet.escapegame.message.impl.DisplayMessage;
import dev.gaellerauffet.escapegame.message.impl.LogMessage;
import dev.gaellerauffet.escapegame.player.Player;
import dev.gaellerauffet.escapegame.util.Formater;
import dev.gaellerauffet.escapegame.util.Parameter;

public abstract class Mode implements GameMode {
	protected int modeDev;
	protected int nbTests;
	protected Player humanPlayer;
	protected Player aiPlayer;
	protected Combination combination;
	protected DisplayMessage displayMsg = new DisplayMessage();
	protected LogMessage logMsg = new LogMessage();
	
	
	/**
	 * Executes steps required to run a complete game mode
	 */
	@Override
	public void run() {
		initMode();
		
		displayStartMsg();
		
		displayMsgBeforeRun();
		int resultGame = runMode();
		
		displayEndMsg(resultGame);
	}
	
	/**
	 * Display message when a game mode is loaded
	 */
	protected abstract void displayStartMsg();
	
	/**
	 * Executes actions required before a game mode can be run
	 */
	public abstract void initMode();
	
	/**
	 * Display message before a game mode starts
	 */
	public abstract void displayMsgBeforeRun();
	
	/**
	 * Display message when a game mode is terminated
	 * @param resultGame
	 */
	protected abstract void displayEndMsg(int resultGame);

	/**
	 * Run game mode : a run is a succession of laps
	 * @return
	 */
	protected int runMode() {
		int resultLap = Parameter.NO_WINNER;
		int currentLap = 0;
		while(currentLap < nbTests && resultLap == Parameter.NO_WINNER) {
			runLap(currentLap);
			logLap(currentLap);
			
			resultLap = getLapResult();
			currentLap++;
		}
		
		return resultLap;
	}
	
	/**
	 * Log message about a lap execution
	 * @param currentLap
	 */
	public abstract void logLap(int currentLap);
	
	/**
	 * Get result of a lap execution
	 * @return
	 */
	public abstract int getLapResult();
	
	/**
	 * Execute a lap of game mode run
	 * 
	 * A lap is launched by runMode()
	 * 
	 * @param currentLap
	 */
	public void runLap(int currentLap) {
		boolean isValidLap = false;
		while(!isValidLap) {
			try {
				askTest(currentLap);
				askResponse();
				isValidLap = true;
			} catch (InvalidGameLapItemException e) {
				displayMsg.errorLine(e.getMessage());
				logMsg.errorLine("essai " + (currentLap + 1) + " " + e.getMessage());
				executeActionsIfError();
			}
		}
	}
	
	/**
	 * Ask a test ie ask a proposition about combination value
	 * @param currentLap
	 */
	protected abstract void askTest(int currentLap);
	
	/**
	 * Ask a response ie ask a response about a proposition sets to guess combination value
	 * @param currentLap
	 */
	protected abstract void askResponse();
	
	/**
	 * Executes necessary process if a lap execution raised errors
	 */
	protected abstract void executeActionsIfError();
	
	/**
	 * Returns the value of the combination used in the game mode as a character string
	 * @return
	 */
	public String getModeCombinationValue() {
		return Formater.arrayToString(combination.getValue());
	}
	
}
