package edu.it10.vuquangdung.spring.AppCustomException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.IllegalArgumentException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.it10.vuquangdung.spring.entity.response.ApiResponse;
import edu.it10.vuquangdung.spring.helper.ParameterNameMapper;
import edu.it10.vuquangdung.spring.payment.PaymentException;

import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
  @Autowired
  HttpSession session;

  @ExceptionHandler(PaymentException.class)
  public ResponseEntity<ApiResponse> handlePaymentException(PaymentException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ApiResponse(false, "Lỗi thanh toán: " + ex.getMessage()));
  }

  @ExceptionHandler(OrderException.class)
  public ResponseEntity<ApiResponse> handleOrderItemException(OrderException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ApiResponse(false, "Lỗi đặt hàng: " + ex.getMessage()));
  }

  @ExceptionHandler(EmptyOrNullListException.class)
  public ResponseEntity<ApiResponse> handleEmptyOrNullListException(EmptyOrNullListException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ApiResponse(false, "Danh sách bị trống: " + ex.getMessage()));
  }

  @ExceptionHandler(SaveAccountException.class)
  public String handleSaveAccountException(
      SaveAccountException ex,
      HttpServletRequest request,
      RedirectAttributes redirectAttributes) {
    session.setAttribute(ex.getSessionErrorAttribute(), ex.getMessage());
    session.setAttribute("taikhoan", ex.getTaiKhoan());
    redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
    String referer = request.getHeader("Referer");
    return ex.getRedirect() != null ? ex.getRedirect() : "redirect:" + referer;
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public String handleDataIntegrityViolationException(
      DataIntegrityViolationException ex,
      HttpServletRequest request,
      RedirectAttributes redirectAttributes) {

    String errorMessage = "Data integrity violation: " + ex.getMostSpecificCause().getMessage();
    session.setAttribute(ErrorEnum.ADMIN_ERROR.name(), errorMessage);
    redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
    String referer = request.getHeader("Referer");
    // Redirect về trang gốc nếu header Referer có tồn tại
    return referer != null ? "redirect:" + referer : "redirect:/admin/dongho/";
  }

  @ExceptionHandler(ServiceException.class)
  public String handleServiceException(
      ServiceException ex,
      HttpServletRequest request,
      RedirectAttributes redirectAttributes) {
    session.setAttribute(
        ex.getSessionErrorAttribute() != null ? ex.getSessionErrorAttribute() : ErrorEnum.ADMIN_ERROR.name(),
        ex.getMessage());
    String referer = request.getHeader("Referer");
    String redirect = ex.getRedirect();
    redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
    return redirect != null ? redirect : (referer != null ? "redirect:" + referer : "redirect:/default-page");
  }

  @ExceptionHandler(ControllerException.class)
  public String handleControllerException(ControllerException ex) {
    session.setAttribute(ex.getSessionErrorAttribute() != null ? ex.getSessionErrorAttribute() : ErrorEnum.INDEX.name(),
        ex.getMessage());
    log.error("REDIRECT ERROR: " + ex.getRedirect());
    return ex.getRedirect();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public String handleEntityNotFoundException(EntityNotFoundException ex) {
    session.setAttribute(ErrorEnum.ADMIN_ERROR.name(), ex.getMessage());
    return ex.getRedirect();
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public String handleMissingParameterException(
      MissingServletRequestParameterException ex,
      HttpServletRequest request,
      RedirectAttributes redirectAttributes) {

    // Lấy tên tham số bị thiếu
    String missingParam = ParameterNameMapper.getFriendlyName(ex.getParameterName());

    // Gửi thông báo lỗi tới trang form
    redirectAttributes.addFlashAttribute("errorMessage", missingParam + " không được để trống.");

    // Lấy đường dẫn gốc (URL mà người dùng truy cập)
    String referer = request.getHeader("Referer");

    // Nếu không có đường dẫn trước, quay về một trang mặc định
    if (referer == null || referer.isEmpty()) {
      return "redirect:/"; // Trang mặc định
    }

    // Redirect về đường dẫn gốc
    return "redirect:" + referer;
  }

  @ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
  public String handleSQLIntegrityConstraintViolationException(
      java.sql.SQLIntegrityConstraintViolationException ex,
      HttpServletRequest request,
      RedirectAttributes redirectAttributes) {

    // Lấy thông báo lỗi từ exception
    String errorMessage = ex.getMessage();

    // Sử dụng Regex để tìm tên cột bị vi phạm
    String columnName = ParameterNameMapper.getFriendlyName(extractColumnName(errorMessage));

    // Gửi thông báo lỗi chi tiết
    String userMessage = columnName != null
        ? columnName + " không được để trống."
        : "Dữ liệu không hợp lệ, vui lòng kiểm tra lại.";
    redirectAttributes.addFlashAttribute("errorMessage", userMessage);

    // Redirect về trang trước đó
    String referer = request.getHeader("Referer");
    return (referer != null && !referer.isEmpty()) ? "redirect:" + referer : "redirect:/";
  }

  private String extractColumnName(String errorMessage) {
    // Regex để tìm cột từ thông báo lỗi (vd: "Column 'giatien' cannot be null")
    Pattern pattern = Pattern.compile("Column '(\\w+)' cannot be null");
    Matcher matcher = pattern.matcher(errorMessage);
    return matcher.find() ? matcher.group(1) : null; // Trả về tên cột nếu tìm thấy
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public String handleValidationExceptions(MethodArgumentNotValidException ex,
      RedirectAttributes redirectAttributes,
      HttpServletRequest request) {
    // Tạo thông điệp lỗi từ các trường không hợp lệ
    String errorMessage = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
        .reduce((a, b) -> a + ", " + b)
        .orElse("Lỗi không xác định");

    // Thêm thông điệp lỗi vào redirectAttributes
    redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

    // Lấy referer (trang trước đó)
    String referer = request.getHeader("Referer");

    // Redirect về referer với thông điệp lỗi
    return "redirect:" + (referer != null ? referer : "/"); // Nếu không có referer thì chuyển về trang chủ
  }

  @ExceptionHandler(OtpException.class)
  public Object handleOtpException(
      OtpException ex,
      HttpServletRequest request,
      Model model,
      RedirectAttributes redirectAttributes) {

    // Lấy thông báo lỗi từ exception
    String errorMessage = ex.getMessage();

    if (ex.isSendingJson()) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(new ApiResponse(false, "Lỗi otp, " + ex.getMessage()));
    }

    if (ex.getRedirect() != null) {
      redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
      return ex.getRedirect();
    } else {
      model.addAttribute("errorMessage", errorMessage);
      return "error/otperror";
    }
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public String handleValidationExceptions(
      ConstraintViolationException ex,
      RedirectAttributes redirectAttributes,
      HttpServletRequest request) {

    // Tạo thông điệp lỗi từ các vi phạm
    String errorMessage = ex.getConstraintViolations()
        .stream()
        .map(violation -> violation.getMessage()) // Lấy thông điệp lỗi
        .reduce((a, b) -> a + ", " + b) // Ghép các thông điệp lỗi nếu có nhiều hơn 1
        .orElse("Lỗi không xác định");

    // Thêm thông điệp lỗi vào redirectAttributes
    redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

    // Lấy referer (trang trước đó)
    String referer = request.getHeader("Referer");

    // Redirect về referer với thông điệp lỗi
    return "redirect:" + (referer != null ? referer : "/"); // Nếu không có referer thì chuyển về trang chủ
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public String handleIllegalArgumentException(
      IllegalArgumentException ex,
      RedirectAttributes redirectAttributes,
      HttpServletRequest request) {

    // Tạo thông điệp lỗi từ các vi phạm
    String errorMessage = ex.getMessage();

    // Thêm thông điệp lỗi vào redirectAttributes
    redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

    // Lấy referer (trang trước đó)
    String referer = request.getHeader("Referer");

    // Redirect về referer với thông điệp lỗi
    return "redirect:" + (referer != null ? referer : "/"); // Nếu không có referer thì chuyển về trang chủ
  }
}
