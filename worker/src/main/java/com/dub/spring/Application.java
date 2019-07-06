package com.dub.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;

import com.dub.spring.minimumSpanningTree.Worker;

@SpringBootApplication
@EnableBinding(Source.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

	@Bean 
	public Worker worker() {
		return new Worker();
	}
	

}
