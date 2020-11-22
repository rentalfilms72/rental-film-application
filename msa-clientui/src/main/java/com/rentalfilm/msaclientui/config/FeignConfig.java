package com.rentalfilm.msaclientui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * 
 * @author Alberto
 *Configuration of Feign
 *Feign use the Bean to authenticate 
 */
@Configuration
public class FeignConfig {
	
	@Bean
	public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("user", "pwd");
	}

}
