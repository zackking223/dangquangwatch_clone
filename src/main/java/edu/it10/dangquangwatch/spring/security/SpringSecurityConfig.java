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
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.TaiKhoanService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
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
            .requestMatchers("/admin/**").hasRole("QUANTRI") // Yêu cầu đăng nhập cho các đường dẫn bắt đầu bằng /admin/
            .requestMatchers("/profile/**").authenticated()
            .anyRequest().permitAll() // Các đường dẫn còn lại không yêu cầu đăng nhập
        )
        // .loginPage("/login")
        // .failureUrl("/login?failed")
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
        .exceptionHandling(exHandl -> 
          exHandl.accessDeniedHandler(deniedHandler())
        );

    // Use HTTP Basic authentication
    http.httpBasic(Customizer.withDefaults());

    // CSRF is not required for stateless Rest APIs that use POST, PUT, DELETE
    http.csrf(csrf -> csrf.disable());

    return http.build();
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
      // Get user details from authentication object

      UserDetails userDetail = (UserDetails) authentication.getPrincipal();
      TaiKhoan userData = taiKhoanService.getTaiKhoan(userDetail.getUsername());

      // Add fullname to session
      request.getSession().setAttribute("username", userDetail.getUsername());
      request.getSession().setAttribute("roles", userDetail.getAuthorities());
      request.getSession().setAttribute("hoten", userData.getHoten());

      // Redirect to default success URL
      super.onAuthenticationSuccess(request, response, authentication);
    }
  }
}
