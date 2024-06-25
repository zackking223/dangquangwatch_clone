package edu.it10.dangquangwatch.spring.security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
  // add support for JDBC (store accounts in database)
  @Bean
  public UserDetailsManager userDetailsManager(DataSource dataSource) {
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
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(configurer -> configurer
            .requestMatchers("/admin/**").hasRole("QUANTRI") // Yêu cầu đăng nhập cho các đường dẫn bắt đầu bằng /admin/
            .requestMatchers("/cart/**").authenticated()
            .anyRequest().permitAll() // Các đường dẫn còn lại không yêu cầu đăng nhập
        )
        .formLogin(Customizer.withDefaults());

    // Use HTTP Basic authentication
    http.httpBasic(Customizer.withDefaults());

    // CSRF is not required for stateless Rest APIs that use POST, PUT, DELETE
    http.csrf(csrf -> csrf.disable());

    return http.build();
  }
}
