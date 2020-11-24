package com.rentalfilm.msauser.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msauser.entity.User;
import com.rentalfilm.msauser.payload.request.RegisterUserRequest;
import com.rentalfilm.msauser.repository.UserRepository;


@RestController
public class UserController {
	
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/user/create")
	public ResponseEntity<User>  createUser( 
			@RequestBody @Validated RegisterUserRequest registerUserRequest) {
		
		
		User newUser = new User();
		newUser.setUserId(registerUserRequest.getUserId());
		newUser.setUsername(registerUserRequest.getUsername());
		newUser.setPassword(registerUserRequest.getPassword());
		newUser.setEmail(registerUserRequest.getEmail());
		
		newUser.setPictureId(registerUserRequest.getPictureId());
				
		
		
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/user/get-user-by-id/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
		
		Optional<User> userFound = userRepository.findById(userId);
		if(!userFound.isPresent()) return null;
		//throw new UserNotFoundException("User with id '" + userId + "' do not exist.");
		
		return new ResponseEntity<User>(userFound.get(), HttpStatus.CREATED);
	}
	
	@GetMapping("/user/get-user-by-username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		
		Optional<User> userFound = userRepository.findByUsername(username);
		if(!userFound.isPresent()) return null;
			//throw new UserNotFoundException("User with username '" + username + "' do not exist.");
		
		return new ResponseEntity<User>(userFound.get(), HttpStatus.CREATED);
	}
	
	@GetMapping("/user/get-user-by-email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
		
		Optional<User> userFound = userRepository.findByEmail(email);
		if(!userFound.isPresent())return null;
		//throw new UserNotFoundException("User with email '" + email + "' do not exist.");
		
		return new ResponseEntity<User>(userFound.get(), HttpStatus.CREATED);
	}
	
	@GetMapping("/user/get-all")
	public List<User> getAllUser() {
		
		List<User> userList = userRepository.findAll();
		if(userList.isEmpty()) return null;
		//throw new UserListEmptyException("User list is empty.");
		
		return userList;
	}
	

}
