package ru.clevertec.userClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
@EnableTransactionManagement
@ComponentScan(basePackages = {"ru.clevertec.userClient","ru.clevertec.userClient.config"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}