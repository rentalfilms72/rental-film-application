package com.rentalfilm.msaauthority.entity;

import lombok.Getter;

@Getter 
public enum AuthorityEmunType{
	
	ROLE_ADMIN("ROLE_ADMIN"), // 1- ADMIN
	ROLE_STAFF("ROLE_STAFF"), // 2- WHO MANAGES CUSTOMER AND RENTAL OPERATIONS
	ROLE_CUSTOMER("ROLE_CUSTOMER"); // 3- READ ONLY
	
	    
    private String authorityName ;  
     
    AuthorityEmunType(String authorityName) {
    	this.authorityName = authorityName ;
    }

}
