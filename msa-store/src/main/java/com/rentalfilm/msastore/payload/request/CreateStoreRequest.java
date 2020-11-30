package com.rentalfilm.msastore.payload.request;

import java.util.Date;

import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateStoreRequest {
	
	@Size(min=8, max=10)
	private String storeId;
	
	@Size(min=1, max=50)
	private String storeName;

	private Date lastUpdate;


	//bi-directional many-to-one association to Staff
	//@NotBlank
	private String managerStaffId;

}
