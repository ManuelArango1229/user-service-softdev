package com.devsoft.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        System.out.println("IP desde env: " + System.getenv("POD_IP"));
        SpringApplication.run(DemoApplication.class, args);
    }

}
