package com.rentalfilm.msaclientui.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerBean{
	
	private String customerId;
	
    private String email;
	
	private String firstName;

	private String lastName;

	private boolean active;
	
	private Date createDate;
	
	private Date lastUpdate;
	
	
	
	private Long pictureId;

	private Long addressId;

	private String storeId;

}