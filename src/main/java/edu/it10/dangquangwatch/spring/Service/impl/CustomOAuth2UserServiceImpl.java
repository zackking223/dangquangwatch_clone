package edu.it10.dangquangwatch.spring.service.impl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.CustomOAuth2UserService;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;

@Service
public class CustomOAuth2UserServiceImpl extends DefaultOAuth2UserService implements CustomOAuth2UserService {
  @Autowired
  private TaiKhoanService taiKhoanService;

  private static final String EMAIL_API_URL = "https://api.github.com/user/emails";

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    String email = oAuth2User.getAttribute("email");
    // Lấy các thuộc tính từ OAuth2User
    Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());

    if (email == null) {
      // Lấy access token từ OAuth2UserRequest
      String accessToken = userRequest.getAccessToken().getTokenValue();

      // Gửi yêu cầu GET đến GitHub API để lấy email
      RestTemplate restTemplate = new RestTemplate();

      // Tạo headers với token
      HttpHeaders headers = new HttpHeaders();
      headers.setBearerAuth(accessToken);

      // Gửi yêu cầu GET đến GitHub API để lấy email
      HttpEntity<String> entity = new HttpEntity<>(headers);
      ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
          EMAIL_API_URL, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Map<String, Object>>>() {
          });

      // Lấy email từ phản hồi
      if (response.getStatusCode() == HttpStatus.OK) {
        List<Map<String, Object>> emailList = response.getBody();
        if (emailList != null && !emailList.isEmpty()) {
          email = (String) emailList.get(0).get("email");
        
          // Thay đổi hoặc thêm thuộc tính
          attributes.put("email", email);
        }
      }
    }

    String name = oAuth2User.getAttribute("name");
    String login = oAuth2User.getAttribute("login");
    attributes.put("name", name);
    attributes.put("login", login);

    TaiKhoan user = taiKhoanService.getTaiKhoan(email != null ? email : login);

    if (user == null) {
      user = new TaiKhoan();
      user.setUsername(email != null ? email : login);
      user.setHoten(name != null ? name : login);
      user.setDiachi("Chưa có");
      user.setSodienthoai(null); // Has Unique constraint!
      user.setPassword("fun123");
      user.setEnabled(1);

      // Set other attributes as needed
      try {
        taiKhoanService.dangKyKhachHang(user);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // Tạo CustomOAuth2User với các thuộc tính đã thay đổi
    return new CustomOAuth2User(oAuth2User, attributes);
  }
}
