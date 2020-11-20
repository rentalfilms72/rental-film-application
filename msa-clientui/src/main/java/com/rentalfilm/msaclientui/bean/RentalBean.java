package com.rentalfilm.msaclientui.bean;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class RentalBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String rentalId;
	
	private Date rentalDate;
	
	private Date returnDate;
	
	private Date lastUpdate;
	
	
	
	private String inventoryId;
	
	private String customerId;
	
	private String staffId;
	
}
