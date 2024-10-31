package edu.it10.dangquangwatch.spring.AppCustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;

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
  public String handleDataIntegrityViolationException(
      DataIntegrityViolationException ex,
      @RequestHeader(value = "Referer", required = false) String referer) {

    String errorMessage = "Data integrity violation: " + ex.getMostSpecificCause().getMessage();
    session.setAttribute(ErrorEnum.ADMIN_ERROR.name(), errorMessage);

    // Redirect về trang gốc nếu header Referer có tồn tại
    return referer != null ? "redirect:" + referer : "redirect:/admin/dongho/";
  }

  @ExceptionHandler(ServiceException.class)
  public String handleServiceException(ServiceException ex) {
    session.setAttribute(ErrorEnum.ADMIN_ERROR.name(), ex.getMessage());
    return ex.getRedirect();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public String handleEntityNotFoundException(EntityNotFoundException ex) {
    session.setAttribute(ErrorEnum.ADMIN_ERROR.name(), ex.getMessage());
    return ex.getRedirect();
  }
}
