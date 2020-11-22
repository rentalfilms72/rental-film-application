package com.rentalfilm.msaclientui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rentalfilm.msaclientui.exception.CustomErrorDecoder;


@Configuration
public class FeignExceptionConfig {
		
	@Bean
	public CustomErrorDecoder myCustomErrorDecoder() {
		return new CustomErrorDecoder();
	}

}
