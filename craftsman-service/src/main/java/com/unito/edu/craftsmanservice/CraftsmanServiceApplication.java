package com.unito.edu.craftsmanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CraftsmanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CraftsmanServiceApplication.class, args);
        System.out.println("Craftsman microservice is running ...");
    }

}
