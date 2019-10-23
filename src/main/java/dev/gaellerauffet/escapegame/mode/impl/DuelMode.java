package dev.gaellerauffet.escapegame.mode.impl;

import dev.gaellerauffet.escapegame.mode.Mode;
import dev.gaellerauffet.escapegame.util.Parameter;

public class DuelMode extends Mode {

	private ChallengerMode modeChallenger;
	private DefenderMode modeDefender;

	public DuelMode(ChallengerMode gameModeChallenger, DefenderMode gameModeDefender, int nbTests) {
		this.nbTests = nbTests;
		this.modeChallenger = gameModeChallenger;
		this.modeDefender = gameModeDefender;
	}

	@Override
	protected void displayStartMsg() {
		displayMsg.infoLine("Démarrage mode Duel");
		logMsg.infoLine("Mode de jeu : Duel");
	}

	@Override
	protected void displayEndMsg(int resultGame) {
		if (resultGame == Parameter.BOTH_ARE_WINNERS) {
			displayMsg.infoLine("Egalité ! Le joueur et l'IA ont deviné la combinaison. ");
			logMsg.infoLine("Fin de partie mode Duel : égalité.");
		} else if (resultGame == Parameter.WINNER_IS_HUMAN) {
			displayMsg.infoLine("Le joueur a gagné. Il a trouvé la combinaison définit par l'IA.");
			logMsg.infoLine(
					"Fin de partie mode Duel : le joueur a gagné, il a trouvé la combinaison définit par l'IA.");
		} else if (resultGame == Parameter.WINNER_IS_AI) {
			displayMsg.infoLine("L'IA a gagné. L'IA a trouvé la combinaison définit par le joueur. \n"
					+ "La combinaison définit par l'IA était : " +  modeChallenger.getModeCombinationValue());
			logMsg.infoLine("Fin de partie mode Duel : l'IA a gagné, elle a trouvé la combinaison définit par le joueur.");
		} else {
			displayMsg.infoLine(
					"Aucun gagnant. Personne n'a trouvé la cominaison secrète. La combinaison définit par l'IA était : "
							+ modeChallenger.getModeCombinationValue());
			logMsg.infoLine("Fin de partie mode Duel : personne n'a trouvé la combinaison.");
		}
		
	}

	@Override
	public void initMode() {
		// humanPlayer and aiPlayer set the value to find for its own combination
		modeChallenger.initMode();
		modeDefender.initMode();
	}

	@Override
	public void displayMsgBeforeRun() {
		modeChallenger.displayMsgBeforeRun();
		modeDefender.displayMsgBeforeRun();
	}


	@Override
	public void logLap(int currentLap) {
		modeChallenger.logLap(currentLap);
		modeDefender.logLap(currentLap);
		
	}

	@Override
	public int getLapResult() {
		int result = Parameter.NO_WINNER;
		
		int resultDefenderMode = modeDefender.getLapResult();
		int resultChallengerMode = modeChallenger.getLapResult();
		
		if(resultDefenderMode == Parameter.WINNER_IS_AI && resultChallengerMode == Parameter.WINNER_IS_HUMAN) {
			result = Parameter.BOTH_ARE_WINNERS;
		} else if (resultDefenderMode == Parameter.WINNER_IS_AI) {
			result = Parameter.WINNER_IS_AI;
		} else if (resultChallengerMode == Parameter.WINNER_IS_HUMAN) {
			result = Parameter.WINNER_IS_HUMAN;
		}
		
		return result;
	}

	@Override
	protected void askTest(int currentLap) {
		//not ask test directly uses challenger and defender mode instead
	}

	@Override
	protected void askResponse() {
		//not ask test directly uses challenger and defender mode instead
	}

	@Override
	public void runLap(int currentLap) {
		//run a lap of challenger mode
		modeChallenger.runLap(currentLap);
		//then run a lap of defender mode
		modeDefender.runLap(currentLap);
	}

	@Override
	protected void executeActionsIfError() {
		//not do this directly delegates to challenger and defender mode instead
	}
	
	@Override
	public String getModeCombinationValue() {
		//not do this directly uses method in challenger and defender mode if necessary
		return "";
	}
	
}
