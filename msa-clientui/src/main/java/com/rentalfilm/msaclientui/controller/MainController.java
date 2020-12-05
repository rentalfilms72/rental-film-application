package com.rentalfilm.msaclientui.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msaclientui.bean.StoreBean;
import com.rentalfilm.msaclientui.proxy.StoreProxy;
import com.rentalfilm.msaclientui.url.Constant;

//@RestController
@Controller
public class MainController {
	
	
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
	
//	@Autowired
//	private StoreProxy storeProxy;
//	// JUST TO TEST
//	@GetMapping("/clientui/public/get-all-store")
//	public List<StoreBean> getStoreList(){
//		
//		List<StoreBean> storeList = storeProxy.getAllStore();		
//		return storeList;
//	}

}
