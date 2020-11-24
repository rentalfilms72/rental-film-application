package com.rentalfilm.msaclientui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msaclientui.bean.CustomerBean;
import com.rentalfilm.msaclientui.bean.UserBean;
import com.rentalfilm.msaclientui.proxy.CustomerProxy;
import com.rentalfilm.msaclientui.proxy.UserProxy;

@RestController
public class UserController {
	
	@Autowired
	UserProxy userProxy;
	
	
	@GetMapping("/test-user")
	public List<UserBean> testCustomer(){
		
		return userProxy.getAllUser();
	}
	

}
