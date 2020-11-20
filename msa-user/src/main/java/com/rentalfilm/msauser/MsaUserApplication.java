package com.rentalfilm.msauser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.rentalfilm.msauser")
public class MsaUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaUserApplication.class, args);
	}

}
