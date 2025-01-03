package com.gauravvishal.spring_security_form_oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gauravvishal.spring_security_form_oauth.dto.RegisterUserRequest;
import com.gauravvishal.spring_security_form_oauth.model.User;
import com.gauravvishal.spring_security_form_oauth.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    public void registerUser(RegisterUserRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        if (userRepo.existsByMobile(request.getMobile())) {
            throw new IllegalArgumentException("Mobile number is already in use.");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setAuthProvider("form");

        userRepo.save(user);
    }
}
