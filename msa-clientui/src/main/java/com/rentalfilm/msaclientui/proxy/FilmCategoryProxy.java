package com.rentalfilm.msaclientui.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
		name = "msa-zuul",
		contextId = "filmcategoryContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-filmcategory")
public interface FilmCategoryProxy {

}
