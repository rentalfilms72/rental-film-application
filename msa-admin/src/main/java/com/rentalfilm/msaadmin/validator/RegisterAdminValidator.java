package com.rentalfilm.msaadmin.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rentalfilm.msaadmin.payload.request.RegisterAdminRequest;
import com.rentalfilm.msaadmin.proxy.UserProxy;

@Component
public class RegisterAdminValidator implements Validator {
	
	// common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private UserProxy userProxy;
    
    // The classes are supported by this validator.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == RegisterAdminRequest.class;
    }
	

	@Override
	public void validate(Object target, Errors errors) {

		RegisterAdminRequest registerAdminRequest = (RegisterAdminRequest) target;

		// Check the fields of RegisterCustomerRequest.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Username is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "", "Confirm Password is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "storeId", "", "Select one store. Store Id is required");

		if (errors.hasErrors()) {
			return;
		}

		
		if (!emailValidator.isValid(registerAdminRequest.getEmail())) {

			errors.rejectValue("email", "", "Email is not valid");
			return;
		}
//		UserBean userFound = new UserBean();
		boolean emailExit = false, usernameExist=false;
		
		usernameExist = userProxy.usernameExist(registerAdminRequest.getUsername());
		if(usernameExist) {
			errors.rejectValue("username", "", "Username is not available");
			return;
		}
		
		emailExit = userProxy.emailExist(registerAdminRequest.getEmail());
		if(emailExit) {
			errors.rejectValue("email", "", "Email is not available");
			return;
		}
			
		if(registerAdminRequest.getPassword().length() < 6)
			 errors.rejectValue("password", "", "Invalid password, minimum length:6");
	
		if (!registerAdminRequest.getConfirmPassword().equals(registerAdminRequest.getPassword())) {
           errors.rejectValue("confirmPassword", "", "Password does not match the confirm password");
       }
		
	}

}
