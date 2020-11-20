package com.rentalfilm.msaclientui.bean.primarykey;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class FilmFeaturesPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String filmId;
		
	private Long featuresId;
}