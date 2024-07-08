package com.anertapi.springboot_rest_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    //HTTP GET Request
    //http://localhost:8080/hello-world   for return string
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hellow World";
    }
}
