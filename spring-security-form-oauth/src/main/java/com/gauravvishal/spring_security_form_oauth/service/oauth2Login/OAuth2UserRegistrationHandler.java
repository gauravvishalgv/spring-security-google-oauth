package com.gauravvishal.spring_security_form_oauth.service.oauth2Login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.gauravvishal.spring_security_form_oauth.model.User;
import com.gauravvishal.spring_security_form_oauth.repo.UserRepo;

@Service
public class OAuth2UserRegistrationHandler {

	@Autowired
	private UserRepo userRepo;

	public void registerOrUpdateUser(OAuth2User oAuth2User) {


		Map<String, Object> oAuth2Attr = oAuth2User.getAttributes();

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
            userOptional.setFirstName(oAuth2Attr.get("given_name").toString());
            userOptional.setLastName(oAuth2Attr.get("family_name").toString());
            userOptional.setImageUrl(oAuth2Attr.get("picture").toString());
            userRepo.save(userOptional);
        }

		System.out.println("User Registered/Updated");

	}

}