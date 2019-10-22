package dev.gaellerauffet.escapegame.exception;

public abstract class InvalidItemException extends RuntimeException {

	public InvalidItemException() {
		super();
	}

	public InvalidItemException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidItemException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidItemException(String message) {
		super(message);
	}

	public InvalidItemException(Throwable cause) {
		super(cause);
	}
	

}
