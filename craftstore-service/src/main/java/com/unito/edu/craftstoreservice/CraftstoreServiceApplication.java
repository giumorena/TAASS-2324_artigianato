package com.unito.edu.craftstoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CraftstoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CraftstoreServiceApplication.class, args);
        System.out.println("Craftstore microservice is running ...");
    }

}
