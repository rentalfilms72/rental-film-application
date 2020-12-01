package com.rentalfilm.msastore.exception;

public class StoreListEmptyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public StoreListEmptyException(String message) {
        super(message);
    }

}
