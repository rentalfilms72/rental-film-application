package com.rentalfilm.msaclientui.bean;


import com.rentalfilm.msaclientui.bean.enumeration.FilmRatingEmunType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class FilmRatingBean {
	
	private Long ratingId;
	
	private FilmRatingEmunType filmRatingEmun;
	
	private String filmRatingDescription;

}
