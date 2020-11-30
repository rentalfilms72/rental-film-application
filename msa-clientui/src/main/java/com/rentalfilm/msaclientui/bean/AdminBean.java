package com.rentalfilm.msaclientui.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class AdminBean {
		
	private String adminId;
	
	private String email;
	
    private String username;
    
    private String password;
	
	private String firstName;

	private String lastName;

	private boolean enabled;
	
	private Date createDate;
	
	private Date lastUpdate;
	
	
	private String storeId;
	
}