package com.rentalfilm.msastaff.validator;


import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rentalfilm.msastaff.payload.request.RegisterStaffRequest;
import com.rentalfilm.msastaff.proxy.StoreProxy;
import com.rentalfilm.msastaff.proxy.UserProxy;

@Component
public class RegisterStaffValidator implements Validator {
	
	// common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private UserProxy userProxy;
    
    @Autowired
    private StoreProxy storeProxy;
    
    // The classes are supported by this validator.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == RegisterStaffRequest.class;
    }
	

	@Override
	public void validate(Object target, Errors errors) {

		RegisterStaffRequest registerStaffRequest = (RegisterStaffRequest) target;

		// Check the fields of RegisterCustomerRequest.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "Username is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "", "Confirm Password is required");
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "storeId", "", "Select one store. Store Id is required");

		if (errors.hasErrors()) {
			return;
		}

		
		if (!emailValidator.isValid(registerStaffRequest.getEmail())) {

			errors.rejectValue("email", "", "Email is not valid");
			return;
		}
//		UserBean userFound = new UserBean();
		boolean emailExit = false, usernameExist=false, storeExist=false;
		
		usernameExist = userProxy.usernameExist(registerStaffRequest.getUsername());
		if(usernameExist) {
			errors.rejectValue("username", "", "Username is not available");
			return;
		}
		
		emailExit = userProxy.emailExist(registerStaffRequest.getEmail());
		if(emailExit) {
			errors.rejectValue("email", "", "Email is not available");
			return;
		}
		
		registerStaffRequest.setStoreId(registerStaffRequest.getStoreId().trim());
		if(registerStaffRequest.getStoreId().equals(""))
			registerStaffRequest.setStoreId(null);
		
		if(registerStaffRequest.getStoreId() != null) {
			storeExist = storeProxy.storeExist(registerStaffRequest.getStoreId());
			if(!storeExist) {
				errors.rejectValue("storeId", "", "Store is not exist");
				return;
			}
			
		}
			
		if(registerStaffRequest.getPassword().length() < 6)
			 errors.rejectValue("password", "", "Invalid password, minimum length:6");
	
		if (!registerStaffRequest.getConfirmPassword().equals(registerStaffRequest.getPassword())) {
           errors.rejectValue("confirmPassword", "", "Password does not match the confirm password");
       }
		
	}

}
