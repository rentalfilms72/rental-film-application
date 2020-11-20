package com.rentalfilm.msaclientui.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Embedded;

import com.rentalfilm.msaclientui.bean.primarykey.UserAuthorityPK;


@Data @AllArgsConstructor @NoArgsConstructor
public class UserAuthorityBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Embedded
	private UserAuthorityPK userAuthorityPK;

	private Date lastUpdate;
}