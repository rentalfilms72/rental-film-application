package com.rentalfilm.msacustomer.payload.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterCustomerRequest {
	
	@Size(min=7, max=10)
	private String customerId;
	
	@Email
	@NotBlank
	@Size(min=4, max=128)
    private String email;
	
	@NotBlank
	@Size(min=4, max=16)
    private String username;
    
    @NotBlank
    @Size(min=6, max=128)
    private String password;
    
    @NotBlank
    @Size(min=6, max=128)
    private String confirmPassword;
	
	@NotBlank
	@Size(min=1, max=45)
	private String firstName;

	@NotBlank
	@Size(min=1, max=45)
	private String lastName;
	

	//bi-directional many-to-one association to Store
	@NotBlank
	private String storeId;
	
}
