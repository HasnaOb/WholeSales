package com.wholesales.exception;

public class DataException extends ServiceException {

	public DataException() {
		super();
	}	
	
	public DataException(String message) {
		this(message,null);		
	}
	

	public DataException(Throwable cause) {
		this(null,cause);		
	}
	
	public DataException(String message, Throwable cause) {
		super(message,cause);		
	}
}
