package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MsInstructorsApplication {

	public static void main(String[] args) {
		log.info("Starting application MsInstructors");
		SpringApplication.run(MsInstructorsApplication.class, args);
	}

}
