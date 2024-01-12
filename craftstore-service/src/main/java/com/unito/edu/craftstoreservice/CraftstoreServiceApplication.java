package com.unito.edu.craftstoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CraftstoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CraftstoreServiceApplication.class, args);
        System.out.println("Craftstore microservice is running ...");
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
