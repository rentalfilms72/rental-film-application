package com.rentalfilm.msapwdresettoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.rentalfilm.msapwdresettoken")
public class MsaPwdresettokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaPwdresettokenApplication.class, args);
	}

}
