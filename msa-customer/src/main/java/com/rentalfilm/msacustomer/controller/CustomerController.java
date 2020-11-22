package com.rentalfilm.msacustomer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msacustomer.entity.Customer;
import com.rentalfilm.msacustomer.exception.CustomerListEmptyException;
import com.rentalfilm.msacustomer.exception.CustomerNotFoundException;
import com.rentalfilm.msacustomer.repository.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/customer/get-by-id/{customerId}")
	public ResponseEntity<Customer>  getOneCustomer(@PathVariable(name = "customerId") String customerId) {
		
		Optional<Customer> customerFound = customerRepository.findById(customerId);
		if(!customerFound.isPresent())
			throw new CustomerNotFoundException("Customer with id " + customerId + " do not exist.");


		return new ResponseEntity<Customer>(customerFound.get(), HttpStatus.CREATED);
			
	}
	
	@GetMapping("/customer/get-all")
	public List<Customer>  getAllCustomer(){
		
		List<Customer> customerList = customerRepository.findAll();
		if(customerList.isEmpty())
			throw new CustomerListEmptyException("Customer list empty.");
		
		return customerList;
	}
	

}
