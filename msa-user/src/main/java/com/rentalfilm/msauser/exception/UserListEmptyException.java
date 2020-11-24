package com.rentalfilm.msauser.exception;

public class UserListEmptyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserListEmptyException(String message) {
        super(message);
    }

}
