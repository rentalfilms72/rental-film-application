package com.rentalfilm.msaclientui.payload.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserLoginRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min=4, max=40)
	private String username;
	
	@NotBlank
	@Size(min=6, max=128)
	private String password;

}
