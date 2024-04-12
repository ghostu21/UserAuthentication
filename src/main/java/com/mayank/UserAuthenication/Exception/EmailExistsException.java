package com.mayank.UserAuthenication.Exception;

public class EmailExistsException extends RuntimeException {
	private static final long serialVersionUID = -9101782178854318980L;

	public EmailExistsException(String message) {
		super(message);
	}
}
