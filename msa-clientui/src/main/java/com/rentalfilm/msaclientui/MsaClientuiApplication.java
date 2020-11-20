package com.rentalfilm.msaclientui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {
		    DataSourceAutoConfiguration.class, 
		    DataSourceTransactionManagerAutoConfiguration.class, 
		    HibernateJpaAutoConfiguration.class
})
@EnableFeignClients("com.rentalfilm.msaclientui")
public class MsaClientuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaClientuiApplication.class, args);
	}

}
