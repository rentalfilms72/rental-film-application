package com.rentalfilm.msaclientui.controller;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import com.rentalfilm.msaclientui.bean.StoreBean;
import com.rentalfilm.msaclientui.payload.request.RegisterCustomerRequest;
import com.rentalfilm.msaclientui.proxy.CustomerProxy;
import com.rentalfilm.msaclientui.proxy.StoreProxy;
import com.rentalfilm.msaclientui.validator.RegisterCustomerValidator;



@Controller
@Transactional
public class SignupController {

	@Autowired
	private CustomerProxy customerProxy;

	@Autowired
	private StoreProxy storeProxy;

	@Autowired
	private RegisterCustomerValidator registerCustomerValidator;


	// Set a form validator
	@InitBinder
	protected void registerCustomerInitBinder(WebDataBinder dataBinder) {
		// Form target
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == RegisterCustomerRequest.class) {
			dataBinder.setValidator(registerCustomerValidator);
		}	
	}

	/**
	 * Show the sign up customer page
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping("/clientui/public/customer/signup")
	public String registerCustomerPage(WebRequest request, Model model) {

		RegisterCustomerRequest registerCustomerRequest = new RegisterCustomerRequest();

		List<StoreBean> storeListFound = storeProxy.getAllStore();

		model.addAttribute("registerCustomerRequest", registerCustomerRequest);
		model.addAttribute("storeListFound", storeListFound);

		return "customer-signup-page";
	}


	@PostMapping("/clientui/public/customer/signup")
	public String saveCustomer(
			WebRequest request,
			Model model, 
			@ModelAttribute("registerCustomerRequest") @Validated RegisterCustomerRequest registerCustomerRequest,
			BindingResult result) throws Exception {

		// Validation error.
		if (result.hasErrors()) {
			List<StoreBean> storeListFound = storeProxy.getAllStore();
			model.addAttribute("storeListFound", storeListFound);
			return "customer-signup-page";
			// return "redirect:/clientui/customer/signup";
		}

		// Call the microservice Customer to register a new Customer
		customerProxy.registerCustomer(registerCustomerRequest);

		// Call the micro service Email to send and email to activate an account
		// TODO
		// ...
		return "customer-successfully-signup-page";

	}


}
