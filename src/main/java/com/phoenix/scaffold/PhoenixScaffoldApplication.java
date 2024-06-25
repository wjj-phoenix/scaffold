package com.phoenix.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PhoenixScaffoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoenixScaffoldApplication.class, args);
    }

}
