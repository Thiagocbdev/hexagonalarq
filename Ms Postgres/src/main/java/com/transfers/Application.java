package com.transfers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		log.info("Starting application Postgres MS" );
		SpringApplication.run(Application.class, args);
	}

}
