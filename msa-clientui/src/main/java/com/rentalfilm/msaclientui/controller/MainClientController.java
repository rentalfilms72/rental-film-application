package com.rentalfilm.msaclientui.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rentalfilm.msaclientui.url.Constant;

@Controller
public class MainClientController {
	
	
	/**
	 * Show the Home page.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String homePage(Model model, Principal principal ) {
		
		model.addAttribute("urlLoginGatewayZuul", Constant.URL_LOGIN_EDGE_ZUUL);

//		List<String> userLoggedAuthorities = new ArrayList<>();
//		if(principal != null) {
//			UserDetails loginedUser = (UserDetails) ((Authentication) principal).getPrincipal();
//			userLoggedAuthorities = WebUtils.getUserAuthorities(loginedUser);
//			model.addAttribute("userLoggedAuthorities", userLoggedAuthorities);
//		}

		return "home-page";
	}
	/**
	 * Redirect to the home page
	 * @param model
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String redirecttohomePage(Model model, Principal principal ) {

		return "redirect:/";
	}

}
