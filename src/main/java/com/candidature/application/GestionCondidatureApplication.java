package com.candidature.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import com.candidature.application.GestionCondidatureApplication;
@SpringBootApplication

public class GestionCondidatureApplication {
	public static void main(String[] args) {
		SpringApplication.run(GestionCondidatureApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
		
	}
}
