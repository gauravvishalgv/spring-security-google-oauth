package com.gauravvishal.spring_security_form_oauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gauravvishal.spring_security_form_oauth.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    
    boolean existsByEmail(String email);
    boolean existsByMobile(String mobile);
}
