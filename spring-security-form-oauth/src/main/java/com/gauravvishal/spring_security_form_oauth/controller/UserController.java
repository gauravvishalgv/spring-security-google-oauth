package com.gauravvishal.spring_security_form_oauth.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gauravvishal.spring_security_form_oauth.dto.RegisterUserRequest;
import com.gauravvishal.spring_security_form_oauth.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest){

        userService.registerUser(registerUserRequest);

        return "User registered successfully";
    }


    @GetMapping("/principal")
    public Principal user(Principal principal){
        return principal;
    }

}
