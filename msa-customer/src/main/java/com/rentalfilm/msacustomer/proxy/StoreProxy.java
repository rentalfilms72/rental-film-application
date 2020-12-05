package com.rentalfilm.msacustomer.proxy;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(
		name = "edge-zuul",
		contextId = "storeContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-store")
public interface StoreProxy {
	
	@GetMapping("/msa-store/store/public/store-exist/{storeId}")
	public boolean storeExist(@PathVariable("storeId") String storeId);

}
