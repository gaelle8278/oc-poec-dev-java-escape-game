package dev.gaellerauffet.escapegame.exceptions.impl;

import dev.gaellerauffet.escapegame.exception.InvalidItemException;

/**
 * Exception used to throw problems in response to a combination test 
 * 
 * @author gaelle
 *
 */
public class InvalidResponseException extends InvalidItemException {

	public InvalidResponseException() {
		super();
	}

	public InvalidResponseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidResponseException(String message) {
		super(message);
	}

	public InvalidResponseException(Throwable cause) {
		super(cause);
	}

	

}
