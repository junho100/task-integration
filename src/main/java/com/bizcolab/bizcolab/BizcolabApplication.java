package com.bizcolab.bizcolab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BizcolabApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizcolabApplication.class, args);
    }
}
