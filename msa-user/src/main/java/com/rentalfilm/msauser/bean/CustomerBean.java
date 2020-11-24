package com.rentalfilm.msauser.bean;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String customerId;
	
    private String email;
    
    private String username;
    
    private String password;
	
	private String firstName;

	private String lastName;

	private boolean enabed;
	
	private Date createDate;
	
	private Date lastUpdate;
	
	
	
	private Long pictureId;

	private Long addressId;

	private String storeId;

}