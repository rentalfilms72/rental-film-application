package com.rentalfilm.msacustomer.payload.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterUserRequest {
	
	@NotBlank
	@Size(min=10, max=10)
	private String userId;
	
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
	

	
	//bi-directional many-to-one association to Picture
	private Long pictureId;


}
