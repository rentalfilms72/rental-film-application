package com.rentalfilm.msauserauthority.entiry;


import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class UserAuthorityPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min=6, max=10)
	private String userId;
	
	@NotBlank
	@Size(min=6, max=50)
	private String authorityName;

}
