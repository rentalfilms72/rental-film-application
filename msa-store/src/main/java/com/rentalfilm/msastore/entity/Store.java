package com.rentalfilm.msastore.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name="STORE")
@Data @AllArgsConstructor @NoArgsConstructor
public class Store implements Serializable {
	// STORExxx : where x represent digit and X represent Letter , 5 letters + 3 digits 
	private static final long serialVersionUID = 1L;
	
	@Id
	@Size(min=8, max=10)
	@Column(name="STORE_ID", columnDefinition="VARCHAR(10)")
	private String storeId;
	
	@Size(min=1, max=50)
	@Column(name="STORE_NAME", nullable=false, unique=true, columnDefinition="VARCHAR(50)")
	private String storeName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATE",
			updatable=true, insertable=false, nullable = false,
			columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date lastUpdate;


	//bi-directional many-to-one association to Staff
	//@NotNull
	@Column(name="MANAGER_STAFF_ID",  unique=true, nullable=true)
	private String managerStaffId;
}