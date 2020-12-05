package com.rentalfilm.msaclientui.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rentalfilm.msaclientui.bean.CustomerBean;
import com.rentalfilm.msaclientui.payload.request.RegisterCustomerRequest;


@FeignClient(
		name = "edge-zuul",
		contextId = "customerContextId"
		//configuration = OrderProxyConfig.class
		)
@RibbonClient(name = "msa-customer")
public interface CustomerProxy {
	
	@PostMapping("/msa-customer/customer/public/register")
	public CustomerBean  registerCustomer( 
			@RequestBody @Validated RegisterCustomerRequest registerCustomerRequest);
	
	@PutMapping("/msa-customer/customer/public/enable/{customerId}")
	public boolean enableCustomer(@PathVariable(name = "customerId") String customerId);
	
	@PutMapping("/msa-customer/customer/public/disable/{customerId}")
	public boolean disableCustomer(@PathVariable(name = "customerId") String customerId);
	
	@GetMapping("/msa-customer/customer/public/get-by-id/{customerId}")
	public CustomerBean  getOneCustomer(@PathVariable(name = "customerId") String customerId);
	
	@GetMapping("/msa-customer/customer/public/get-all")
	public List<CustomerBean>  getAllCustomer();

}
