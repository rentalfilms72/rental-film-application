package com.rentalfilm.msaclientui.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class AddressBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long addressId;
	
	private String address;

	private String postalCode;
	
	private String phone;

	private Date lastUpdate;

	
	private Long cityId;
}