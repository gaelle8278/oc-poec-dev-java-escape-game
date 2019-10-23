package dev.gaellerauffet.escapegame.exception;

/**
 * Exception used to throw problems in menu usage
 * @author gaelle
 *
 */
public class MenuOptionException extends Exception {

	public MenuOptionException() {
		super();
	}

	public MenuOptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MenuOptionException(String message, Throwable cause) {
		super(message, cause);
	}

	public MenuOptionException(String message) {
		super(message);
	}

	public MenuOptionException(Throwable cause) {
		super(cause);
	}

}
