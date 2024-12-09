package edu.it10.dangquangwatch.spring.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;
import edu.it10.dangquangwatch.spring.service.impl.CustomOAuth2UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
  @Autowired
  private CustomOAuth2UserServiceImpl customOAuth2UserServiceImpl;

  // add support for JDBC (store accounts in database)
  @Bean
  UserDetailsManager userDetailsManager(DataSource dataSource) {
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

    // define query to retrieve an user by username
    jdbcUserDetailsManager.setUsersByUsernameQuery(
        "Select username, password, enabled from taikhoan where username=?");

    // define query to retrieve the authorities/roles by username
    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
        "Select username, loai_tai_khoan as 'role' from taikhoan where username=?");
    // Automaticly look at tables `users` and `authorities`
    return jdbcUserDetailsManager;
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(configurer -> configurer
            .requestMatchers("/admin/**", "/ws/**").hasAnyRole("NHANVIEN", "QUANLY") // Yêu cầu đăng nhập cho các đường dẫn bắt đầu bằng /admin/
            .requestMatchers("/profile/**", "/api/vnpay/**").authenticated()
            .anyRequest().permitAll() // Các đường dẫn còn lại không yêu cầu đăng nhập
        )
        .csrf(configurer -> configurer.disable()) // Disable csrf for testing
        .cors(cors -> cors.configurationSource(_ -> {
          CorsConfiguration config = new CorsConfiguration();
          config.setAllowedOrigins(List.of("http://localhost:3000")); // Chỉ cho phép frontend
          config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
          config.setAllowCredentials(true); // Cho phép cookie/sessions
          return config;
        }))
        .oauth2Login(page -> page
            .loginPage("/login")
            .userInfoEndpoint(endpoint -> endpoint
                .userService(customOAuth2UserServiceImpl))
            .successHandler(successHandler())
            .permitAll())
        .formLogin((formLogin) -> formLogin
            .usernameParameter("username")
            .passwordParameter("password")
            .loginPage("/login")
            .failureUrl("/login?failed")
            .successHandler(successHandler())
            .permitAll(true))
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .invalidateHttpSession(true) // Invalidate the session
            .deleteCookies("JSESSIONID") // Delete cookies
            .permitAll())
        .exceptionHandling(exHandl -> exHandl.accessDeniedHandler(deniedHandler()));

    // Use HTTP Basic authentication
    http.httpBasic(Customizer.withDefaults());

    // CSRF is not required for stateless Rest APIs that use POST, PUT, DELETE
    http.csrf(csrf -> csrf.disable());

    return http.build();
  }

  @Bean
  public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
    return new DefaultOAuth2UserService();
  }

  @Bean
  public AuthenticationSuccessHandler successHandler() {
    return new CustomAuthenticationSuccessHandler();
  }

  @Bean
  public AccessDeniedHandler deniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  private static class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {
      response.sendRedirect("/access-denied");
    }
  }

  private static class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    TaiKhoanService taiKhoanService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        org.springframework.security.core.Authentication authentication)
        throws IOException, ServletException {

      // Get the principal object:
      Object principal = authentication.getPrincipal();
      String email; // user email

      // Check the object type using instance:
      if (principal instanceof UserDetails) {

        UserDetails userDetails = (UserDetails) principal;

        // Xử lý UserDetails
        email = userDetails.getUsername();

      } else if (principal instanceof DefaultOAuth2User) {

        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) principal;

        // Xử lý DefaultOAuth2User
        String login = oAuth2User.getAttribute("login");
        String tempEmail = oAuth2User.getAttribute("email");
        if (tempEmail != null) {
          email = tempEmail;
        } else if (login != null) {
          email = login.trim();
        } else {
          throw new IllegalArgumentException("No email or login inside oAuth2User object");
        }
      } else {
        // Xử lý trường hợp không phải UserDetails hoặc DefaultOAuth2User
        throw new IllegalArgumentException("Unsupported principal type: " + principal.getClass().getName());
      }

      // Get user details from authentication object
      TaiKhoan userData = taiKhoanService.getTaiKhoan(email);

      if (userData != null) {
        // Add fullname to session
        request.getSession().setAttribute("username", userData.getUsername());
        request.getSession().setAttribute("role", userData.getLoai_tai_khoan());
        request.getSession().setAttribute("hoten", userData.getHoten());
      }

      // Redirect to default success URL
      super.onAuthenticationSuccess(request, response, authentication);
    }
  }
}
