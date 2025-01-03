package com.gauravvishal.spring_security_form_oauth.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "auth_provider")
    private String authProvider;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobile;

    private String password;

    private String role;
    
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "mobile_verified")
    private boolean mobileVerified;

    @Column(name = "email_verified")
    private boolean emailVerified;

    @Column(name = "is_online")
    private boolean isOnline;

    @Column(name = "is_disabled")
    private boolean isDisabled;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_locked")
    private boolean isLocked;
    
    public User(){
        this.createdOn = new Date(System.currentTimeMillis());
        this.updatedOn = new Date(System.currentTimeMillis());
        this.isOnline = false;
        this.isDisabled = false;
        this.isDeleted = false;
        this.isLocked = false;
        this.mobileVerified = false;
        this.emailVerified = false;
        this.role = "ROLE_USER";
    }
}
