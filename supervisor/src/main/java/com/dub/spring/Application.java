package com.dub.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dub.spring.controller.CountHolder;
import com.dub.spring.controller.DistMinHolder;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootApplication
public class Application {
	
	@Value("${worker-number}")
	int workerNumber;
		
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
 
    @Bean 
	public CountHolder countHolder() {
		return new CountHolder();
	}
	
	@Bean 
	public DistMinHolder distMinHolderHolder() {
		return new DistMinHolder(workerNumber);
	}
	
	@Bean ObjectMapper mapper() {
		return new ObjectMapper();
	}
	
}