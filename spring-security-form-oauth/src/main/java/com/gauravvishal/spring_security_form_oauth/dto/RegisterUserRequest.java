package com.gauravvishal.spring_security_form_oauth.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Long mobile;
    private String password;
}
