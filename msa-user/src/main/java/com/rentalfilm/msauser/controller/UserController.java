package com.rentalfilm.msauser.controller;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msauser.entity.User;
import com.rentalfilm.msauser.exception.UserListEmptyException;
import com.rentalfilm.msauser.exception.UserNotFoundException;
import com.rentalfilm.msauser.payload.request.CreateUserAuthorityRequest;
import com.rentalfilm.msauser.payload.request.CreateUserRequest;
import com.rentalfilm.msauser.proxy.UserAuthorityProxy;
import com.rentalfilm.msauser.repository.UserRepository;


@RestController
public class UserController {
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAuthorityProxy userAuthorityProxy;
	
	@Transactional
	@PostMapping("/user/public/create")
	public ResponseEntity<User>  createUser( @RequestBody CreateUserRequest createUserRequest) {
		
		// verify if the user already exist
		Optional<User> userFound = userRepository.findById(createUserRequest.getUserId());
		
		User newUser = new User();
		newUser.setUserId(createUserRequest.getUserId());
		newUser.setUsername(createUserRequest.getUsername());
		newUser.setPassword(createUserRequest.getPassword());
		newUser.setEmail(createUserRequest.getEmail());
		
		//newUser.setPictureId(createUserRequest.getPictureId());
		//newUser.setAuthorities(createUserRequest.getAuthorities());	
		
		// Save the User on DATA BASE
		if(!userFound.isPresent())
			newUser = userRepository.save(newUser);
		
		// Call the msa-userauthority
		CreateUserAuthorityRequest  createUserAuthorityRequest = new CreateUserAuthorityRequest();
		createUserAuthorityRequest.setUserId(createUserRequest.getUserId());
		createUserAuthorityRequest.setAuthorityName(createUserRequest.getAuthorityName());
		userAuthorityProxy.createUserAuthority(createUserAuthorityRequest);
		
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}
	
	@PutMapping("/user/public/enable/{userId}")
	public boolean enableUser(@PathVariable(name = "userId") String userId){
		
		Optional<User> userFound = userRepository.findById(userId);
		if(userFound.isPresent()) {
			userFound.get().setEnabled(true);
			userRepository.save(userFound.get());
			return true;
		}
		return false;
	}
	
	@PutMapping("/user/public/disable/{userId}")
	public boolean disableUser(@PathVariable(name = "userId") String userId){
		
		Optional<User> userFound = userRepository.findById(userId);
		if(userFound.isPresent()) {
			userFound.get().setEnabled(false);
			userRepository.save(userFound.get());
			return true;
		}
		
		
		return false;
	}
	
	@GetMapping("/user/public/user-exist/{userId}")
	public boolean userExist(@PathVariable("userId") String userId) {
		
		Optional<User> userFound = userRepository.findById(userId);
		if(!userFound.isPresent()) 
			return false;
		
		return true;
	}
	
	@GetMapping("/user/public/username-exist/{username}")
	public boolean usernameExist(@PathVariable("username") String username) {
		
		Optional<User> userFound = userRepository.findByUsername(username);
		if(!userFound.isPresent()) 
			return false;
		
		return true;
	}
	
	@GetMapping("/user/public/email-exist/{email}")
	public boolean emailExist(@PathVariable("email") String email) {
		
		Optional<User> userFound = userRepository.findByEmail(email);
		if(!userFound.isPresent()) 
			return false;
		
		return true;
	}
	
	
	@GetMapping("/user/public/get-user-by-id/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
		
		Optional<User> userFound = userRepository.findById(userId);
		if(!userFound.isPresent()) 
			throw new UserNotFoundException("User with id '" + userId + "' do not exist.");
		
		return new ResponseEntity<User>(userFound.get(), HttpStatus.CREATED);
	}
	
	@GetMapping("/user/public/get-user-by-username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		
		Optional<User> userFound = userRepository.findByUsername(username);
		if(!userFound.isPresent())
			throw new UserNotFoundException("User with username '" + username + "' do not exist.");
		
		return new ResponseEntity<User>(userFound.get(), HttpStatus.CREATED);
	}
	
	@GetMapping("/user/public/get-user-by-email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
		
		Optional<User> userFound = userRepository.findByEmail(email);
		if(!userFound.isPresent()) 
			throw new UserNotFoundException("User with email '" + email + "' do not exist.");
		
		return new ResponseEntity<User>(userFound.get(), HttpStatus.CREATED);
	}
	
	@GetMapping("/user/public/get-all")
	public List<User> getAllUser() {
		
		List<User> userList = userRepository.findAll();
		if(userList.isEmpty())
			throw new UserListEmptyException("User list is empty.");
		
		return userList;
	}
	

}
