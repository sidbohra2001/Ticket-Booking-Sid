package com.booking.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TicketApplication {
	@Autowired
	private RestTemplateBuilder builder;

	@Bean @LoadBalanced
	RestTemplate restTemplate(){
		return builder.build();
	}
	public static void main(String[] args) {
		SpringApplication.run(TicketApplication.class, args);
	}

}
