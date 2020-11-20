package com.rentalfilm.msaclientui.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
		name = "msa-zuul",
		contextId = "cityContextId"
		//configuration = .class
		)
@RibbonClient(name = "msa-city")
public interface CityProxy {

}
