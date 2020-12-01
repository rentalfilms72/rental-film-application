package com.rentalfilm.msastore.entity;

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
 * This table content IDs  ( Staff:STORExxx )
 * The table  always have just one rows which content the last Id generated 
 * 
 * @author Albert Franck
 *
 */
@Entity
@Table(name="STORE_ID_TABLE")
@Data @AllArgsConstructor @NoArgsConstructor
public class StoreIdTable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Size(min=8, max=10)
	@Column(name = "STORE_ID", columnDefinition="VARCHAR(10)")
	private String storeId; // STORExxx
}
