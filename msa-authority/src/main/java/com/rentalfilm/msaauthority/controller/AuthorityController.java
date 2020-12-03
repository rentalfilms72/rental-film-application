package com.rentalfilm.msaauthority.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msaauthority.entity.Authority;
import com.rentalfilm.msaauthority.entity.AuthorityEmunType;
import com.rentalfilm.msaauthority.exception.AuthorityNotFoundException;
import com.rentalfilm.msaauthority.repository.AuthorityRepository;


@RestController
public class AuthorityController {
	
	@Autowired
	AuthorityRepository  authorityRepository;
	
	@GetMapping("/authority/public/auhority-name-exist")
	public boolean authorityNameExist( @PathVariable("name") String name){
		
		AuthorityEmunType AuthorityEmun = null;
		if(name != null) {
			if(name.equals(AuthorityEmunType.ROLE_ADMIN.getAuthorityName()))
				AuthorityEmun = AuthorityEmunType.ROLE_ADMIN;
			
			else if(name.equals(AuthorityEmunType.ROLE_STAFF.getAuthorityName()))
				AuthorityEmun = AuthorityEmunType.ROLE_STAFF;
			
			else if(name.equals(AuthorityEmunType.ROLE_CUSTOMER.getAuthorityName()))
				AuthorityEmun = AuthorityEmunType.ROLE_CUSTOMER;
		}
		
		Optional<Authority> authorityFound = authorityRepository.findByAuthorityNameEnum(AuthorityEmun);
		if(!authorityFound.isPresent())
			return false;
			
		return true;
	}
	
	@GetMapping("/authority/public/get-by-name")
	public ResponseEntity<Authority> getAuthorityByName( @PathVariable("name") String name){
		
		AuthorityEmunType AuthorityEmun = null;
		if(name != null) {
			if(name.equals(AuthorityEmunType.ROLE_ADMIN.getAuthorityName()))
				AuthorityEmun = AuthorityEmunType.ROLE_ADMIN;
			
			else if(name.equals(AuthorityEmunType.ROLE_STAFF.getAuthorityName()))
				AuthorityEmun = AuthorityEmunType.ROLE_STAFF;
			
			else if(name.equals(AuthorityEmunType.ROLE_CUSTOMER.getAuthorityName()))
				AuthorityEmun = AuthorityEmunType.ROLE_CUSTOMER;
		}
		
		Optional<Authority> authorityFound = authorityRepository.findByAuthorityNameEnum(AuthorityEmun);
		if(!authorityFound.isPresent()) 
			throw new AuthorityNotFoundException("Authority with name '" + name + "' do not exist.");
		
		
		return new ResponseEntity<Authority>(authorityFound.get(), HttpStatus.CREATED);
	}

}
