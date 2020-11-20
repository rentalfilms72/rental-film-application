package com.rentalfilm.msaclientui.bean.enumeration;

import lombok.Getter;

@Getter
public enum FilmRatingEmunType {
	
	G("G"),
	PG("PG"),
	PG_13("PG-13"),
	R("R"),
	NC_17("NC-17");
	
	    
    private String filmRatingName ;  
     
    FilmRatingEmunType(String filmRatingName) {
    	this.filmRatingName = filmRatingName ;
    }

}
