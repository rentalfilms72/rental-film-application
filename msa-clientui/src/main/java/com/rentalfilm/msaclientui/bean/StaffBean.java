package com.rentalfilm.msaclientui.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class StaffBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String staffId;
	
    private String email;
	
	private String firstName;

	private String lastName;

	private boolean active;

	private Date lastUpdate;


	
	
	private Long pictureId;
	
	private Long addressId;

	private String storeId;
}