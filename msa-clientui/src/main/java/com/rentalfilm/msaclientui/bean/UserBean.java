package com.rentalfilm.msaclientui.bean;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class UserBean {
  
    private String userId;
    
    private String username;
    
    private String encrytedPassword;
    
    private String email;
  
    private boolean enabled;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	
	
	private Long pictureId;

}
