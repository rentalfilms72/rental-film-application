package com.rentalfilm.msaclientui.bean;

import java.io.Serializable;

import com.rentalfilm.msaclientui.bean.primarykey.FilmCategoryPK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Embedded;


@Data @AllArgsConstructor @NoArgsConstructor
public class FilmCategoryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Embedded
	private FilmCategoryPK filmCategoryPK;

	private Date lastUpdate;
}