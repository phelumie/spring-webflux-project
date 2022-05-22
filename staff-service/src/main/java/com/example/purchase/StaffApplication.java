package com.example.purchase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.example.purchase"})
//@EnableR2dbcRepositories("com.example.purchase.repository.AuthRepository")
//@EnableJpaRepositories("com.example.purchase.repository.AuthRepository")
@EnableEurekaClient
public class StaffApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaffApplication.class, args);
	}

//	@Bean
//	public ServletWebServerFactory servletWebServerFactory(){
//		return new TomcatServletWebServerFactory();
//	}
}
