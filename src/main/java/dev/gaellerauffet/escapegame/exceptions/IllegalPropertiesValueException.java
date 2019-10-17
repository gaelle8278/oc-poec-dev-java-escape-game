package dev.gaellerauffet.escapegame.exceptions;

/**
 * Exception used to throw problems in external properties/settings
 * 
 * @author gaelle
 *
 */
public class IllegalPropertiesValueException extends Exception {
	
	public IllegalPropertiesValueException(String message) {
		super(message);
	}

	public IllegalPropertiesValueException() {
		super();
	}

	public IllegalPropertiesValueException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalPropertiesValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalPropertiesValueException(Throwable cause) {
		super(cause);
	}

	

}
