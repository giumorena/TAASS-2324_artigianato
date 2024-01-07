package com.unito.edu.samplerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SamplerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamplerServiceApplication.class, args);
        System.out.println("Sampler microservice is running ...");
    }

}
