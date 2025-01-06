package edu.it10.dangquangwatch.spring.security.successHandler;

import edu.it10.dangquangwatch.spring.entity.TaiKhoan;
import edu.it10.dangquangwatch.spring.service.taikhoan.TaiKhoanManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TaiKhoanManager taikhoanManager;
    private final List<PrincipalHandler> principalHandlers;

    public CustomAuthenticationSuccessHandler(
            TaiKhoanManager taikhoanManager, List<PrincipalHandler> principalHandlers) {
        this.taikhoanManager = taikhoanManager;
        this.principalHandlers = principalHandlers;
    }

    private String getEmailFromPrincipal(Object principal) {
        return principalHandlers.stream()
                .filter(handler -> handler.supports(principal))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No handler found for principal"))
                .extractEmail(principal);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        org.springframework.security.core.Authentication authentication)
            throws IOException, ServletException {

        // Get the principal object:
        Object principal = authentication.getPrincipal();
        String email = getEmailFromPrincipal(principal); // user email

        // Get user details from authentication object
        TaiKhoan userData = taikhoanManager.getTaiKhoan(email);

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
