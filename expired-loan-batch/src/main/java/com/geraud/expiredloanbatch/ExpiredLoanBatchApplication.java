package com.geraud.expiredloanbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
public class ExpiredLoanBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpiredLoanBatchApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(){return new RestTemplate();}
}
