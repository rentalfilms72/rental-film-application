package com.rentalfilm.msaclientui.bean.primarykey;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class FilmActorPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String filmId;
	
	private String actorId;
}