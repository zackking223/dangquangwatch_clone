package edu.it10.dangquangwatch.spring.security.successHandler;

public interface PrincipalHandler {
    boolean supports(Object principal);
    String extractEmail(Object principal);
}
