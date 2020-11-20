package com.rentalfilm.msaclientui.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
		name = "msa-zuul",
		contextId = "userContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-user")
public interface UserProxy {

}
