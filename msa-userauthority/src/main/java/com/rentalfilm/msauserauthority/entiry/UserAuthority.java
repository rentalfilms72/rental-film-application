package com.rentalfilm.msauserauthority.entiry;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "USER_AUTHORITY")
@Data @AllArgsConstructor @NoArgsConstructor
public class UserAuthority implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserAuthorityPK userAuthorityPK;

	
	@Column(name="LAST_UPDATE",
			updatable=true, insertable=false, nullable = false,
			columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date lastUpdate;
}