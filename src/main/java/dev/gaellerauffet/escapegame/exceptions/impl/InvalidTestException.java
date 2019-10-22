package dev.gaellerauffet.escapegame.exceptions.impl;

import dev.gaellerauffet.escapegame.exception.InvalidItemException;

/**
 * Exception used to throw problems in combination test
 * 
 * @author gaelle
 *
 */
public class InvalidTestException  extends InvalidItemException {

	public InvalidTestException() {
		super();
	}

	public InvalidTestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidTestException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidTestException(String message) {
		super(message);
	}

	public InvalidTestException(Throwable cause) {
		super(cause);
	}

}
