package com.spring.boot.swagger.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class AppController {
    @GetMapping("/hello")
    public Map<String, String> getHello() {
        return Map.of("message", "Hello World");
    }
}
