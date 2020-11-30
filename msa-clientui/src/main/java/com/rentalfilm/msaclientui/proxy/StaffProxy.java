package com.rentalfilm.msaclientui.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
		name = "edge-zuul",
		contextId = "staffContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-staff")
public interface StaffProxy {

}
