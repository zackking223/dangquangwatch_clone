package edu.it10.dangquangwatch.spring.AppCustomException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpSession;

// @ControllerAdvice
public class GlobalExceptionHandler {
  // @Autowired
  HttpSession session;

  @ExceptionHandler(DuplicateEntryException.class)
  public String handleDuplicateEntryException(DuplicateEntryException ex) {
    session.setAttribute(ErrorEnum.UPDATE_PROFILE_ERROR.name(), ex.getMessage());
    return "redirect:/profile/doithongtin";
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public String handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    String errorMessage = "Data integrity violation: " + ex.getMostSpecificCause().getMessage();
    session.setAttribute(ErrorEnum.UPDATE_PROFILE_ERROR.name(), errorMessage);
    return "redirect:/profile/doithongtin";
  }
}
