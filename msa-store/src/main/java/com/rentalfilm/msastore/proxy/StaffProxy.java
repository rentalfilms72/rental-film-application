package com.rentalfilm.msastore.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.rentalfilm.msastore.bean.StaffBean;

@FeignClient(
		name = "edge-zuul",
		contextId = "staffContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-staff")
public interface StaffProxy {
	
	@GetMapping("/msa-staff/staff/public/staff-exist/{staffId}")
	public boolean staffExist(@PathVariable("staffId") String staffId);
	
	@PutMapping("/msa-staff/staff/public/update-storeId/{staffId}/{storeId}")
	public StaffBean  updateStoreIdOfStaff(
			@PathVariable(name = "staffId") String staffId,
			@PathVariable(name = "storeId") String storeId
			);

}
