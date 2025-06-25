package com.npc.laboratorio04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@ConfigurationPropertiesScan
public class Laboratorio04Application {

    public static void main(String[] args) {
        SpringApplication.run(Laboratorio04Application.class, args);
    }

}
