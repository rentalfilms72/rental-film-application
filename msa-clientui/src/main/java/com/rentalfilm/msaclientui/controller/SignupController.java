package com.rentalfilm.msaclientui.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.rentalfilm.msaclientui.bean.StoreBean;
import com.rentalfilm.msaclientui.payload.request.RegisterCustomerRequest;
import com.rentalfilm.msaclientui.proxy.CustomerProxy;
import com.rentalfilm.msaclientui.service.ClientService;
import com.rentalfilm.msaclientui.validator.RegisterCustomerValidator;



@Controller
@Transactional
public class SignupController {

	@Autowired
	private CustomerProxy customerProxy;

	@Autowired
	private ClientService clientService;
	
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
	 * Show the sigm up page
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping("/clientui/customer/signup")
	public String registerCustomerPage(WebRequest request, Model model) {

		RegisterCustomerRequest registerCustomerRequest = new RegisterCustomerRequest();
		
		List<StoreBean> storeList = new ArrayList<>();
		
		model.addAttribute("registerCustomerRequest", registerCustomerRequest);
		model.addAttribute("storeList", storeList);

		return "customer-signup-page";
	}


/*
	@PostMapping
	public String signupSave(
			WebRequest request,
			Model model, 
			@ModelAttribute("userSignupRequest") @Validated UserSignupRequest userSignupRequest,
			BindingResult result) throws Exception {

		// Validation error.
		if (result.hasErrors()) {
			return "signup-page";
		}
		// 1:ROLE_ADMIN
		// 2:ROLE_STAFF
		// 3:ROLE_CUSTOMER
		int userRoleSignup = 3;

		String roleNameStr = null;
		if(userRoleSignup == 1)
			roleNameStr = RoleEmunType.ROLE_ADMIN.getRoleName();
		else if(userRoleSignup == 2)
			roleNameStr = RoleEmunType.ROLE_STAFF.getRoleName();
		else
			roleNameStr = RoleEmunType.ROLE_CUSTOMER.getRoleName();

		List<String> roleNames = new ArrayList<String>();
		roleNames.add(roleNameStr);

		User newUser = null;

		try {
			// If boolean is True means we create a Customer user 
			// If False means we create a Staff user
			newUser = userService.registerNewUserAccount(userSignupRequest, userRoleSignup);

			// Save on Data Base
			userRepository.save(newUser);

		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("errorMessage", "Error " + ex.getMessage());
			return "signup-page";
		}

//		if (userSignupRequest.getSignInProvider() != null) {
//			ProviderSignInUtils providerSignInUtils
//			= new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
//			providerSignInUtils.doPostSignUp(newUser.getUsername(), request);
//		}
		
//		if(userRoleSignup == 1 || userRoleSignup == 2)// ADMIN or // STAFF
//			return "user-successful-signup-page";
//		else{// CUSTOMER
//			// Auto loggin of the user
//			SecurityUtil.logInUser(newUser, roleNames);
//			return "redirect:/private/customer/dashboard";
//		}
		
		return "redirect:/private/customer/dashboard";

	}
*/

}
