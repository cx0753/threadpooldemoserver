package com.example.threadpooldemoserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ThreadpooldemoserverApplication {

    private static Logger logger = LoggerFactory.getLogger(ThreadpooldemoserverApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ThreadpooldemoserverApplication.class, args);
        logger.info("-----My Spring Boot Application Started Success-----");
    }

}
