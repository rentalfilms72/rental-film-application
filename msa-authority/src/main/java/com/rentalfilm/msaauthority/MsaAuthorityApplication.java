package com.rentalfilm.msaauthority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.rentalfilm.msaauthority")
public class MsaAuthorityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaAuthorityApplication.class, args);
	}

}
