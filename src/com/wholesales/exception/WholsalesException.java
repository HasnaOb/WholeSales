package com.wholesales.exception;

public class WholsalesException extends Exception{

	public WholsalesException() {
		super();
	}

	
	public WholsalesException(String message) {
		super(message);
	}
	
	 
	
	public WholsalesException(Throwable cause) {
		super(cause);
	}
	
	
	public WholsalesException(String message, Throwable cause) {
		super(message, cause);
	}
}
