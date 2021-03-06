package com.rentalfilm.msaclientui.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
		name = "edge-zuul",
		contextId = "authorityContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-authority")
public interface AuthorityProxy {

}
