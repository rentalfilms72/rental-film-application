package com.rentalfilm.msastaff.exception;

public class StaffNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public StaffNotFoundException(String message) {
        super(message);
    }
	

}
