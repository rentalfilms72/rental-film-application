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
	
	@GetMapping("/msa-customer/customer/get-by-id/{customerId}")
	public CustomerBean  getOneCustomer(@PathVariable(name = "customerId") String customerId);
	
	
	@GetMapping("/msa-customer/customer/get-all")
	public List<CustomerBean>  getAllCustomer();

}
