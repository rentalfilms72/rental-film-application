package com.rentalfilm.msauser.payload.request;


import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateUserAuthorityRequest {
	
	@NotBlank
	private String userId;
	
	@NotBlank
	private String authorityName;

}
