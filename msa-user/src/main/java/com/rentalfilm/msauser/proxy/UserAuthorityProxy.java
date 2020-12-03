package com.rentalfilm.msauser.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rentalfilm.msauser.bean.UserAuthorityBean;
import com.rentalfilm.msauser.payload.request.CreateUserAuthorityRequest;

@FeignClient(
		name = "edge-zuul",
		contextId = "userauthorityContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-userauthority")
public interface UserAuthorityProxy {
	
	//@PostMapping("/userauthority/create")
	@PostMapping("/msa-userauthority/userauthority/public/create")
	public UserAuthorityBean createUserAuthority(
			@RequestBody CreateUserAuthorityRequest createUserAuthorityRequest);

}
