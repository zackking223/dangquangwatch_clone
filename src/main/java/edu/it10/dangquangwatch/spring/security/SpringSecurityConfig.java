package edu.it10.dangquangwatch.spring.security;

import javax.sql.DataSource;

import edu.it10.dangquangwatch.spring.security.oauth2.CustomOAuth2UserService;
import edu.it10.dangquangwatch.spring.security.successHandler.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomAuthenticationSuccessHandler customSuccessHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SpringSecurityConfig(
            CustomOAuth2UserService customOAuth2UserService,
            CustomAuthenticationSuccessHandler customSuccessHandler,
            CustomAccessDeniedHandler customDeniedHandler) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.customAccessDeniedHandler = customDeniedHandler;
    }

    // add support for JDBC (store accounts in database)
    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "Select username, password, enabled from taikhoan where username=?");

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "Select username, loai_tai_khoan as 'role' from taikhoan where username=?");
        // Automaticly look at tables `users` and `authorities`
        return jdbcUserDetailsManager;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAccessDeniedHandler customAccessDeniedHandler) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable csrf for testing
                .cors(cors -> cors.configurationSource
                        (httpServletRequest -> {
                            CorsConfiguration config = new CorsConfiguration();
                            // config.setAllowedOrigins(List.of("http://localhost:3000")); // Frontend local
                            config.setAllowedOrigins(List.of("https://dangquangwatchclone.up.railway.app")); // Frontend production
                            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                            config.setAllowCredentials(true); // Cho phép cookie/sessions
                            return config;
                        }))
                .oauth2Login(page -> page
                        .loginPage("/login")
                        .userInfoEndpoint(endpoint -> endpoint
                                .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)
                        .permitAll())
                .formLogin((formLogin) -> formLogin
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .failureUrl("/login?failed")
                        .successHandler(customSuccessHandler)
                        .permitAll(true))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true) // Invalidate the session
                        .deleteCookies("JSESSIONID") // Delete cookies
                        .permitAll())
                .authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/admin/**", "/ws/**").hasAnyRole("NHANVIEN", "QUANLY") // Yêu cầu đăng nhập cho các đường dẫn bắt đầu bằng /admin/
                        .requestMatchers("/profile/**").authenticated()
                        .anyRequest().permitAll() // Các đường dẫn còn lại không yêu cầu đăng nhập
                )
                .exceptionHandling(exHandler -> exHandler.accessDeniedHandler(this.customAccessDeniedHandler));

        return http.build();
    }
}
