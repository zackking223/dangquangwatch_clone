package edu.it10.dangquangwatch.spring.security.successHandler;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsHandler implements PrincipalHandler{
    @Override
    public boolean supports(Object principal) {
        return principal instanceof UserDetails;
    }

    @Override
    public String extractEmail(Object principal) {
        return ((UserDetails) principal).getUsername();
    }
}
