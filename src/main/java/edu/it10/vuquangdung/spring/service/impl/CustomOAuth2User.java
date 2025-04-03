package edu.it10.vuquangdung.spring.service.impl;

import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class CustomOAuth2User extends DefaultOAuth2User {

  private final Map<String, Object> customAttributes;

  public CustomOAuth2User(OAuth2User oauth2User, Map<String, Object> customAttributes) {
    super(oauth2User.getAuthorities(), oauth2User.getAttributes(), "email");
    this.customAttributes = customAttributes;
  }

  @Override
  public <A> A getAttribute(String name) {
    Object value = customAttributes.get(name);
    if (value == null) {
      @SuppressWarnings("unchecked")
      A nullVal = (A) "null";
      return nullVal;
    }
    try {
      // Chuyển đổi kiểu một cách an toàn
      @SuppressWarnings("unchecked")
      A castedValue = (A) value;
      return castedValue;
    } catch (ClassCastException e) {
      // Xử lý lỗi nếu cần
      return null;
    }
  }

  @Override
  public Map<String, Object> getAttributes() {
    // Trả về các thuộc tính tùy chỉnh
    return this.customAttributes;
  }
}
