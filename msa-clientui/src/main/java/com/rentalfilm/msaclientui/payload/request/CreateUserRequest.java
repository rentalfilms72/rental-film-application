package com.rentalfilm.msaclientui.payload.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateUserRequest {
	
	@NotBlank
	@Size(min=7, max=10)
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
	

    @NotBlank
	private String authorityName;
    
	//bi-directional many-to-one association to Picture
	//private Long pictureId;
	


}
