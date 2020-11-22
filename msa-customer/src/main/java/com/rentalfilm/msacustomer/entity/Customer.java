package com.rentalfilm.msacustomer.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="CUSTOMER")
@Data @AllArgsConstructor @NoArgsConstructor
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Size(min=10, max=10)
	@Column(name="CUSTOMER_ID", columnDefinition="VARCHAR(10)")
	private String customerId;
	
	@NaturalId(mutable=true)
	@Email
	@NotBlank
	@Size(min=4, max=128)
    @Column(name = "EMAIL", nullable = false, columnDefinition="VARCHAR(128)")
    private String email;
	
	@NotBlank
	@Size(min=1, max=45)
	@Column(name="FIRST_NAME", nullable=false, columnDefinition="VARCHAR(45)")
	private String firstName;

	@NotBlank
	@Size(min=1, max=45)
	@Column(name="LAST_NAME", nullable=false, columnDefinition="VARCHAR(45)")
	private String lastName;

	@Column(name="ACTIVE", columnDefinition="TINYINT(1)")
	private boolean active;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE", 
			updatable=false, insertable=false, nullable=false, 
			columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATE",
			updatable=true, insertable=false, 
			columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date lastUpdate;
	

	
	//bi-directional many-to-one association to Picture
	@Column(name = "PICTURE_ID")
	private Long pictureId;

	//bi-directional many-to-one association to Address
	@NotNull
	@Column(name="ADDRESS_ID")
	private Long addressId;

	//bi-directional many-to-one association to Store
	@NotNull
	@Column(name="STORE_ID")
	private String storeId;

}