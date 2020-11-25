package com.rentalfilm.msaclientui.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentalfilm.msaclientui.bean.UserBean;
import com.rentalfilm.msaclientui.payload.request.UserLoginRequest;
import com.rentalfilm.msaclientui.proxy.UserProxy;



@Controller
@RequestMapping("/clientui/login")
public class LoginClientController {

	@Autowired
	private UserProxy userProxy;

	/**
	 * Show the login page
	 * @param model
	 * @return
	 */
	@GetMapping
	public String showLogin(Model model) {
		return "login-page";
	}

	/**
	 * Execute the login process then redirect automatically the user
	 * to the dashboard page
	 * @param principal
	 * @param model
	 * @param userLoginRequest
	 */
	@PostMapping
	public void userAuthentication(
			Principal principal,
			Model model, 
			@ModelAttribute("userLoginRequest")  UserLoginRequest userLoginRequest) {

		List<String> roleNames = new ArrayList<>();

		UserBean userFound = userProxy.getUserByUsername(userLoginRequest.getUsername());
		
//		if(userFound.isPresent())
//			roleNames = userFound.get().getRoles()
//			.stream()
//			.map(r -> r.getRoleEnum().getRoleName())
//			.collect(Collectors.toList());
//
//		// Auto Login of UserNeed to give a paramater roleNames as a list of String
//		try {
//			SecurityUtil.logInUser(userFound.get(), roleNames);
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("errorMessageLoggin", "Error " + e.getMessage());
//		}
	}

}
