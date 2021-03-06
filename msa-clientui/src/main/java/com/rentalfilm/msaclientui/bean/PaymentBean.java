package com.rentalfilm.msaclientui.bean;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class PaymentBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String paymentId;
	
	private Double amount;
	
	private Date paymentDate;
	
	private Date lastUpdate;
	
	
	
	
	private String customerId;
	
	private String staffId;
	
	private String rentalId;


}
