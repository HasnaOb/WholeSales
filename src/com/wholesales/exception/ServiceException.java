package com.wholesales.exception;
 
	public class ServiceException extends WholsalesException {
	       
		public ServiceException() {
		}
		
		
		public ServiceException(String message) {
			super(message);
		}
		 
		
		
		public ServiceException(Throwable cause) {
			super(cause);
		}
		
		
		public ServiceException(String message, Throwable cause) {
			super(message, cause);
		}

	}
