package com.rentalfilm.msacustomer.controller;

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

import com.rentalfilm.msacustomer.entity.Customer;
import com.rentalfilm.msacustomer.exception.CustomerListEmptyException;
import com.rentalfilm.msacustomer.exception.CustomerNotFoundException;
import com.rentalfilm.msacustomer.payload.request.RegisterCustomerRequest;
import com.rentalfilm.msacustomer.payload.request.RegisterUserRequest;
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

	@PostMapping("/customer/register")
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
		
		
		newCustomer.setPictureId(registerCustomerRequest.getPictureId());
		newCustomer.setAddressId(registerCustomerRequest.getAddressId());
		newCustomer.setStoreId(registerCustomerRequest.getStoreId());
		
		
		
		// Call the User microservice to create also a User
		RegisterUserRequest registerUserRequest = new RegisterUserRequest();
		registerUserRequest.setUserId(encryptedPassword);
		registerUserRequest.setEmail(registerCustomerRequest.getEmail().trim());
		registerUserRequest.setUsername(registerCustomerRequest.getUsername().trim());
		registerUserRequest.setPassword(encryptedPassword);
		registerUserRequest.setPictureId(registerCustomerRequest.getPictureId());
		
		userProxy.createUser(registerUserRequest);
		// ....

		return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
	}


	// Proxy method
	@GetMapping("/customer/get-by-id/{customerId}")
	public ResponseEntity<Customer>  getOneCustomer(@PathVariable(name = "customerId") String customerId) {

		Optional<Customer> customerFound = customerRepository.findById(customerId);
		if(!customerFound.isPresent())
			throw new CustomerNotFoundException("Customer with id '" + customerId + "' do not exist.");


		return new ResponseEntity<Customer>(customerFound.get(), HttpStatus.CREATED);

	}
	// Proxy method
	@GetMapping("/customer/get-all")
	public List<Customer>  getAllCustomer(){

		List<Customer> customerList = customerRepository.findAll();
		if(customerList.isEmpty())
			throw new CustomerListEmptyException("Customer list empty.");

		return customerList;
	}


}
