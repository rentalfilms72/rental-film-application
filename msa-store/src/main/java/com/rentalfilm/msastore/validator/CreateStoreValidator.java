package com.rentalfilm.msastore.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rentalfilm.msastore.entity.Store;
import com.rentalfilm.msastore.payload.request.CreateStoreRequest;
import com.rentalfilm.msastore.repository.StoreRepository;


@Component
public class CreateStoreValidator implements Validator {
    
	
	@Autowired
	private StoreRepository storeRepository;
	
    // The classes are supported by this validator.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == CreateStoreRequest.class;
    }
	

	@Override
	public void validate(Object target, Errors errors) {

		CreateStoreRequest createStoreRequest = (CreateStoreRequest) target;

		// Check the fields of RegisterCustomerRequest.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "storeName", "", "Store name is required");


		if (errors.hasErrors()) {
			return;
		}
		
		Optional<Store> storeFound = storeRepository.findByStoreName(createStoreRequest.getStoreName());
		if(storeFound.isPresent()) {
			errors.rejectValue("storeName", "", "Store Name is not available");
			return;
		}
		
	}

}
