package com.rentalfilm.msauser.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
		name = "msa-zuul",
		contextId = "pwdresettokenContextId"
		//configuration = <classNane>ProxyConfig.class
		)
@RibbonClient(name = "msa-pwdresettoken")
public interface PwdResetTokenProxy {

}
