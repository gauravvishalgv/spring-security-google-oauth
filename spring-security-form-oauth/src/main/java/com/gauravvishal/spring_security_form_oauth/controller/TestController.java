package com.gauravvishal.spring_security_form_oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @GetMapping()
    public String test() {
        return "Tested";
    }
    
}
