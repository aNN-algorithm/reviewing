package com.example.reviewing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ReviewingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewingApplication.class, args);
	}

}
