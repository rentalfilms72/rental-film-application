package com.rentalfilm.msaclientui.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class PictureBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long pictureId;	

	private String fileName;
	
	private String fileType;
	
	private byte[] data;
	
	private Date lastUpdate;

	
	
	public PictureBean(String fileName, String fileType, byte[] data) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PictureBean other = (PictureBean) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		return true;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		return result;
	}
	
	
	
}