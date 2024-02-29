package ru.clevertec.userClient.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "news-service", url = "localhost:8080")
public interface NewsClient {
    @GetMapping("/check/{token}")
    void getToken(@PathVariable String token);

}

