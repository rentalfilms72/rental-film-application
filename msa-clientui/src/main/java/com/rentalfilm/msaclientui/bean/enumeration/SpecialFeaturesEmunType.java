package com.rentalfilm.msaclientui.bean.enumeration;

import lombok.Getter;

@Getter 
public enum SpecialFeaturesEmunType{
	
	TRAILERS("Trailers"),
	COMMENTARIES("Commentaries"),
	DELETED_SCENES("Deleted Scenes"),
	BEHIND_THE_SCENES("Behind the Scenes");
	
	    
    private String specialFeaturesName ;  
     
    SpecialFeaturesEmunType(String specialFeaturesName) {
    	this.specialFeaturesName = specialFeaturesName ;
    }

}
