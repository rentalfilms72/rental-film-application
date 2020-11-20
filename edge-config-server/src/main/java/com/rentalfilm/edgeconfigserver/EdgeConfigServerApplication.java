package com.rentalfilm.edgeconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer // server config ( spring cloud)
@EnableDiscoveryClient // Eureka client
@SpringBootApplication
public class EdgeConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeConfigServerApplication.class, args);
	}

}
