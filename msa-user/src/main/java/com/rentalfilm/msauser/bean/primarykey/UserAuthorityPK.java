package com.rentalfilm.msauser.bean.primarykey;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class UserAuthorityPK {
	
	private String userId;
	
	private String authorityName;

}
