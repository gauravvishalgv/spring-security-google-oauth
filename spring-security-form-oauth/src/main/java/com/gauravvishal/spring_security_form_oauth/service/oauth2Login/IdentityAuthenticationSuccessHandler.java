package com.gauravvishal.spring_security_form_oauth.service.oauth2Login;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;


@Service
public final class IdentityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final AuthenticationSuccessHandler delegate = new SavedRequestAwareAuthenticationSuccessHandler();

    @Autowired
    private OIDCUserRegistrationHandler oidcHandler;

    @Autowired
    private OAuth2UserRegistrationHandler oAuth2Handler;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		if (authentication instanceof OAuth2AuthenticationToken) {
			if (authentication.getPrincipal() instanceof OidcUser) {
				oidcHandler.registerOrUpdateUser((OidcUser) authentication.getPrincipal());
			} else if (authentication.getPrincipal() instanceof OAuth2User) {
				oAuth2Handler.registerOrUpdateUser((OAuth2User) authentication.getPrincipal());
			}
		}

		this.delegate.onAuthenticationSuccess(request, response, authentication);
	}


}