package com.rentalfilm.msauser.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class PwdResetTokenBean {

    private Long passwordResetTokenId;

    private String token;
    
    private Date expiryDate;
    
    

    private String userId;

    
//    // Set the expire date
//    public void setExpiryDate(int minutes){
//        Calendar now = Calendar.getInstance();
//        now.add(Calendar.MINUTE, minutes);
//        this.expiryDate = now.getTime();
//    }
//
//    public boolean isExpired() {
//        return new Date().after(this.expiryDate);
//    }
}
