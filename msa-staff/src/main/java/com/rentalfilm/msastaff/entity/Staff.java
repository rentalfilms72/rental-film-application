package com.rentalfilm.msastaff.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="STAFF")
@Data @AllArgsConstructor @NoArgsConstructor
public class Staff implements Serializable {
	// STAFFxxxx : where x represent digit and X represent Letter , 5 letters + 4 digits
	private static final long serialVersionUID = 1L;

	@Id
	@Size(min=9, max=10)
	@Column(name="STAFF_ID", columnDefinition="VARCHAR(10)")
	private String staffId;
	
	@NaturalId(mutable=true)
	@Email
	@NotBlank
	@Size(min=4, max=128)
    @Column(name = "EMAIL", nullable = false, columnDefinition="VARCHAR(128)")
    private String email;
	
	@NotBlank
	@Size(min=4, max=16)
    @Column(name = "USERNAME", length = 16, unique=true, nullable = false)
    private String username;
    
    @NotBlank
	@Size(min=6, max=128)
    @Column(name = "ENCRYPTED_PASSWORD", length = 128, nullable = false)
    private String password;
	
	@NotBlank
	@Size(min=1, max=45)
	@Column(name="FIRST_NAME", nullable=false, columnDefinition="VARCHAR(45)")
	private String firstName;

	@NotBlank
	@Size(min=1, max=45)
	@Column(name="LAST_NAME", nullable=false, columnDefinition="VARCHAR(45)")
	private String lastName;

	@Column(name="IS_ENABLED", columnDefinition="TINYINT(1)")
	private boolean enabled = false;
	
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
	

	//bi-directional many-to-one association to Store
	//@NotNull
	@Column(name="STORE_ID")
	private String storeId = null;
		
	//bi-directional many-to-one association to Picture
//	@Column(name = "PICTURE_ID")
//	private Long pictureId;
//
//	//bi-directional many-to-one association to Address
//	@NotNull
//	@Column(name="ADDRESS_ID")
//	private Long addressId;

}
