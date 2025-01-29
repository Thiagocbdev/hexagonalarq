package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class MsTeachersManagementApplication {

	public static void main(String[] args) {
		log.info("Starting application MsTeachersManagement" );
		SpringApplication.run(MsTeachersManagementApplication.class, args);
	}

}
