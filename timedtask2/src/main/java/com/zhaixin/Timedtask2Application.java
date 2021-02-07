package com.zhaixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Timedtask2Application {

	public static void main(String[] args) {
		SpringApplication.run(Timedtask2Application.class, args);
	}

}
