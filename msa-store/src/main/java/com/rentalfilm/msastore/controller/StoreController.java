package com.rentalfilm.msastore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msastore.entity.Store;
import com.rentalfilm.msastore.payload.request.CreateStoreRequest;
import com.rentalfilm.msastore.repository.StoreRepository;
import com.rentalfilm.msastore.validator.CreateStoreValidator;

@RestController
public class StoreController {

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private CreateStoreValidator createStoreValidator;

	// Set a form validator
	@InitBinder
	protected void createStoreInitBinder(WebDataBinder dataBinder) {
		// Form target
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == CreateStoreRequest.class) {
			dataBinder.setValidator(createStoreValidator);
		}	
	}

	@PostMapping("/store/create")
	public ResponseEntity<Store>  createStore( 
			@RequestBody @Validated CreateStoreRequest createStoreRequest) { 
		
		Store newStore = new Store();

		return new ResponseEntity<Store>(newStore, HttpStatus.OK);
	}

}
