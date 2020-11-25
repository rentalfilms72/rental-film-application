package com.rentalfilm.msaauthority.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="AUTHORITY")
@Data @AllArgsConstructor @NoArgsConstructor
public class Authority {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AUTHORITY_ID")
	private Long authorityId;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Size(min=6, max=50)
	@Column(name="NAME", unique = true )
	private AuthorityEmunType authorityNameEnum;
	
}
