package dev.gaellerauffet.escapegame.exception;


/**
 * Exception used to throw problems in response to a combination test 
 * 
 * @author gaelle
 *
 */
public class InvalidGameLapItemException extends RuntimeException {

	public InvalidGameLapItemException() {
		super();
	}

	public InvalidGameLapItemException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidGameLapItemException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidGameLapItemException(String message) {
		super(message);
	}

	public InvalidGameLapItemException(Throwable cause) {
		super(cause);
	}

	

}
