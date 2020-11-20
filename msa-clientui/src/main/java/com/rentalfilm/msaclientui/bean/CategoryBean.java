package com.rentalfilm.msaclientui.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class CategoryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long categoryId;
	
	private String categoryName;

	private Date lastUpdate;
}