package com.rentalfilm.msacustomer.exception;

public class CustomerListEmptyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CustomerListEmptyException(String message) {
        super(message);
    }
	

}
