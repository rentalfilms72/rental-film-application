package com.rentalfilm.msastore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msastore.entity.Store;
import com.rentalfilm.msastore.exception.StoreListEmptyException;
import com.rentalfilm.msastore.service.StoreService;
import com.rentalfilm.msastore.payload.request.CreateStoreRequest;
import com.rentalfilm.msastore.proxy.StaffProxy;
import com.rentalfilm.msastore.repository.StoreRepository;
import com.rentalfilm.msastore.validator.CreateStoreValidator;

@RestController
public class StoreController {

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private StaffProxy staffProxy;
	
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

	@PostMapping("/store/private/create")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Store>  createStore( 
			@RequestBody @Validated CreateStoreRequest createStoreRequest) { 
		
		// Control of data
		
		// Create new store		
		Store newStore = new Store();
		newStore.setStoreId(storeService.generateStringId());
		newStore.setStoreName(createStoreRequest.getStoreName().trim());
		newStore.setManagerStaffId(createStoreRequest.getManagerStaffId());
		
		newStore = storeRepository.save(newStore);
		
		// Change the Store of the new Staff manager
		staffProxy.updateStoreIdOfStaff(newStore.getManagerStaffId(), newStore.getStoreId());
		
		return new ResponseEntity<Store>(newStore, HttpStatus.OK);
	}
	
	@GetMapping("/store/public/get-all")
	public List<Store> getAllStore(){
		
		List<Store> storeList = storeRepository.findAll();
		if(storeList.isEmpty())
			throw new StoreListEmptyException("Store list empty.");
		
		return storeList;
	}
	
	@GetMapping("/store/public/check/get-all")
	public List<Store> getAllStoreCheck(){
		
		List<Store> storeList = storeRepository.findAll();
		
		return storeList;
	}
	
	@GetMapping("/store/public/store-exist/{storeId}")
	public boolean storeExist(@PathVariable("storeId") String storeId) {
		
		Optional<Store> storeFound = storeRepository.findById(storeId);
		if(!storeFound.isPresent()) 
			return false;
		
		return true;
	}

}
