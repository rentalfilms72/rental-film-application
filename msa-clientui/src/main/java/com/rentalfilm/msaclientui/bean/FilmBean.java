package com.rentalfilm.msaclientui.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


@Data @AllArgsConstructor @NoArgsConstructor
public class FilmBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String filmId;
	
	private String title;

	private String description;
	
	private Integer releaseYear;
	
	private Integer rentalDuration; // Value in day
	
	private Double rentalRate;
	
	private Integer length; // Value in minutes
	
	private Double replacementCost;
	
	private Date lastUpdate;
	
	
	
	private Set<Long> specialFeatures;
	
	private Long ratingId;// = new FilmRating(1L, FilmRatingEmunType.G, "General audiences – All ages admitted");
	
	private Long languageId;

	private Long originalLanguageId;

}