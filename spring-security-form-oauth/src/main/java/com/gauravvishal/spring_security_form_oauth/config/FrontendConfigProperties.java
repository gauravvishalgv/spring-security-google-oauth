package com.gauravvishal.spring_security_form_oauth.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "frontend")
@Data
public class FrontendConfigProperties {
    private String homeUrl;
    private String loginUrl;
}


