package edu.it10.vuquangdung.spring.security.oauth2;

import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import edu.it10.vuquangdung.spring.security.successHandler.PrincipalHandler;

@Component
public class OAuth2UserHandler implements PrincipalHandler {
    @Override
    public boolean supports(Object principal) {
        return principal instanceof DefaultOAuth2User;
    }

    @Override
    public String extractEmail(Object principal) {
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) principal;
        String email = oAuth2User.getAttribute("email");
        if (email != null) {
            return email;
        }
        String login = oAuth2User.getAttribute("login");
        if (login != null) {
            return login.trim();
        }
        throw new IllegalArgumentException("No email or login found!");
    }
}
