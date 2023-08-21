package com.example.be.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
@RequiredArgsConstructor

public class DemoController {
    @GetMapping("/test")
    public String getDemo(){
        return "DEMO";
    }
}
