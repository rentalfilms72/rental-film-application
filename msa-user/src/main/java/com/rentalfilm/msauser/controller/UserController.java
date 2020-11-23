package com.rentalfilm.msauser.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msauser.entity.User;
import com.rentalfilm.msauser.repository.UserRepository;


@RestController
public class UserController {
	
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/user/get-user-by-id/{userId}")
	public User getUserById(@PathVariable("userId") String userId) {
		
		Optional<User> userFound = userRepository.findById(userId);
		
		return userFound.get();
	}
	
	@GetMapping("/user/get-user-by-username/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		
		Optional<User> userFound = userRepository.findByUsername(username);
		
		return userFound.get();
	}
	
	@GetMapping("/user/get-user-by-email/{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		
		Optional<User> userFound = userRepository.findByEmail(email);
		
		return userFound.get();
	}
	

}
