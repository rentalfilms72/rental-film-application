package com.rentalfilm.msacustomer.bean;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class StoreBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String storeId;
	
	private String storeName;

	private Date lastUpdate;


	private String managerStaffId;
}