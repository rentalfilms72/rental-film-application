package com.rentalfilm.edgezuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rentalfilm.edgezuul.proxy.ClientProxy;

@Controller
public class LoginZuulController {
	
	@Autowired
	private ClientProxy clientProxy;
	
	@GetMapping("/public/clientui/login")
	public void showLoginPage(Model model) {
		clientProxy.showLoginPage(model);
		//return "redirect:/private/customer/dashboard";
		//return "redirect:/http://localhost:4000/public/clientui/login";
	}
	

}
