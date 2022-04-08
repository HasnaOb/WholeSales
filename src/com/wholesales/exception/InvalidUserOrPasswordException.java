package com.wholesales.exception;

public class InvalidUserOrPasswordException extends ServiceException {

	public InvalidUserOrPasswordException(String email) {
		super(email);
	}

}
