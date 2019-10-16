package dev.gaellerauffet.escapegame.exceptions;

public class InconsistencyException extends RuntimeException {

	public InconsistencyException(String message) {
		super(message);
	}

	public InconsistencyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InconsistencyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InconsistencyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InconsistencyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}


}
