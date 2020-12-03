package com.rentalfilm.msauserauthority.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msauserauthority.entiry.UserAuthority;
import com.rentalfilm.msauserauthority.entiry.UserAuthorityPK;
import com.rentalfilm.msauserauthority.payload.request.CreateUserAuthorityRequest;
import com.rentalfilm.msauserauthority.repository.UserAuthorityRepository;

@RestController
public class UserAuthorityController {
	
	@Autowired
	UserAuthorityRepository userAuthorityRepository;
	
	@Transactional
	@PostMapping("/userauthority/public/create")
	public ResponseEntity<UserAuthority> createUserAuthority(
			@RequestBody CreateUserAuthorityRequest createUserAuthorityRequest){
		
		//Control: verify if the UserAuthority already exist
		Optional<UserAuthority> userAuthorityFound = userAuthorityRepository.findById(new UserAuthorityPK(
				createUserAuthorityRequest.getUserId(),
				createUserAuthorityRequest.getAuthorityName()
				));
		
		// Create the new UserAuthority
		UserAuthority newUserAuthority = new UserAuthority();
		newUserAuthority.setUserAuthorityPK(
				new UserAuthorityPK(
						createUserAuthorityRequest.getUserId(),
						createUserAuthorityRequest.getAuthorityName()
						));
		
		if(!userAuthorityFound.isPresent())
			newUserAuthority = userAuthorityRepository.save(newUserAuthority);
		
		return new ResponseEntity<UserAuthority>(newUserAuthority, HttpStatus.OK);
	}

}
