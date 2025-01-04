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
        user.setEmail((String) oAuth2Attr.get("email"));
        user.setFirstName(oAuth2Attr.get("name") != null ? oAuth2Attr.get("name").toString() : "");
        user.setLastName("");
        user.setImageUrl(oAuth2Attr.get("avatar_url") != null ? oAuth2Attr.get("avatar_url").toString() : "");
        user.setAuthProvider(oAuth2Attr.get("url") != null ? oAuth2Attr.get("url").toString() : "");
        user.setEmailVerified(oAuth2Attr.get("email") != null && !((String) oAuth2Attr.get("email")).isEmpty());
        
        User userOptional = userRepo.findByEmail(user.getEmail());

        if(userOptional == null) {
            userRepo.save(user);
        } else {
            userOptional.setFirstName(oAuth2Attr.get("name") != null ? oAuth2Attr.get("name").toString() : "");
            userOptional.setLastName("");
            userOptional.setImageUrl(oAuth2Attr.get("avatar_url") != null ? oAuth2Attr.get("avatar_url").toString() : "");
            userOptional.setAuthProvider(oAuth2Attr.get("url") != null ? oAuth2Attr.get("url").toString() : "");
            userRepo.save(userOptional);
        }

	}

}