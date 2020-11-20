package com.rentalfilm.msaadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.rentalfilm.msaadmin")
public class MsaAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaAdminApplication.class, args);
	}

}
