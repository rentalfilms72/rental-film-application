package com.rentalfilm.msacustomer.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rentalfilm.msacustomer.bean.UserBean;
import com.rentalfilm.msacustomer.payload.request.CreateUserRequest;

@FeignClient(
		name = "edge-zuul",
		contextId = "userContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-user")
public interface UserProxy {
	
	@PostMapping("/msa-user/user/create")
	public UserBean  createUser( @RequestBody CreateUserRequest createUserRequest);
	
	@GetMapping("/msa-user/user/user-exist/{userId}")
	public boolean userExist(@PathVariable("userId") String userId);
	
	@GetMapping("/msa-user/user/username-exist/{username}")
	public boolean usernameExist(@PathVariable("username") String username);
	
	@GetMapping("/msa-user/user/email-exist/{email}")
	public boolean emailExist(@PathVariable("email") String email);
	
	@GetMapping("/msa-user/user/get-all")
	public List<UserBean> getAllUser();
	
	@GetMapping("/msa-user/user/get-user-by-id/{userId}")
	public UserBean getUserById(@PathVariable("userId") String userId);
	
	@GetMapping("/msa-user/user/get-user-by-username/{username}")
	public UserBean getUserByUsername(@PathVariable("username") String username);
	
	@GetMapping("/msa-user/user/get-user-by-email/{email}")
	public UserBean getUserByEmail(@PathVariable("email") String email);

}
