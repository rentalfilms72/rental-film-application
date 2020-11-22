package com.rentalfilm.msaclientui.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rentalfilm.msaclientui.bean.CustomerBean;

@FeignClient(
		name = "msa-zuul",
		contextId = "customerContextId"
		//configuration = OrderProxyConfig.class
		)
@RibbonClient(name = "msa-customer")
public interface CustomerProxy {
	
	@GetMapping("/customer/get-by-id/{customerId}")
	public CustomerBean  getCustomer(@PathVariable(name = "customerId") String customerId);
	
	
	@GetMapping("/customer/get-all")
	public List<CustomerBean>  getAllCustomer();

}
