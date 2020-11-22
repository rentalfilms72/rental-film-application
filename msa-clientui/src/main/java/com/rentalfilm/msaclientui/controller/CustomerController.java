package com.rentalfilm.msaclientui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msaclientui.bean.CustomerBean;
import com.rentalfilm.msaclientui.proxy.CustomerProxy;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerProxy customerProxy;
	
	
	@GetMapping("test-customer")
	public List<CustomerBean> testCustomer(){
		
		return customerProxy.getAllCustomer();
	}
	

}
