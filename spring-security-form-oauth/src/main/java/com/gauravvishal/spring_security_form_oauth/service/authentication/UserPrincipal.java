package com.gauravvishal.spring_security_form_oauth.service.authentication;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.gauravvishal.spring_security_form_oauth.model.User;

public class UserPrincipal implements UserDetails{

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        
        return !user.isDeleted();
    }

    @Override
    public boolean isAccountNonLocked() {
        
        return !user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        
        return true;
    }

    @Override
    public boolean isEnabled() {
        
        return !user.isDisabled();
    
    }
    
}
