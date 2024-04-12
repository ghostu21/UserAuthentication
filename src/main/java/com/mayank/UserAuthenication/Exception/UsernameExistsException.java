package com.mayank.UserAuthenication.Exception;

public class UsernameExistsException extends RuntimeException {
	private static final long serialVersionUID = -7849130152606400175L;

	public UsernameExistsException(String message) {
		super(message);
	}
}
