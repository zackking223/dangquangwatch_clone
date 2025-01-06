package edu.it10.dangquangwatch.spring.security.oauth2;

import java.util.Map;
import java.util.HashMap;

import edu.it10.dangquangwatch.spring.security.oauth2.emailProvider.OAuth2EmailProvider;
import edu.it10.dangquangwatch.spring.service.impl.CustomOAuth2User;

import edu.it10.dangquangwatch.spring.service.taikhoan.TaiKhoanManager;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final TaiKhoanManager taikhoanManager;
    private final OAuth2EmailProvider emailProvider;

    public CustomOAuth2UserService(TaiKhoanManager taikhoanManager, OAuth2EmailProvider emailProvider) {
        this.taikhoanManager = taikhoanManager;
        this.emailProvider = emailProvider;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        // Lấy các thuộc tính từ OAuth2User
        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
        String email = oAuth2User.getAttribute("email");

        if (email == null) {
            email = emailProvider.fetchEmail(userRequest);
            if (email != null) {
                // Thay đổi hoặc thêm thuộc tính
                attributes.put("email", email);
            }
        }

        String name = oAuth2User.getAttribute("name");
        String login = oAuth2User.getAttribute("login");
        attributes.put("name", name);
        attributes.put("login", login);

        // Throw exception if user == null
        taikhoanManager.getTaiKhoan(email != null ? email : login);

        // Tạo CustomOAuth2User với các thuộc tính đã thay đổi
        return new CustomOAuth2User(oAuth2User, attributes);
    }
}
