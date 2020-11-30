package com.rentalfilm.msastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.rentalfilm.msastore")
public class MsaStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaStoreApplication.class, args);
	}

}
