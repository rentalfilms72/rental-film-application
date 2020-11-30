package com.rentalfilm.msaclientui.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
		name = "edge-zuul",
		contextId = "adminContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-admin")
public interface AdminProxy {

}
