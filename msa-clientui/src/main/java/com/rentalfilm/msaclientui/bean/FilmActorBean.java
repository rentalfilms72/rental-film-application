package com.rentalfilm.msaclientui.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Embedded;

import com.rentalfilm.msaclientui.bean.primarykey.FilmActorPK;


@Data @AllArgsConstructor @NoArgsConstructor
public class FilmActorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Embedded
	private FilmActorPK filmActorPK;

	private Date lastUpdate;
}