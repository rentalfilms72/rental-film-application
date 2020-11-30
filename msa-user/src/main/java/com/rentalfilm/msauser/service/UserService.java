package com.rentalfilm.msauser.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	
	/**
	 * Validation of ID syntax
	 * 
	 * @param userId
	 * @param isCustomer
	 * @return A string that give a role name. ie: ADMIN, STAFF, CUSTOMER and UNKNOWN
	 */
	public String getAuthorityFromUserId(String userId) {
		// Pattern pattern = Pattern.compile( "^CUST\\d+CC( )$" );

		Pattern pattern = null;
		Matcher matcher = null;
		
		pattern = Pattern.compile( "^( )*ADMIN\\d+( )*$" );
		matcher = pattern.matcher(userId);
		if(matcher.find())
			return "ADMIN";
		
		pattern = Pattern.compile( "^( )*STAFF\\d+( )*$" );
		matcher = pattern.matcher(userId);
		if(matcher.find())
			return "STAFF";
		
		pattern = Pattern.compile( "^( )*CUST\\d+[A-Z]{2}( )*$" );
		matcher = pattern.matcher(userId);
		if(matcher.find())
			return "CUSTOMER";
		
		return "UNKNOWN";
	}

}
