package edu.it10.dangquangwatch.spring.AppCustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.it10.dangquangwatch.spring.entity.ApiResponse;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalExceptionHandler {
  @Autowired
  HttpSession session;

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new ApiResponse(false, ex.getMessage()));
  }

  @ExceptionHandler(OrderItemException.class)
  public ResponseEntity<ApiResponse> handleOrderItemException(OrderItemException ex) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new ApiResponse(false, ex.getMessage()));
  }

  @ExceptionHandler(EmptyOrNullListException.class)
  public ResponseEntity<ApiResponse> handleEmptyOrNullListException(EmptyOrNullListException ex) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(new ApiResponse(false, ex.getMessage()));
  }

  @ExceptionHandler(SaveAccountException.class)
  public String handleSaveAccountException(SaveAccountException ex) {
    session.setAttribute(ex.getSessionErrorAttribute().name(), ex.getMessage());
    session.setAttribute("taikhoan", ex.getTaiKhoan());
    return ex.getRedirect();
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public String handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    String errorMessage = "Data integrity violation: " + ex.getMostSpecificCause().getMessage();
    session.setAttribute(ErrorEnum.UPDATE_PROFILE_ERROR.name(), errorMessage);
    return "redirect:/profile/doithongtin";
  }
}
