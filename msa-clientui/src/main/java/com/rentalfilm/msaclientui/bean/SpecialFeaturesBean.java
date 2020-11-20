package com.rentalfilm.msaclientui.bean;


import com.rentalfilm.msaclientui.bean.enumeration.SpecialFeaturesEmunType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class SpecialFeaturesBean {
	
	private Long specialFeaturesId;
	
	private SpecialFeaturesEmunType specialFeaturesEmun;
	
}
