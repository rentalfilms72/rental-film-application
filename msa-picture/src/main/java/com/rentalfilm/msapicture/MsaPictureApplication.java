package com.rentalfilm.msapicture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.rentalfilm.msapicture")
public class MsaPictureApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaPictureApplication.class, args);
	}

}
