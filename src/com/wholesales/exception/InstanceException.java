package com.wholesales.exception;

public class InstanceException extends ServiceException {
	 
		 
	public InstanceException() {
		super();
	}
	
	public InstanceException(String message) {
		this(message,null);		
	}
	

	public InstanceException(Throwable cause) {
		this(null,cause);		
	}
	
	public InstanceException(String message, Throwable cause) {
		super(message,cause);		
	}

	 
}

