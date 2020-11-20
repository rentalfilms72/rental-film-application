package com.rentalfilm.msauser.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
		name = "msa-zuul",
		contextId = "adminContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-admin")
public interface AdminProxy {

}
