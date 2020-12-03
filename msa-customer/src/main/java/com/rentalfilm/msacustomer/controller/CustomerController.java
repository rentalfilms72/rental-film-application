package com.rentalfilm.msacustomer.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msacustomer.entity.Customer;
import com.rentalfilm.msacustomer.exception.CustomerListEmptyException;
import com.rentalfilm.msacustomer.exception.CustomerNotFoundException;
import com.rentalfilm.msacustomer.payload.request.RegisterCustomerRequest;
import com.rentalfilm.msacustomer.payload.request.CreateUserRequest;
import com.rentalfilm.msacustomer.proxy.UserProxy;
import com.rentalfilm.msacustomer.repository.CustomerRepository;
import com.rentalfilm.msacustomer.service.CustomerService;
import com.rentalfilm.msacustomer.util.EncrytedPasswordUtils;
import com.rentalfilm.msacustomer.validator.RegisterCustomerValidator;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	UserProxy userProxy;
	
	@Autowired
	RegisterCustomerValidator registerCustomerValidator;

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

	@Transactional
	@PostMapping("/customer/public/register")
	public ResponseEntity<Customer>  registerCustomer( 
			@RequestBody @Validated RegisterCustomerRequest registerCustomerRequest) {
		
		// VALIDATION OF DATA
		
		
		// Create the new Customer
		Customer newCustomer = new Customer();
		String idGenerated = customerService.generateStringId();
		newCustomer.setCustomerId(idGenerated);
		newCustomer.setEmail(registerCustomerRequest.getEmail().trim());
		newCustomer.setUsername(registerCustomerRequest.getUsername().trim());
		
		String encryptedPassword = "{bcrypt}" + EncrytedPasswordUtils.encrytePassword(registerCustomerRequest.getPassword());
		newCustomer.setPassword(encryptedPassword);
		
		newCustomer.setFirstName(registerCustomerRequest.getFirstName().trim());
		newCustomer.setLastName(registerCustomerRequest.getLastName().trim());
		newCustomer.setEnabled(false);
		
		
		//newCustomer.setPictureId(registerCustomerRequest.getPictureId());
		//newCustomer.setAddressId(registerCustomerRequest.getAddressId());
		newCustomer.setStoreId(registerCustomerRequest.getStoreId());
		
		// Save the customer on DATA BASE
		newCustomer = customerRepository.save(newCustomer);
		
		
		// Call the User microservice to create also a User
		CreateUserRequest createUserRequest = new CreateUserRequest();
		createUserRequest.setUserId(idGenerated);
		createUserRequest.setEmail(registerCustomerRequest.getEmail().trim());
		createUserRequest.setUsername(registerCustomerRequest.getUsername().trim());
		createUserRequest.setPassword(encryptedPassword);
		
		//createUserRequest.setPictureId(registerCustomerRequest.getPictureId());
		createUserRequest.setAuthorityName("ROLE_CUSTOMER");
		userProxy.createUser(createUserRequest);
		

		return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
	}
	
	@PutMapping("/customer/public/enable/{customerId}")
	public boolean enableCustomer(@PathVariable(name = "customerId") String customerId){
		
		Optional<Customer> customerFound = customerRepository.findById(customerId);
		if(customerFound.isPresent()) {
			// TODO
			//enable the customer after a confermation mail
			//...
			customerFound.get().setEnabled(true);
			customerRepository.save(customerFound.get());
			userProxy.enableUser(customerId);
			return true;
		}
		
		
		return false;
	}
	
	
	@PutMapping("/customer/public/disable/{customerId}")
	public boolean disableCustomer(@PathVariable(name = "customerId") String customerId){
		
		Optional<Customer> customerFound = customerRepository.findById(customerId);
		if(customerFound.isPresent()) {
			customerFound.get().setEnabled(false);
			customerRepository.save(customerFound.get());
			userProxy.disableUser(customerId);
			return true;
		}
		
		
		return false;
	}

	
	// Proxy method
	@GetMapping("/customer/public/get-by-id/{customerId}")
	public ResponseEntity<Customer>  getOneCustomer(@PathVariable(name = "customerId") String customerId) {

		Optional<Customer> customerFound = customerRepository.findById(customerId);
		if(!customerFound.isPresent())
			throw new CustomerNotFoundException("Customer with id '" + customerId + "' do not exist.");


		return new ResponseEntity<Customer>(customerFound.get(), HttpStatus.OK);
	}
	
	// Proxy method
	@GetMapping("/customer/public/get-all")
	public ResponseEntity<List<Customer>>  getAllCustomer(){

		List<Customer> customerList = customerRepository.findAll();
		if(customerList.isEmpty())
			throw new CustomerListEmptyException("Customer list empty.");

		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}


}
