package com.rentalfilm.msacustomer.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rentalfilm.msacustomer.payload.request.RegisterCustomerRequest;
import com.rentalfilm.msacustomer.proxy.UserProxy;

@Component
public class RegisterCustomerValidator implements Validator {
	
	// common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private UserProxy userProxy;
    
    // The classes are supported by this validator.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == RegisterCustomerRequest.class;
    }
	

	@Override
	public void validate(Object target, Errors errors) {

		RegisterCustomerRequest registerCustomerRequest = (RegisterCustomerRequest) target;

		// Check the fields of RegisterCustomerRequest.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Username is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "", "Confirm Password is required");


		if (errors.hasErrors()) {
			return;
		}

		
		if (!emailValidator.isValid(registerCustomerRequest.getEmail())) {

			errors.rejectValue("email", "", "Email is not valid");
			return;
		}
//		UserBean userFound = new UserBean();
		boolean emailExit = false, usernameExist=false;
		
		usernameExist = userProxy.usernameExist(registerCustomerRequest.getUsername());
		if(usernameExist) {
			errors.rejectValue("username", "", "Username is not available");
			return;
		}
		
		emailExit = userProxy.emailExist(registerCustomerRequest.getEmail());
		if(emailExit) {
			errors.rejectValue("email", "", "Email is not available");
			return;
		}
			
		if(registerCustomerRequest.getPassword().length() < 6)
			 errors.rejectValue("password", "", "Invalid password, minimum length:6");
	
		if (!registerCustomerRequest.getConfirmPassword().equals(registerCustomerRequest.getPassword())) {
           errors.rejectValue("confirmPassword", "", "Password does not match the confirm password");
       }
		
	}

}
