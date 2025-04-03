package edu.it10.vuquangdung.spring.security.successHandler;

public interface PrincipalHandler {
    boolean supports(Object principal);
    String extractEmail(Object principal);
}
