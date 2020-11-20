package com.rentalfilm.msauser.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
		name = "msa-zuul",
		contextId = "customerContextId"
		//configuration = OrderProxyConfig.class
		)
@RibbonClient(name = "msa-customer")
public interface CustomerProxy {

}
