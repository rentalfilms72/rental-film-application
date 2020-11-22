package com.rentalfilm.msacustomer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This table content IDs User ( Staff:STAFFxxx, Customer:CUSTxxxCC )
 * The table  always have just one rows which content the last Id generated 
 * 
 * @author Albert Franck
 *
 */
@Entity
@Table(name="CUSTOMER_ID_TABLE")
@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerIdTable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Size(min=10, max=10)
	@Column(name = "CUSTOMER_ID", columnDefinition="VARCHAR(10)")
	private String customerId; // CUSxxxxxAA
}