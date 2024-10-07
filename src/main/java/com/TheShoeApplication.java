package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TheShoeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheShoeApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> System.out.println("SpringBoot Status: UP");
    }
}
