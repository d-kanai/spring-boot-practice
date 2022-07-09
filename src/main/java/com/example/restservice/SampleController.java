package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {


    @GetMapping("/hello")
    public String hello() {
        System.out.println("/hello: 200");
        return "hello";
    }


}
