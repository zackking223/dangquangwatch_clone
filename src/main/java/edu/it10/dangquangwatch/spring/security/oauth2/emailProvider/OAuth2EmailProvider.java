package edu.it10.dangquangwatch.spring.security.oauth2.emailProvider;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

public interface OAuth2EmailProvider {
    String fetchEmail(OAuth2UserRequest oAuth2UserRequest);
}
