package dev.gaellerauffet.escapegame.exceptions;

public class InvalidResponseException extends RuntimeException {

	public InvalidResponseException(String message) {
		super(message);
	}

	public InvalidResponseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidResponseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidResponseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidResponseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}


}
