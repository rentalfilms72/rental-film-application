package com.rentalfilm.msaclientui.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rentalfilm.msaclientui.bean.StoreBean;
import com.rentalfilm.msaclientui.payload.request.CreateStoreRequest;

@FeignClient(
		name = "edge-zuul",
		contextId = "storeContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-store")
public interface StoreProxy {
	
	@PostMapping("/msa-store/store/create")
	public StoreBean  createStore( 
			@RequestBody @Validated CreateStoreRequest createStoreRequest);
	
	@GetMapping("/msa-store/store/get-all")
	public List<StoreBean> getAllStore();

}
