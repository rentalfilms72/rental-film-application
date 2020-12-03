package com.rentalfilm.edgezuul.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
		name = "edge-zuul",
		contextId = "clientContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-clientui")
public interface ClientProxy {
	
	@GetMapping
	public String showLoginPage(Model model);

}
