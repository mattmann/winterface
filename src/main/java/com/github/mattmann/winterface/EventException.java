package com.github.mattmann.winterface;

public class EventException extends RuntimeException {

	private static final long serialVersionUID = 5417043768894955923L;

	public EventException() {}

	public EventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EventException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventException(String message) {
		super(message);
	}

	public EventException(Throwable cause) {
		super(cause);
	}
}