package com.rentalfilm.edgezuul.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="JWT_TOKEN")
@Data @AllArgsConstructor @NoArgsConstructor
public class JwtToken implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	@Id
    private String token;
}
