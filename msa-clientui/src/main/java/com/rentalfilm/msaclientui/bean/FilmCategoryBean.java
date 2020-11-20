package com.rentalfilm.msaclientui.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.rentalfilm.msaclientui.bean.primarykey.FilmCategoryPK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class FilmCategoryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Embedded
	private FilmCategoryPK filmCategoryPK;

	private Date lastUpdate;
}