package com.ali.nurse_at_home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan
public class NurseAtHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NurseAtHomeApplication.class, args);
    }

}
