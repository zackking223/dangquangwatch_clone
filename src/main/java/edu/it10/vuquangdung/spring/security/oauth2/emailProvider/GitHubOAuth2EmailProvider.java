package edu.it10.vuquangdung.spring.security.oauth2.emailProvider;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class GitHubOAuth2EmailProvider implements OAuth2EmailProvider {
    private final RestTemplate restTemplate;

    public GitHubOAuth2EmailProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String fetchEmail(OAuth2UserRequest oAuth2UserRequest) {
        String accessToken = oAuth2UserRequest.getAccessToken().getTokenValue();

        // Tạo headers với token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        // Gửi yêu cầu GET đến GitHub API để lấy email
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String GITHUB_EMAIL_API_URL = "https://api.github.com/user/emails";
        ResponseEntity<List<Map<String, Object>>> response =
                restTemplate.exchange(
                        GITHUB_EMAIL_API_URL,
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<>() {});

        // Lấy email từ phản hồi
        if (response.getStatusCode() == HttpStatus.OK) {
            List<Map<String, Object>> emailList = response.getBody();
            if (emailList != null && !emailList.isEmpty()) {
                return (String) emailList.get(0).get("email");
            }
        }
        return null;
    }
}
