package com.rentalfilm.msaclientui.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class AdminBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long addressId;
	
	private String address;

	private String address2 = null;

	private String district;

	private String postalCode = null;

	private Date lastUpdate;

	private String phone;
	
	

	private Long cityId;
}