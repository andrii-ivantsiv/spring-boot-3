package com.andiv.example.springbootmicrometer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootMicrometerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMicrometerApplication.class, args);
    }

}
