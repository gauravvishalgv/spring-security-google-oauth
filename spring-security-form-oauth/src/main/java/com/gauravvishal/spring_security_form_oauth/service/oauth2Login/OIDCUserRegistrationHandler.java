package com.gauravvishal.spring_security_form_oauth.service.oauth2Login;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.gauravvishal.spring_security_form_oauth.model.User;
import com.gauravvishal.spring_security_form_oauth.repo.UserRepo;

@Service
public class OIDCUserRegistrationHandler {

	@Autowired
	private UserRepo userRepo;

	public void registerOrUpdateUser(OidcUser oidcUser) {

        Map<String, Object> oAuth2Attr = oidcUser.getAttributes();

        System.out.println("OAuth2 Attr: " + oAuth2Attr);

        User user = new User();
        user.setEmail(oAuth2Attr.get("email").toString());
        user.setFirstName(oAuth2Attr.get("given_name").toString());
        user.setLastName(oAuth2Attr.get("family_name").toString());
        user.setImageUrl(oAuth2Attr.get("picture").toString());
        user.setAuthProvider(oAuth2Attr.get("iss").toString());
        user.setEmailVerified(oAuth2Attr.get("email_verified").toString().equals("true") ? true : false);

        User userOptional = userRepo.findByEmail(user.getEmail());

        if(userOptional == null) {
            userRepo.save(user);
        } else {
            user.setFirstName(oAuth2Attr.get("given_name").toString());
            user.setLastName(oAuth2Attr.get("family_name").toString());
            user.setImageUrl(oAuth2Attr.get("picture").toString());
            userRepo.save(user);
        }

		System.out.println("User Registered/Updated");

	}



}