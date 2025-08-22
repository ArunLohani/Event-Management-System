package com.project.eventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EventmanagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(EventmanagementApplication.class, args);
	}

}
