package com.rentalfilm.msacustomer.proxy;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rentalfilm.msacustomer.bean.UserBean;
import com.rentalfilm.msacustomer.payload.request.RegisterUserRequest;




@FeignClient(
		name = "edge-zuul",
		contextId = "userContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-user")
public interface UserProxy {
	
	@PostMapping("/user/create")
	public UserBean  createUser( 
			@RequestBody @Validated RegisterUserRequest registerUserRequest);
	
	@GetMapping("/msa-user/user/get-user-by-id/{userId}")
	public UserBean getUserById(@PathVariable("userId") String userId);
	
	@GetMapping("/msa-user/user/get-user-by-username/{username}")
	public UserBean getUserByUsername(@PathVariable("username") String username);
	
	@GetMapping("/msa-user/user/get-user-by-email/{email}")
	public UserBean getUserByEmail(@PathVariable("email") String email);

}
