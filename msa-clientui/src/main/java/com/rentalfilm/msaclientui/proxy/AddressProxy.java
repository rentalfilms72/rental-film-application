package com.rentalfilm.msaclientui.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
		name = "msa-zuul",
		contextId = "addressContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-address")
public interface AddressProxy {

}
