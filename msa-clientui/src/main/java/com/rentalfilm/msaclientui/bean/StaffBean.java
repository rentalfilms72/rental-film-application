package com.rentalfilm.msaclientui.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class StaffBean{
	
	private String staffId;
	
	private String email;
	
    private String username;
    
    private String password;
	
	private String firstName;

	private String lastName;

	private boolean enabled;
	
	private Date createDate;
	
	private Date lastUpdate;
	

	//bi-directional many-to-one association to Store
	private String storeId;
}