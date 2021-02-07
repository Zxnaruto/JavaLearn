package com.zhaixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TimedTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimedTaskApplication.class, args);
    }

}
