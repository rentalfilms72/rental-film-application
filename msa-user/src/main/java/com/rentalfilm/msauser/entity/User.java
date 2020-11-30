package com.rentalfilm.msauser.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER")
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
  
    @Id
    @Size(min=7, max=10)
    @Column(name = "USER_ID", length = 10, nullable = false)
    private String userId;
    
    @NaturalId(mutable=true)
	@Email
	@NotBlank
	@Size(min=4, max=128)
    @Column(name = "EMAIL", length = 128, nullable = false)
    private String email;
    
    @NotBlank
	@Size(min=4, max=16)
    @Column(name = "USERNAME", length = 16, unique=true, nullable = false)
    private String username;
    
    @NotBlank
	@Size(min=6, max=128)
    @Column(name = "PASSWORD", length = 128, nullable = false)
    private String password;
  
	@NotNull
    @Column(name = "IS_ENABLED", length = 1, nullable = false)
    private boolean enabled = false;
	
	@Column(name="CREATED_AT", 
			updatable=false, insertable=false, 
			columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
	
	@Column(name="UPDATED_AT",
			updatable=true, insertable=false, 
			columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date updatedAt;

	
//	@Column(name = "PICTURE_ID")
//	private Long pictureId;

}
