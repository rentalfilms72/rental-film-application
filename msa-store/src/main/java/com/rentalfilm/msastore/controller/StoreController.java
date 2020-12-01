package com.rentalfilm.msastore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msastore.entity.Store;
import com.rentalfilm.msastore.exception.StoreListEmptyException;
import com.rentalfilm.msastore.service.StoreService;
import com.rentalfilm.msastore.payload.request.CreateStoreRequest;
import com.rentalfilm.msastore.repository.StoreRepository;
import com.rentalfilm.msastore.validator.CreateStoreValidator;

@RestController
public class StoreController {

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private StoreService storeService;
	
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
		
		// Control of data
		
		// Create new store		
		Store newStore = new Store();
		newStore.setStoreId(storeService.generateStringId());
		newStore.setStoreName(createStoreRequest.getStoreName());
		newStore.setManagerStaffId(createStoreRequest.getManagerStaffId());
		
		newStore = storeRepository.save(newStore);
		
		return new ResponseEntity<Store>(newStore, HttpStatus.OK);
	}
	
	@GetMapping("/store/get-all")
	public List<Store> getAllStore(){
		
		List<Store> storeList = storeRepository.findAll();
		if(storeList.isEmpty())
			throw new StoreListEmptyException("Store list empty.");
		
		return storeList;
	}

}
