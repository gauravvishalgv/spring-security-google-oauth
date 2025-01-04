package com.gauravvishal.spring_security_form_oauth.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gauravvishal.spring_security_form_oauth.service.authentication.MyUserDetailsService;
import com.gauravvishal.spring_security_form_oauth.service.oauth2Login.IdentityAuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${frontend.homeUrl}")
    private String frontendHomeUrl;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private IdentityAuthenticationSuccessHandler identityAuthenticationSuccessHandler;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors ->cors.configurationSource(request -> {
                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of(frontendHomeUrl));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/", "/user/register", "perform-login").permitAll();
                    registry.anyRequest().authenticated();
                })
                .oauth2Login(oauth2->
                    oauth2
                        .successHandler(identityAuthenticationSuccessHandler)
                )
                .formLogin(formLogin -> formLogin
                    .loginProcessingUrl("/perform-login")
                    .defaultSuccessUrl(frontendHomeUrl, true)
                    .failureHandler((request, response, exception) -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"Invalid username or password\"}");
                    })
                )
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .permitAll()
                )
                .build();
            
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

}
