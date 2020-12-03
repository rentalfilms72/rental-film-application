package com.rentalfilm.msaadmin.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {
	 
    /**
     * Encryte Password with BCryptPasswordEncoder
     * @param password
     * @return
     */
    public static String encrytePassword(String password) {
    	
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        return encoder.encode(password);
    }
 
}
